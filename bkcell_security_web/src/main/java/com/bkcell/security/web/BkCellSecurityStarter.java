package com.bkcell.security.web;


import com.bkcell.security.common.listener.RequestUrlListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bkcell.security")
public class BkCellSecurityStarter {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BkCellSecurityStarter.class);
        springApplication.addListeners(new RequestUrlListener());
        springApplication.run(args);
    }
}