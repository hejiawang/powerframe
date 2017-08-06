package com.wang.powerframe.j.aspect;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wang.powerframeJ.annotation.Aspect;
import com.wang.powerframeJ.annotation.Controller;
import com.wang.powerframeJ.proxy.AspectProxy;

/**
 * 拦截controller所有方法
 * @author HeJiawang
 * @date   2017.08.06
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);
	
	private long begin;

	@Override
	public void before(Class<?> cls, Method method, Object[] params) throws Throwable {
		LOGGER.debug("------------begin--------------");
		LOGGER.debug(String.format("class: %s", cls.getName()));
		LOGGER.debug(String.format("method: %s", method.getName()));
		begin = System.currentTimeMillis();
	}

	@Override
	public void after(Class<?> cls, Method method, Object[] params, Object result) throws Throwable {
		LOGGER.debug(String.format("time: %s", System.currentTimeMillis() - begin));
		LOGGER.debug("------------end--------------");
	}
	
}
