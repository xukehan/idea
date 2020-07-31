package com.xkh.controller;

import com.xkh.pojo.CstCustomer;
import com.xkh.service.Myservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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

    @Autowired
   private RedisTemplate redisTemplate;

    @RequestMapping("/login")
    @ResponseBody
    public String login(){
        myservice.updateCustomer();
        //System.out.println(cstCustomer);
        return "我太难了";

    }
}
