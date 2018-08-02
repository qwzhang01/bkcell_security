package com.bkcell.security.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEvent;
import org.springframework.stereotype.Component;

@Component
public class EventPbulish {

    @Autowired
    private ApplicationContext context;

    public void publish(ApplicationEvent event) {
        context.publishEvent(event);
    }

}