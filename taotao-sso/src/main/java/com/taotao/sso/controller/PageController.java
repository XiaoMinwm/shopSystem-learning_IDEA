package com.taotao.sso.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by XiaoMin on 2016/7/9.
 */
@Controller
@RequestMapping("/page")
public class PageController {

    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/showLogin")
    public String login(String redirect, Model model) {
        model.addAttribute("redirect", redirect);
        return "login";
    }
}
