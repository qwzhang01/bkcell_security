package com.bkcell.security.dict;

import com.bkcell.security.common.listener.RequestUrlListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.bkcell.security")
public class BkCellSecurityDictStarter {

    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(BkCellSecurityDictStarter.class);
        springApplication.addListeners(new RequestUrlListener());
        springApplication.run(args);
    }
}