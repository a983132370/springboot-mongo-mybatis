package com.zhu.ctrl;

import com.zhu.biz.UserBiz;
import com.zhu.constant.AppLicationConstant;
import com.zhu.context.RequestContext;
import com.zhu.mapper.MySqlMapper;
import com.zhu.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhu on 2016/11/3.
 */
@Controller
public class TestCtrl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserBiz userBiz;

    @RequestMapping(value = {"/testWebSocket"}, method= RequestMethod.GET)
    public String testWebSocket(){
        return "websocket";
    }

    @RequestMapping(value = {"/test","/"}, method= RequestMethod.GET)
    public String test(){
        return "index";
    }

    @RequestMapping(value = {"/testRedis"}, method= RequestMethod.GET)
    public String testRedis(){
        return "index";
    }

    @RequestMapping(value = {"/testMysql"}, method= RequestMethod.GET)
    public String testmysql(){

        return "index";
    }
    @ResponseBody
    @RequestMapping(value = {"/testMongo"}, method= RequestMethod.GET)
    public String testMongo(){
        
        return JsonUtil.toString(userBiz.getUser());
    }

}
