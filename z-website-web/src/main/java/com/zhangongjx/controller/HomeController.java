package com.zhangongjx.controller;

import com.zhangongjx.message.auth.view.HomeViewModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping()
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model) {
        model.addAttribute("key", "hello world");
        model.addAttribute("homeView", new HomeViewModel());
        return "index";
    }


}
