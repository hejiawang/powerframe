package com.wang.powerframe.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wang.powerframe.helper.DatabaseHelper;
import com.wang.powerframe.model.Customer;
import com.wang.powerframeJ.annotation.Service;

/**
 * 提供客户数据服务
 * @author HeJiawang
 * @date   2017.07.30
 */
@SuppressWarnings("unused")
@Service
public class CustomerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	/**
	 * 获取客户列表
	 * @return
	 */
	public List<Customer> getCustomerList(){
		String sql = "select * from customer";
		return DatabaseHelper.queryEntityList(Customer.class, sql);
	}
	
	/**
	 * 获取客户
	 * @param id ID
	 * @return 客户
	 */
	public Customer getCustomer(long id) {
		String sql = "select * from customer where id = " + id;
		return DatabaseHelper.queryEntity(Customer.class, sql);
	}
	
	/**
	 * 创建客户
	 * @param fieldMap 客户信息
	 * @return
	 */
	public boolean createCustomer(Map<String, Object> fieldMap) {
		return DatabaseHelper.insertEntity(Customer.class, fieldMap);
	}
	
	/**
	 * 更新客户
	 * @param id ID
	 * @param fieldMap 客户信息
	 * @return
	 */
	public boolean updateCustomer( long id, Map<String, Object> fieldMap ) {
		return DatabaseHelper.updateEntity(Customer.class, id, fieldMap);
	}
	
	/**
	 * 删除客户
	 * @param id ID
	 * @return
	 */
	public boolean deleteCustomer( long id ) {
		return DatabaseHelper.deleteEntity(Customer.class, id);
	}
}
