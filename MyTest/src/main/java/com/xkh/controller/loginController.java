package com.xkh.controller;

import com.xkh.service.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Auther: xukh
 * @Date: 2019/10/11 11:16
 * @Description:
 */
@Controller
public class loginController {

    @Autowired
    private Myservice myservice;

    @RequestMapping("/login")
    @ResponseBody
    public String login(){
        myservice.start();
        System.out.println("我太难了");
        return "我太难了";

    }
}
