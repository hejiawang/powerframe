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
import com.wang.powerframe.util.CastUtil;

/**
 * 修改客户
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
@WebServlet("/customer_edit")
@SuppressWarnings("serial")
public class CustomerEditServlet extends HttpServlet {

	/**
	 * customerServlet
	 */
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	/**
	 * 进入 修改客户 页面
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/view/customer_edit.jsp").forward(req, resp);
	}

	/**
	 * 处理 修改客户 请求
	 */
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		
		Enumeration<String> paramNames = req.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = paramNames.nextElement();  
            String[] paramValues = req.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0 && !paramValue.equals("id")) {  
                	fieldMap.put(paramName, paramValue);  
                }  
            }  
        }  
        long id = CastUtil.castLong(req.getParameter("id"));
        
		boolean success = customerService.updateCustomer(id, fieldMap);
		if( success ) {
			resp.sendRedirect("/customer");
		}
	}
	
}
