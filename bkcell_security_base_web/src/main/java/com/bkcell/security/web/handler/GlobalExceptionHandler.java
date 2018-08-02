package com.bkcell.security.web.handler;

import cn.hutool.core.exceptions.ExceptionUtil;
import com.bkcell.security.common.aop.TokenContract;
import com.bkcell.security.common.entity.R;
import com.bkcell.security.common.exception.RepeatSubmitExcepion;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 全局异常处理器
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(TokenContract.class);

    /**
     * 默认异常处理器
     *
     * @param request
     * @param e
     */
    @ExceptionHandler(value = Exception.class)
    public void defaultErrorHandler(HttpServletRequest request, Exception e) {
        logger.error(ExceptionUtil.stacktraceToString(e));
    }

    /**
     * shiro未授权异常处理方法，解决shiro自定义配置不起作用
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = UnauthorizedException.class)
    public ModelAndView unauthorizedExceptionHandler(HttpServletRequest request, Exception e) {
        ModelAndView mv = new ModelAndView("common/403.html");
        return mv;
    }

    /**
     * 表单重复提交
     *
     * @param request
     * @param e
     * @return
     */
    @ExceptionHandler(value = RepeatSubmitExcepion.class)
    public void repeatSubmitExcepionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
        printTips(response, "表单重复提交");
    }

    private void printTips(HttpServletResponse response, String tips) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(R.error(tips));
            response.setContentType("application/json;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.print(json);
        } catch (IOException e) {
            logger.error(ExceptionUtil.stacktraceToString(e));
        }
    }
}