package com.bkcell.security.web.event;

import cn.hutool.core.thread.ThreadUtil;
import com.bkcell.security.common.kit.ThreadKit;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class TestListener implements ApplicationListener<TestEvent> {

    @Async
    @Override
    public void onApplicationEvent(TestEvent testEvent) {
        Object source = testEvent.getSource();
        System.out.println(source.toString());
        Integer appId = ThreadKit.getAppId();
        System.out.println(appId);

    }
}