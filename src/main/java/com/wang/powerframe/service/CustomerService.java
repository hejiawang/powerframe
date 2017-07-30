package com.wang.powerframe.service;

import java.util.List;
import java.util.Map;

import com.wang.powerframe.model.Customer;

/**
 * 提供客户数据服务
 * @author HeJiawang
 * @date   2017.07.30
 */
public class CustomerService {
	
	/**
	 * 获取客户列表
	 * @return
	 */
	public List<Customer> getCustomerList(){
		// TODO
		return null;
	}
	
	/**
	 * 获取客户
	 * @param id ID
	 * @return 客户
	 */
	public Customer getCustomer(long id) {
		// TODO
		return null;
	}
	
	/**
	 * 创建客户
	 * @param fieldMap 客户信息
	 * @return
	 */
	public boolean createCustomer(Map<String, Object> fieldMap) {
		// TODO
		return false;
	}
	
	/**
	 * 更新客户
	 * @param id ID
	 * @param fieldMap 客户信息
	 * @return
	 */
	public boolean updateCustomer( long id, Map<String, Object> fieldMap ) {
		// TODO
		return false;
	}
	
	/**
	 * 删除客户
	 * @param id ID
	 * @return
	 */
	public boolean deleteCustomer( long id ) {
		// TODO
		return false;
	}
}
