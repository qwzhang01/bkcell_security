package com.bkcell.security.common.kit;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.resource.ResourceUrlProvider;

import javax.servlet.http.HttpServletRequest;

public class ServletKit {
    public static HttpServletRequest getRequest() {
        return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    }

    public static boolean isStaticFile(String uri) {
        ResourceUrlProvider resourceUrlProvider = SpringContextKit.getBean(ResourceUrlProvider.class);
        String staticUri = resourceUrlProvider.getForLookupPath(uri);
        return staticUri != null;
    }
}
