package com.zhangongjx.controller;

import com.zhangongjx.message.auth.view.HomeViewModel;
import com.zhangongjx.model.l.LogLoginEntity;
import com.zhangongjx.repository.l.ILogLoginRepository;
import com.zhangongjx.infrastructure.domain.ResutlMsg;
import com.zhangongjx.message.auth.view.HomeViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping()
public class HomeController {

    @Autowired
    private ILogLoginRepository logLoginRepository;

    @RequestMapping(value = "/",method = RequestMethod.GET)
    public  String index(Model model)
    {
        model.addAttribute("homeView",new HomeViewModel());
        return "index";
    }
    @RequestMapping(value = "/home",method = RequestMethod.GET)
    public  String home()
    {
        return "home/home";
    }

    //log_login
    @RequestMapping(value = "/log_login",method = RequestMethod.GET)
    public  String logLogin()
    {
        return "home/log_login";
    }

    @ResponseBody
    @RequestMapping(value = "/log_login_data",method = RequestMethod.GET)
    public  ResutlMsg<Object> logLoginData(int draw, int length, int start) {

        ResutlMsg res = ResutlMsg.success();
        List<LogLoginEntity> items = logLoginRepository.getDatas(start, length,1);
        int count = logLoginRepository.getCount(1);

        res.setData(items);
        res.setDraw(draw);
        res.setRecordsFiltered(count);
        res.setRecordsTotal(count);
        return res;
    }

    //sys_info
    @RequestMapping(value = "/sys_info",method = RequestMethod.GET)
    public  String sysInfo()
    {
        return "home/sys_info";
    }

    //user_set
    @RequestMapping(value = "/user_set",method = RequestMethod.GET)
    public  String userSet()
    {
        return "home/user_set";
    }

}
