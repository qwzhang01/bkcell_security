package com.bkcell.security.common.aop;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.json.JSONUtil;
import com.bkcell.security.common.annotation.Token;
import com.bkcell.security.common.exception.RepeatSubmitExcepion;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Aspect
@Component
public class TokenContract {
    private static final Logger logger = LoggerFactory.getLogger(TokenContract.class);

    /**
     * 打印控制器请求详情，方便调试
     *
     * @param joinPoint
     */
    @Before(value = "@within(org.springframework.stereotype.Controller)")
    public void devLog(final JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        logger.info("url={}", request.getRequestURI());
        logger.info("method={}", request.getMethod());
        logger.info("ip={}", request.getRemoteAddr());
        logger.info("class={} and method name = {}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info("参数={}", JSONUtil.toJsonPrettyStr(joinPoint.getArgs()));
    }

    /**
     * 添加防止表单重复提交拦截器
     *
     * @param joinPoint
     * @param token
     */
    @Before(value = "@within(org.springframework.stereotype.Controller) && @annotation(token)")
    public void testToken(final JoinPoint joinPoint, Token token) {
        if (token != null) {
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            boolean needSaveSession = token.save();
            if (needSaveSession) {
                HttpSession session = request.getSession();
                session.removeAttribute("_formToken_");
                String uuid = RandomUtil.randomUUID();
                session.setAttribute("_formToken_", uuid);
            }

            boolean needRemoveSession = token.remove();
            if (needRemoveSession) {
                if (isRepeatSubmit(request)) {
                    throw new RepeatSubmitExcepion();
                }
                HttpSession session = request.getSession(false);
                if (session != null) {
                    session.removeAttribute("_formToken_");
                }
            }
        }
    }

    private boolean isRepeatSubmit(HttpServletRequest request) {
        String serverToken = (String) request.getSession(false).getAttribute("_formToken_");
        if (serverToken == null) {
            return true;
        }
        String clinetToken = request.getParameter("_formToken_");
        if (clinetToken == null || clinetToken.equals("")) {
            return true;
        }
        if (!serverToken.equals(clinetToken)) {
            return true;
        }
        return false;
    }
}