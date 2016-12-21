package com.zhu.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * 应用上下文
 */
public class AppContext {

	@Autowired
	protected Properties properties;

	protected static AppContext instance;

	public AppContext() {
		if (instance == null) {
			instance = this;
		}
	}

	public static String getProperty(String key) {
		return instance.properties.getProperty(key);
	}
	//二维码登陆的url
	public static String getQrcodeLoginUrl(){
		return getProperty("qrcodelogin.url");
	}

	// 接口控制时间间隔
	public static Long getInterfaceCallControllInterval() {
		return Long.valueOf(getProperty("interface.call.control.interval"));
	}
	// 接口控制时间间隔
	public static String getInterfaceCallControllExclude() {
		return getProperty("Interface.call.control.exclude");
	}

}
