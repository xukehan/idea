package com.ace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Auther: xukh
 * @Date: 2019/10/11 11:07
 * @Description:
 */
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class OrderQueryMoudleApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderQueryMoudleApplication.class,args);
    }
}
