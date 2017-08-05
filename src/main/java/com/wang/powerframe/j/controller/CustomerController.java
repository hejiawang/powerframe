package com.wang.powerframe.j.controller;

import java.util.List;
import java.util.Map;

import com.wang.powerframe.model.Customer;
import com.wang.powerframe.service.CustomerService;
import com.wang.powerframeJ.annotation.Action;
import com.wang.powerframeJ.annotation.Controller;
import com.wang.powerframeJ.annotation.Inject;
import com.wang.powerframeJ.bean.Data;
import com.wang.powerframeJ.bean.Param;
import com.wang.powerframeJ.bean.View;

/**
 * 处理客户管理相关请求
 * @author HeJiawang
 * @date   2017.08.05
 */
@Controller
public class CustomerController {

	@Inject
	private CustomerService customerService;
	
	/**
	 * 进入 客户列表 界面
	 * @param param
	 * @return
	 */
	@Action("get:/customer")
	public View index( Param param ) {
		List<Customer> customerList = customerService.getCustomerList();
		return new View("customer_list.jsp").addModel("customerList", customerList);
	}
	
	/**
	 * 显示客户基本信息
	 * @param param
	 * @return
	 */
	@Action("get:/customer_show")
	public View show( Param param ) {
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_showt.jsp").addModel("customer", customer);
	}
	
	/**
	 * 进入 创建客户 界面
	 * @param param
	 * @return
	 */
	@Action("get:/customer_create")
	public View create( Param param ) {
		return new View("customer_create.jsp");
	}
	
	/**
	 * 处理 创建客户 请求
	 * @param param
	 * @return
	 */
	@Action("post:/customer_create")
	public Data createSubmit( Param param ) {
		Map<String, Object> fieldMap = param.getMap();
		boolean success = customerService.createCustomer(fieldMap);
		return new Data(success);
	}
	
	/**
	 * 进入 编辑客户 界面
	 * @param param
	 * @return
	 */
	@Action("get:/customer_edit")
	public View edit( Param param ) {
		long id = param.getLong("id");
		Customer customer = customerService.getCustomer(id);
		return new View("customer_edit.jsp").addModel("customer", customer);
	}
	
	/**
	 * 处理 编辑客户 请求
	 * @param param
	 * @return
	 */
	@Action("put:/customer_edit")
	public Data editSubmit( Param param ) {
		long id = param.getLong("id");
		Map<String, Object> fieldMap = param.getMap();
		boolean success = customerService.updateCustomer(id,fieldMap);
		return new Data(success);
	}
	
	/**
	 * 处理 删除客户 请求
	 * @param param
	 * @return
	 */
	@Action("delete:/customer_edit")
	public Data delete( Param param ) {
		long id = param.getLong("id");
		boolean success = customerService.deleteCustomer(id);
		return new Data(success);
	}
}
