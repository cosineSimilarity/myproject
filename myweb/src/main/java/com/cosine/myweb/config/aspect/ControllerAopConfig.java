package com.cosine.myweb.config.aspect;

import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Cosine
 * @version 1.0.0
 * @title ControllerAopConfig
 * @create 2023/6/29
 * @description 接口调用切面
 */
@Aspect
@Configuration
@Slf4j
public class ControllerAopConfig {

    @Pointcut("execution(public * com.cosine.myweb.controller..*.*(..))")
    public void pointCut(){
    }

    @Around("pointCut()")
    public Object process(ProceedingJoinPoint point) throws Throwable {
        Object returnValue = null;
        List<Object> paramList = new ArrayList<>();
        //获取请求信息
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if(servletRequestAttributes==null){
            return point.proceed();
        }
        HttpServletRequest request = servletRequestAttributes.getRequest();
        Object[] args = point.getArgs();
        //过滤HttpServlet，获取请求参数
        for(Object obj : args){
            if(obj instanceof HttpServletRequest){
                continue;
            }
            if(obj instanceof HttpServletResponse){
                continue;
            }
            paramList.add(obj);
        }
        String apiUrl = request.getRequestURI();
        String[] decTypeNameArr = point.getSignature().getDeclaringTypeName().split("\\.");
        StringBuilder decTypeNameStr = new StringBuilder("");
        for(int i=0;i<decTypeNameArr.length;i++){
            if(i==0||i==1||i==2){
                decTypeNameStr.append(decTypeNameArr[i].charAt(0)).append(".");
            }else {
                decTypeNameStr.append(decTypeNameArr[i]).append(".");
            }
        }
        String requestRoute = decTypeNameStr.toString()+point.getSignature().getName();
        Map<String,String[]> paramMap = request.getParameterMap();
        String requestParamJsonStr="";
        if(paramMap.size()!=0){
            requestParamJsonStr = JSON.toJSONString(request.getParameterMap());
        }else {
            requestParamJsonStr = JSON.toJSONString(paramList);
        }
        log.info("调用接口({})开始，请求路径：{}",apiUrl,requestRoute);
        log.info("调用接口({})入参：{}", apiUrl,requestParamJsonStr);
        long startTime = System.currentTimeMillis();
        //执行方法
        returnValue = point.proceed(point.getArgs());

        long endTime = System.currentTimeMillis();
        long useTime = endTime-startTime;
        log.info("调用接口({})出参：{}",apiUrl,returnValue);
        log.info("调用接口({})结束，用时：{}ms，请求路径：{}",apiUrl,useTime,requestRoute);
        return returnValue;
    }
}
