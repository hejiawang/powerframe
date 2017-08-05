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
 * 进入 查看客户 界面
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
//@WebServlet("/customer_show")
@SuppressWarnings("serial")
public class CustomerShowServlet extends HttpServlet {

	/**
	 * customerServlet
	 */
	private CustomerService customerService;
	
	@Override
	public void init() throws ServletException {
		customerService = new CustomerService();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		long id = CastUtil.castLong(req.getParameter("id"));
		
		Customer customer = customerService.getCustomer(id);
		req.setAttribute("customer", customer);
		req.getRequestDispatcher("/WEB-INF/view/customer_view.jsp").forward(req, resp);
	}

}
