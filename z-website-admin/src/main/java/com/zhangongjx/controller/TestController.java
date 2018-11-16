package com.zhangongjx.controller;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.infrastructure.util.JsonHelper;
import com.zhangongjx.infrastructure.util.MailHelper;
import com.zhangongjx.infrastructure.util.SMSHelper;
import com.zhangongjx.message.sms.request.SMSRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private MailHelper mailHelper;
    @Autowired
    private SMSHelper smsHelper;

    @ResponseBody
    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public ResutlMsg email() {

        ResutlMsg res = ResutlMsg.success();

        String from = "1076239475@qq.com";
        String to = "570340350@qq.com";
        String subject = "测试";
        String text = "测试";

        res = mailHelper.sendEmail(from, to, subject, text);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/sms", method = RequestMethod.GET)
    public ResutlMsg sms() {

        ResutlMsg res = ResutlMsg.success();

        SMSRequest req = new SMSRequest();
        req.setCode("123456");

        String phone = "15001217168";
        String sign = "橙子";
        String template = "SMS_45955007";
        String param = JsonHelper.toJSON(req);

        try {
            SendSmsResponse response = smsHelper.sendSms(phone, sign, template, param);
        } catch (ClientException exception) {

        }
        return res;
    }

}
