package com.wang.powerframe.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wang.powerframe.model.Customer;
import com.wang.powerframe.service.CustomerService;

/**
 * 进入 客户列表 界面
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
@WebServlet("/customer")
@SuppressWarnings("serial")
public class CustomerServlet extends HttpServlet {

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
		List<Customer> customerList = customerService.getCustomerList();
		req.setAttribute("customerList", customerList);
		req.getRequestDispatcher("/WEB-INF/view/customer_list.jsp").forward(req, resp);
	}
}
