package org.lizi.demo.services;

import org.lizi.demo.Exception.GetCloseException;
import org.lizi.demo.Exception.RedPacketException;
import org.lizi.demo.Exception.RepeatException;
import org.lizi.demo.domain.RedPacket;
import org.lizi.demo.domain.SuccessGet;
import org.lizi.demo.dto.Exposer;
import org.lizi.demo.dto.GetMon;

import java.util.List;

/**
 * Created by touch on 2017/1/18.
 */

public interface RedPacketService {
    /**
     * 红包列表
     * @return
     */
    List<RedPacket> getRedPacketList();

    /**
     * 按ID查询
     * @param rId
     * @return
     */
    RedPacket queryById(long rId);

    Exposer exportRedUrl(long rId);

    GetMon getMoney(long rId, long userPhone,String md5)throws RepeatException,RedPacketException,GetCloseException,RuntimeException;

    public GetMon GetMoneyPro(long rId, long userPhone,String md5);
}
