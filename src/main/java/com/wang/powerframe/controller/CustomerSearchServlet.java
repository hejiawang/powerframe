package com.wang.powerframe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.powerframe.model.Customer;
import com.wang.powerframe.service.CustomerService;
import com.wang.powerframe.util.CastUtil;

/**
 * 查询客户
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
//@WebServlet("/customer_search")
@SuppressWarnings("serial")
public class CustomerSearchServlet extends HttpServlet {

	/**
	 * customerServlet
	 */
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = CastUtil.castLong(req.getParameter("id"));
		
		Customer customer = customerService.getCustomer(id);
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("/WEB-INF/view/customer_view.jsp").forward(req, resp);
	}
}
