package org.lizi.demo.web;

import org.lizi.demo.Exception.GetCloseException;
import org.lizi.demo.Exception.RedPacketException;
import org.lizi.demo.Exception.RepeatException;
import org.lizi.demo.domain.RedPacket;
import org.lizi.demo.dto.Exposer;
import org.lizi.demo.dto.GetMon;
import org.lizi.demo.dto.Result;
import org.lizi.demo.enums.GetStatEnum;
import org.lizi.demo.services.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.List;

/**
 * Created by touch on 2017/1/18.
 */
@Controller
@RequestMapping("/demo")
public class RedPacketController {

    @Autowired
    private RedPacketService service;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        List<RedPacket> list=service.getRedPacketList();
        model.addObject("reds",list);
        model.setViewName("redlist");
        return model;
    }

    @RequestMapping(value = "/{rid}/detail",method = RequestMethod.GET)
    public ModelAndView detail(ModelAndView model, @PathVariable("rid")long rid){
        RedPacket redPacket=service.queryById(rid);
        model.addObject("red",redPacket);
        model.setViewName("detail");
        return model;
    }
    @RequestMapping(value = "/{rId}/exposer",method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Result<Exposer> exposer(@PathVariable Long rId){
        Result<Exposer> result;
        try{
            Exposer exposer=service.exportRedUrl(rId);
            result=new Result<Exposer>(true,exposer);
        }catch (Exception e){
            result =new Result<Exposer>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = "/{rId}/{md5}/execution",method = RequestMethod.POST)
    @ResponseBody
    public Result<GetMon> getmoney(@CookieValue(value = "userPhone",required = false) Long phone,@PathVariable("rId")long rId, @PathVariable("md5")String md5){
        if(phone==null)
            return new Result<GetMon>(false,"未注册");
        Result<Exposer> result;
        try {
            GetMon execution=service.getMoney(rId,phone,md5);
            return new Result<GetMon>(true,execution);
        }catch (Exception e){
            GetMon ex=new GetMon(rId, GetStatEnum.INNER_ERROR);
            return new Result<GetMon>(false,ex);
        }
    }
    @RequestMapping(value = "/time/now",method = RequestMethod.GET)
    @ResponseBody
    public Result<Date> date(){
        return new Result<Date>(true,new Date());
    }
}
