package com.wang.powerframe.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 创建客户
 * @author HeJiawang
 * @date   2017.07.30
 *
 */
@WebServlet("/customer_create")
@SuppressWarnings("serial")
public class CustomerCreateServlet extends HttpServlet {

	/**
	 * 进入 创建用户 页面
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * 处理 创建客户 请求
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
