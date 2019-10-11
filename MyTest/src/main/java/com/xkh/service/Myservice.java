package com.xkh.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Auther: xukh
 * @Date: 2019/10/11 14:59
 * @Description:
 */
@Service
public class Myservice {
    private int count=0;
    @Scheduled(cron = "* * * * * ?")
    public void start(){
        System.out.println("[" + Thread.currentThread().getName() + "]" + "this is scheduler task runing  "+(count++));

    }


}
