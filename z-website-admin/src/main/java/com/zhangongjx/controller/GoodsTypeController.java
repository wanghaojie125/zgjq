package com.zhangongjx.controller;

import com.zhangongjx.service.IGoodsService;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.message.goods.request.GetGoodsTypeRequest;
import com.zhangongjx.message.goods.request.SaveGoodsTypeRequest;
import com.zhangongjx.message.goods.response.GetGoodsTypeResponse;
import com.zhangongjx.message.goods.response.SaveGoodsTypeResponse;
import com.zhangongjx.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/goods_type")
public class GoodsTypeController {

    @Autowired
    private IGoodsService goodsService;

    //types
    @RequestMapping(value = "/data", method = RequestMethod.POST)
    public ResutlMsg<Object> goods() {

        GetGoodsTypeRequest request=new GetGoodsTypeRequest();
       GetGoodsTypeResponse resp= goodsService.getGoodsType(request);
       return  resp.getResultMsg();
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResutlMsg save(@RequestBody SaveGoodsTypeRequest request) {
      if(request==null)
        {
           return  ResutlMsg.error("未接受到请求参数");
        }
        SaveGoodsTypeResponse resp= goodsService.saveGoodsType(request);
        return  resp.getResultMsg();
    }
}
