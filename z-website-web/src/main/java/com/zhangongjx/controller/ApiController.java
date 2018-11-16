package com.zhangongjx.controller;

import com.alibaba.fastjson.JSON;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.VerifyCodeHelper;
import com.zhangongjx.message.auth.request.SaveUserRequest;
import com.zhangongjx.message.auth.response.SaveUserResponse;
import com.zhangongjx.message.auth.view.HomeViewModel;
import com.zhangongjx.model.c.ConfAdEntity;
import com.zhangongjx.model.s.UserEntity;
import com.zhangongjx.repository.c.IConfAdRepository;
import com.zhangongjx.service.IAuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: wanghaojie
 * @description：
 * @date: 2018/9/12 17:13
 */
@Controller
@RequestMapping("/api")
public class ApiController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IConfAdRepository confAdRepository;
    @Autowired
    private IAuthService authService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("homeView", new HomeViewModel());
        return "test";
    }

    /**
     * 获取商品列表
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/goods_data", method = RequestMethod.GET)
    public ResutlMsg goodsData() {
        ResutlMsg res = ResutlMsg.success();
        StringBuffer buffer = new StringBuffer();
        buffer.append("1=1 ");
        int count = confAdRepository.getCount(buffer.toString());
        List<ConfAdEntity> items = confAdRepository.getDatas(0, count, buffer.toString());
        res.setData(items);
        res.setRecordsTotal(count);
        logger.info("items={}", JSON.toJSONString(res));
        return res;
    }

    /**
     * 用户注册接口
     * @param req
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/save_user", method = RequestMethod.POST)
    public ResutlMsg saveUser(@RequestBody  SaveUserRequest req) {
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        logger.info("/api/save_user 接收参数 req={}" , req);
//        if(req.getUsername())
        SaveUserResponse resp = authService.saveUser(req);
        return resp.getResultMsg();
    }

    /**
     * 通过手机号，获取注册时的验证码
     * 对于已经注册过的手机号，不用发送短信获取验证码
     * @param phoneNo 手机号
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/getRegisterCode", method = RequestMethod.GET)
    public ResutlMsg getRegisterCode(String phoneNo){
        logger.info("/api/getRegisterCode 接收参数 phoneNo={}" , phoneNo);
        ResutlMsg res = ResutlMsg.success();
        if(StringUtils.isEmpty(phoneNo)){
            res = ResutlMsg.error("手机号不能为空，请输入手机号");
            return res;
        }
        res.setMsg("success");
        //这里需要增加对于手机号的验证规则，判断该手机号是否已经注册
        UserEntity userEntity = authService.getUserEntityByPhone(phoneNo);
        if(userEntity != null){
            res = ResutlMsg.error("该手机号已经注册，请使用其他手机号注册");
            return res;
        }
        res.setData(VerifyCodeHelper.getRandNum(6));

        return res;
    }

}
