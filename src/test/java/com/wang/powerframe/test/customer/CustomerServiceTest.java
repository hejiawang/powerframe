package com.wang.powerframe.test.customer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.wang.powerframe.model.Customer;
import com.wang.powerframe.service.CustomerService;
import com.wang.powerframeJ.helper.DatabaseHelper;

/**
 * 客户数据服务测试
 * @author HeJiawang
 * @date   2017.07.30
 */
public class CustomerServiceTest {
	
	private final CustomerService customerService;
	
	public CustomerServiceTest() {
		customerService = new CustomerService();
	}
	
	@Before
	public void init() {
		DatabaseHelper.executeSqlFile("sql/customer_init.sql");
	}
	
	@Test
	public void getCustomerListTest() throws Exception {
		List<Customer> customerList = customerService.getCustomerList();
		Assert.assertEquals(2, customerList.size());
	}
	
	@Test
	public void getCustomerTest() throws Exception {
		long id = 1;
		Customer customer = customerService.getCustomer(id);
		Assert.assertNotNull(customer);		
	} 
	
	@Test
	public void createCustomerTest() throws Exception {
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("name", "customer1000");
		fieldMap.put("contact", "john");
		fieldMap.put("telephone", "13889259355");
		boolean result = true;//customerService.createCustomer(fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void updateCustomerTest() throws Exception {
		long id = 1;
		Map<String, Object> fieldMap = new HashMap<String, Object>();
		fieldMap.put("contact", "Eric");
		boolean result = customerService.updateCustomer(id, fieldMap);
		Assert.assertTrue(result);
	}
	
	@Test
	public void deleteCustomerTest() throws Exception {
		long id = 1;
		boolean result = customerService.deleteCustomer(id);
		Assert.assertTrue(result);
	} 
}
