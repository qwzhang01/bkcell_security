package com.bkcell.security.web.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.event.ApplicationContextEvent;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class EventKit {
    private static EventKit eventKit;
    @Autowired
    private EventPbulish pbulish;

    @PostConstruct
    public void init() {
        eventKit = this;
        eventKit.pbulish = this.pbulish;
    }

    public static void post(ApplicationEvent event){
        eventKit.pbulish.publish(event);
    }
}
