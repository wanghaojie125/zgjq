package com.zhangongjx.controller;

import com.alibaba.fastjson.JSON;
import com.zhangongjx.infrastructure.util.MailHelper;
import com.zhangongjx.model.c.ConfAdEntity;
import com.zhangongjx.model.c.ConfEmailTemplateEntity;
import com.zhangongjx.model.c.ConfInfoEntity;
import com.zhangongjx.model.c.ConfVerifyCodeEntity;
import com.zhangongjx.repository.c.IConfEmailTemplateRepository;
import com.zhangongjx.repository.c.IConfInfoRepository;
import com.zhangongjx.repository.c.IConfVerifyCodeRepository;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.repository.c.IConfAdRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/conf")
public class ConfController {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private IConfAdRepository confAdRepository;
    @Autowired
    private IConfVerifyCodeRepository confVerifyCodeRepository;
    @Autowired
    private IConfInfoRepository confInfoRepository;
    @Autowired
    private IConfEmailTemplateRepository confEmailTemplateRepository;
    @Autowired
    private MailHelper mailHelper;

    //ad
    @RequestMapping(value = "/ad_list", method = RequestMethod.GET)
    public String goods() {
        return "conf/ad_list";
    }

    @ResponseBody
    @RequestMapping(value = "/ad_data", method = RequestMethod.GET)
    public ResutlMsg adData(int draw, int length, int start, String search_name, int search_position, int search_date) {

        ResutlMsg res = ResutlMsg.success();

        StringBuffer buffer = new StringBuffer();
        buffer.append("1=1 ");
        if (search_name != null && search_name != "") {
            buffer.append(" and name like '%" + search_name + "%' ");
        }
        if (search_position != 0) {
            buffer.append(" and position=" + search_position);
        }
        if (search_date != 0) {
            Date date = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.DAY_OF_MONTH, +search_date);
            date = calendar.getTime();


            Timestamp timestamp = new Timestamp(date.getTime());
            buffer.append(" and end_at<" + timestamp);
        }
        List<ConfAdEntity> items = confAdRepository.getDatas(start, length, buffer.toString());
        logger.info("/ad_data/adData() List<ConfAdEntity> items = {}",JSON.toJSONString(items));
        int count = confAdRepository.getCount(buffer.toString());

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_ad", method = RequestMethod.POST)
    public ResutlMsg saveAd(@RequestBody ConfAdEntity item) {
        logger.info("conf/save_ad/saveAd(ConfAdEntity={})", JSON.toJSONString(item));
        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }

        if (item.getId() == 0) {
            confAdRepository.insert(item);
            res.setMsg("广告添加成功");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            item.setUpdate_at(timestamp.toString());
            confAdRepository.update(item);
            res.setMsg("广告修改成功");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/delete_ad", method = RequestMethod.POST)
    public ResutlMsg deleteAd(@RequestBody ConfAdEntity item) {

        ResutlMsg res = ResutlMsg.success();
        if (item == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }
        confAdRepository.delete(item.getId());
        res.setMsg("广告删除成功");

        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/enable_ad", method = RequestMethod.POST)
    public ResutlMsg enableAd(@RequestBody ConfAdEntity req) {
        ResutlMsg res = ResutlMsg.success();
        if (req == null) {
            return ResutlMsg.error("未接受到请求参数");
        }
        confAdRepository.updateStatus(req);

        res.setMsg("广告上下架成功");
        return res;
    }

    //verify_code
    @RequestMapping(value = "/verify_code", method = RequestMethod.GET)
    public String verifyCode() {
        return "conf/verify_code";
    }

    @ResponseBody
    @RequestMapping(value = "/verify_code_data", method = RequestMethod.GET)
    public ResutlMsg verifyCodeData() {

        ResutlMsg res = ResutlMsg.success();

        ConfVerifyCodeEntity item = confVerifyCodeRepository.getData();
        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_verify_code", method = RequestMethod.POST)
    public ResutlMsg saveVerifyCode(@RequestBody ConfVerifyCodeEntity req) {

        ResutlMsg res = ResutlMsg.success();
        if (req == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }

        ConfVerifyCodeEntity item = confVerifyCodeRepository.getData();

        if (item == null) {
            confVerifyCodeRepository.insert(req);
            res.setMsg("修改成功");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            req.setUpdate_at(timestamp.toString());
            confVerifyCodeRepository.update(req);
            res.setMsg("修改成功");
        }
        return res;
    }

    //info
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public String info() {
        return "conf/info";
    }

    @ResponseBody
    @RequestMapping(value = "/info_data", method = RequestMethod.GET)
    public ResutlMsg infoData() {

        ResutlMsg res = ResutlMsg.success();

        ConfInfoEntity item = confInfoRepository.getData();
        res.setData(item);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/save_info", method = RequestMethod.POST)
    public ResutlMsg saveInfo(@RequestBody ConfInfoEntity req) {

        ResutlMsg res = ResutlMsg.success();
        if (req == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }

        ConfInfoEntity item = confInfoRepository.getData();

        if (item == null) {
            confInfoRepository.insert(req);
            res.setMsg("修改成功");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            req.setUpdate_at(timestamp.toString());
            confInfoRepository.update(req);
            res.setMsg("修改成功");
        }
        return res;
    }

    //email
    @RequestMapping(value = "/email", method = RequestMethod.GET)
    public String email() {
        return "conf/email";
    }

    @ResponseBody
    @RequestMapping(value = "/email_data", method = RequestMethod.GET)
    public ResutlMsg emailData(String type) {

        ResutlMsg res = ResutlMsg.success();

        ConfEmailTemplateEntity item = confEmailTemplateRepository.getData(type);
        res.setData(item);
        return res;
    }
    @ResponseBody
    @RequestMapping(value = "/save_email", method = RequestMethod.POST)
    public ResutlMsg saveEmail(@RequestBody ConfEmailTemplateEntity req) {

        ResutlMsg res = ResutlMsg.success();
        if (req == null) {
            return ResutlMsg.error("没有接收到请求参数");
        }

        ConfEmailTemplateEntity item = confEmailTemplateRepository.getData(req.getType());

        if (item == null) {
            confEmailTemplateRepository.insert(req);
            res.setMsg("修改成功");
        } else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            req.setUpdate_at(timestamp.toString());
            confEmailTemplateRepository.update(req);
            res.setMsg("修改成功");
        }
        return res;
    }

}
