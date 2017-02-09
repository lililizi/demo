package org.lizi.demo.services.Impl;

import org.apache.commons.collections.MapUtils;
import org.lizi.demo.Exception.GetCloseException;
import org.lizi.demo.Exception.RedPacketException;
import org.lizi.demo.Exception.RepeatException;
import org.lizi.demo.dao.SuccessGetMapper;
import org.lizi.demo.dao.cache.RedisDao;
import org.lizi.demo.domain.RedPacket;
import org.lizi.demo.domain.SuccessGet;
import org.lizi.demo.dao.redpacketsMapper;
import org.lizi.demo.dto.Exposer;
import org.lizi.demo.dto.GetMon;
import org.lizi.demo.enums.GetStatEnum;
import org.lizi.demo.services.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * Created by touch on 2017/1/18.
 */
@Service
public class RedPacketServiceImp implements RedPacketService {
    public final String slat="jxcvhewrqew/,']asdfw/*-+efw";

   @Autowired
    private redpacketsMapper mapper;
   @Autowired
   private SuccessGetMapper getMapper;
    @Autowired
    private RedisDao redisDao;
    public List<RedPacket> getRedPacketList() {
        return mapper.queryList();
    }

    public RedPacket queryById(long rId){
        RedPacket redPacket=mapper.queryById(rId);
        return redPacket;
    }

    public Exposer exportRedUrl(long rId) {
        RedPacket redPacket=redisDao.getRedPacket(rId);
        if (redPacket==null) {
            redPacket=mapper.queryById(rId);
            if (redPacket == null) {
                return new Exposer(false,rId);
            }else {
                redisDao.putRedPacket(redPacket);
            }
        }


        Date start=redPacket.getStartTime();
        Date end=redPacket.getEndTime();
        Date now=new Date();
        if (now.getTime()<start.getTime()||now.getTime()>end.getTime())
            return new Exposer(false,rId,now.getTime(),start.getTime(),end.getTime());
        String md5=getMd5(rId);
        return new Exposer(true,md5,rId);
    }
    @Transactional
    public GetMon getMoney(long rId, long userPhone,String md5) throws RepeatException,RedPacketException,GetCloseException,RuntimeException{

        if (md5 == null || !md5.equals(getMd5(rId)))
            throw new RedPacketException("data has been rewrite");
        try {
            float money=money(rId);
            int updatecount = mapper.reduceMoney(rId, money);
            if (updatecount <= 0){
                throw new GetCloseException("activity is close");
            }else {
                int insertcount = getMapper.insertSuccessGet(rId, money, userPhone);
                if (insertcount <= 0) {
                    throw new RepeatException("repeate");
                }else {
                    SuccessGet successGet = getMapper.queryByIdWithRedPacket(rId, userPhone);
                    return new GetMon(rId, money, "success", successGet);
                }
            }

        }catch (RepeatException e) {
            throw  e;
        }catch (GetCloseException e){
            throw e;
        } catch (Exception e){
            throw new RedPacketException("error");
        }

    }

    public GetMon GetMoneyPro(long rId, long userPhone, String md5) {
        if (md5 == null || !md5.equals(getMd5(rId)))
            return new GetMon(rId, GetStatEnum.DATA_REWRITE);
        float money=money(rId);
        Map<String,Object> map=new HashMap<String,Object>();
        map.put("seckillId",rId);
        map.put("phone",userPhone);
        map.put("money",money);
        map.put("result",null);
        try {
            mapper.getbyPro(map);
            int result=MapUtils.getInteger(map,"result",-2);
            if (result==1){
                SuccessGet sg=
                        getMapper.queryByIdWithRedPacket(rId,userPhone);
                return new GetMon(rId,GetStatEnum.SUCCESS,sg);
            }else {
                return new GetMon(rId,GetStatEnum.stateof(result));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new GetMon(rId,GetStatEnum.INNER_ERROR);
        }
    }


    private String getMd5(long id){
        String base=id+"/"+slat;
        String md5= DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
    private float money(long rId){
        RedPacket redPacket = mapper.queryById(rId);
        float f = redPacket.getTotal();
        int temp = (int) f;
        Random random = new Random(temp / 13 * 10 / 100);
        float money = (float) random.nextFloat();
        if (money > redPacket.getBalance())
            money = redPacket.getBalance();
        return money;
    }
}

