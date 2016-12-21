package com.zhu.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

//异常处理
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    /*
        * 返回json数据或者String数据：
        * 那么需要在方法上加上注解：@ResponseBody
        * 添加return即可。
        * 返回视图：
        * 定义一个ModelAndView即可，
        * 然后return;
        * 定义视图文件(比如：error.html,error.ftl,error.jsp);
        *
     */
    @ExceptionHandler(Throwable.class) /*Throwable 捕获切面异常*/
    public String Exception(HttpServletResponse response) throws Throwable {
        log.debug("捕获Throwable异常! ");

        return "index";
    }
    @ExceptionHandler(AccessDeniedException.class)//访问无权限
    public String handleAccessDeniedException(HttpServletResponse response) throws Exception {
        log.debug("捕获Exception异常! ");

        /*response.setStatus(HttpStatus.UNAUTHORIZED.value());
        response.sendRedirect("/index");*/
        return "index";
    }
    /*@ExceptionHandler(NullPointerException.class)//空指针
    public void handleException(HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.BANDWIDTH_LIMIT_EXCEEDED.value());
        response.sendRedirect("/index");
    }
    。。。。。
    */

}
