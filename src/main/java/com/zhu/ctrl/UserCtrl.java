package com.zhu.ctrl;

import com.zhu.constant.AppLicationConstant;
import com.zhu.context.RequestContext;
import com.zhu.mapper.MySqlMapper;
import com.zhu.util.QrcodeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by zhu on 2016/11/25.
 */
@Controller()
public class UserCtrl {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    MySqlMapper mySqlMapper;

    @RequestMapping(value = {"/home"}, method= RequestMethod.GET)
    public String home(){
        log.info("用户登陆IP ====="+ RequestContext.getClientIp());
        log.info("二维码登陆地址--------" + AppLicationConstant.QRCODELOGINURL);
        return "index";
    }

    @ResponseBody
    @RequestMapping(value = {"/doQrcodeLogin"})
    public String qrcodeLogin(HttpServletRequest request,
                               @RequestParam(required = true, value = "sessionId")String sessionId){
        if(QrcodeUtil.users.containsKey(sessionId)){
            //设置true 登陆成功
            QrcodeUtil.users.put(sessionId, true);
            return "LoginSuccess";
        }
        return "LoginFail";
    }


}
