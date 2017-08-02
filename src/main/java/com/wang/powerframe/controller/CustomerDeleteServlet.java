package com.wang.powerframe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.powerframe.service.CustomerService;
import com.wang.powerframe.util.CastUtil;

/**
 * 删除客户
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
@WebServlet("/customer_delete")
@SuppressWarnings("serial")
public class CustomerDeleteServlet extends HttpServlet {

	/**
	 * customerServlet
	 */
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = CastUtil.castLong(req.getParameter("id"));
		boolean success = customerService.deleteCustomer(id);
		if( success ) {
			resp.sendRedirect("/customer");
		}
	}

}
