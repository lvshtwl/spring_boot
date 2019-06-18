package com.sqlva.springboot.aop;

import com.mongodb.BasicDBObject;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;


@Component
@Order(1)
@Aspect
public class WebLogAspect {
    private Logger logger = Logger.getLogger("mongodb");
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    @Pointcut("execution(public * com.sqlva.springboot.controller..*.*(..))")
    public void webLog() {
    }

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());

        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

//        // 记录下请求内容
//        logger.info("URL : " + request.getRequestURL().toString());
//        logger.info("HTTP_METHOD : " + request.getMethod());
//        logger.info("IP : " + request.getRemoteAddr());
//        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        // 获取要记录的日志内容
        BasicDBObject logInfo = getBasicDBObject(request, joinPoint);
        logger.info(logInfo);
    }
    private BasicDBObject getBasicDBObject(HttpServletRequest request, JoinPoint joinPoint) {
        // 基本信息
        BasicDBObject r = new BasicDBObject();
        r.append("requestURL", request.getRequestURL().toString());
        r.append("requestURI", request.getRequestURI());
        r.append("queryString", request.getQueryString());
        r.append("remoteAddr", request.getRemoteAddr());
        r.append("remoteHost", request.getRemoteHost());
        r.append("remotePort", request.getRemotePort());
        r.append("localAddr", request.getLocalAddr());
        r.append("localName", request.getLocalName());
        r.append("method", request.getMethod());
        r.append("headers", getHeadersInfo(request));
        r.append("parameters", request.getParameterMap());
        r.append("classMethod", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        r.append("args", Arrays.toString(joinPoint.getArgs()));
        return r;
    }
    private Map<String, String> getHeadersInfo(HttpServletRequest request) {
        Map<String, String> map = new HashMap<>();
        Enumeration headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }
        return map;
    }
    @AfterReturning(returning = "rete",argNames = "rete",pointcut = "webLog()")
    public void afterReturning(Object rete) {
        BasicDBObject r = new BasicDBObject();
        r.append("RESPONSE",rete);
        r.append("SPEND TIME",(System.currentTimeMillis() - startTime.get()));
        logger.info(r);
//        // 处理完请求，返回内容
//        logger.info("RESPONSE : " + rete);
//        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
