package com.bkcell.security.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Component
public class NavInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println(request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (response.getStatus() == 404) {
            modelAndView.setViewName("redirect:/login");
        } else if (handler instanceof HandlerMethod && "GET".equalsIgnoreCase(request.getMethod())) {
            if (modelAndView != null) {
                Map<String, Object> model = modelAndView.getModel();
                if (model != null) {
                    Object current = model.get("current");
                    if (current != null) {
                        return;
                    }
                }
                //设置高亮当前目录
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Object controller = handlerMethod.getBean();
                String cotrollerName = controller.getClass().getSimpleName().toLowerCase().replace("controller", "");
                if (cotrollerName.contains("$")) {
                    cotrollerName = cotrollerName.substring(0, cotrollerName.indexOf("$"));
                }
                modelAndView.addObject("current", cotrollerName);
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}