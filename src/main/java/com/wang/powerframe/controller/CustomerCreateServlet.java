package com.wang.powerframe.controller;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.powerframe.service.CustomerService;

/**
 * 创建客户
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
//@WebServlet("/customer_create")
@SuppressWarnings("serial")
public class CustomerCreateServlet extends HttpServlet {
	
	/**
	 * customerServlet
	 */
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	/**
	 * 进入 创建用户 页面
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/customer_create.jsp").forward(req, resp);
	}

	/**
	 * 处理 创建客户 请求
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		
		Enumeration<String> paramNames = req.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = paramNames.nextElement();  
            String[] paramValues = req.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                	fieldMap.put(paramName, paramValue);  
                }  
            }  
        }  
		
		boolean success = true;//customerService.createCustomer(fieldMap);
		if( success ) {
			resp.sendRedirect("/customer");
		}
	}

}
