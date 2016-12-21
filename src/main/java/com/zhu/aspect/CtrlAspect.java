package com.zhu.aspect;

import com.google.gson.Gson;
import com.zhu.constant.EmailConstant;
import com.zhu.context.RequestContext;
import com.zhu.util.DateUtil;
import com.zhu.util.JsonUtil;
import com.zhu.util.email.SimpleMailSender;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.nio.file.AccessDeniedException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhu on 2016/11/4.
 */
@Aspect
@Configuration
public class CtrlAspect {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String requestPath = null ; // 请求地址
    private String userName = null ; // 用户名
    private Map<?,?> inputParamMap = null ; // 传入参数
    private Map<String, Object> outputParamMap = null; // 存放输出结果

    /*各通知类型执行顺序
    同一个类： Around的前通知-->Before通知-->Around的后通知-->After通知-->AfterReturn通知（或AfterThrowing通知）*/
    /**
     *
     * @Title：doAround
     * @Description: 环绕触发
     * @author zhu
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("execution(* com.zhu.ctrl..*.*(..))")
    public Object doAround(ProceedingJoinPoint pjp) throws Exception {
        Date startTime = new Date();
        log.info("请求开始时间===="+ DateUtil.format(startTime,DateUtil.PATTERN_NORMAL_YEAR2MILLISECOND));
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes)ra;
        HttpServletRequest request = sra.getRequest();

        // 获取输入参数
        inputParamMap = request.getParameterMap();
        // 获取请求地址
        requestPath = request.getRequestURI();
        String userIpAddr = getUserIpAddr(request);

        RequestContext.setClientIp(userIpAddr);

        log.info("请求路径====="+requestPath+":请求ip===="+userIpAddr);
        log.info("输入参数 = " + JsonUtil.toString(inputParamMap));


        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        outputParamMap = new HashMap<String, Object>();
        Object result = null;// result的值就是被拦截方法的返回值
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            /*new Thread(new Runnable() {
                @Override
                public void run() {
                    String errContent = "系统错误原因:"+throwable.getMessage()+"     "
                            +  "   所在类:"+throwable.getStackTrace()[0].getClassName()
                            +  "   方法名:"+throwable.getStackTrace()[0].getMethodName()
                            +  "   行号:"+String.valueOf(throwable.getStackTrace()[0].getLineNumber());
                    SimpleMailSender sms = new SimpleMailSender();
                    sms.sendTextMail(EmailConstant.ToMail,"系统错误!",errContent);//发送文体格式
                }
            }).start();//start  等待cpu,可跳过直接执行下面的代码   run 不可跳过*/

            throwable.printStackTrace();


        }
        outputParamMap.put("result", result);
        log.debug("输出参数 = " + JsonUtil.toString(outputParamMap));

        Date endTime = new Date();
        log.info("请求结束时间====" + DateUtil.format(endTime, DateUtil.PATTERN_NORMAL_YEAR2MILLISECOND));
        log.info("耗时(毫秒):"+(endTime.getTime() - startTime.getTime()));
        return result;
    }
    /**
     * 获取用户ip
     */
    public String getUserIpAddr(HttpServletRequest request) {

        String ip = request.getHeader("x-forwarded-for");
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if(ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
