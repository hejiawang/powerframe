package com.wang.powerframe.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wang.powerframe.helper.DatabaseHelper;
import com.wang.powerframe.model.Customer;
import com.wang.powerframe.util.CastUtil;

/**
 * 提供客户数据服务
 * @author HeJiawang
 * @date   2017.07.30
 */
public class CustomerService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);
	
	/**
	 * 获取客户列表
	 * @return
	 */
	public List<Customer> getCustomerList(){
		Connection conn = null;
		List<Customer> customerList = new ArrayList<Customer>();
		try {
			
			String sql = "select * from customer";
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				Customer customer = new Customer();
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("name"));
				customer.setContact(rs.getString("contact"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setEmail(rs.getString("email"));
				customer.setRemark(rs.getString("remark"));
				customerList.add(customer);
			}
		} catch (SQLException e) {
			LOGGER.error("execute sql failure", e);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		return customerList;
	}
	
	/**
	 * 获取客户
	 * @param id ID
	 * @return 客户
	 */
	public Customer getCustomer(long id) {
		Connection conn = null;
		Customer customer = new Customer();
		try {
			
			String sql = "select * from customer where id = " + id;
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while( rs.next() ) {
				customer.setId(rs.getLong("id"));
				customer.setName(rs.getString("name"));
				customer.setContact(rs.getString("contact"));
				customer.setTelephone(rs.getString("telephone"));
				customer.setEmail(rs.getString("email"));
				customer.setRemark(rs.getString("remark"));
			}
		} catch (SQLException e) {
			LOGGER.error("execute sql failure", e);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		return customer;
	}
	
	/**
	 * 创建客户
	 * @param fieldMap 客户信息
	 * @return
	 */
	public boolean createCustomer(Map<String, Object> fieldMap) {
		Connection conn = null;
		int result = 0;
		try {
			
			String sql = "insert into customer(name,contact,telephone,email,remark) values (?, ?, ?, ?, ?)";
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, CastUtil.castString(fieldMap.get("name")));
			stmt.setString(2, CastUtil.castString(fieldMap.get("contact")));
			stmt.setString(3, CastUtil.castString(fieldMap.get("telephone")));
			stmt.setString(4, CastUtil.castString(fieldMap.get("email")));
			stmt.setString(5, CastUtil.castString(fieldMap.get("remark")));
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("execute sql failure", e);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		
		return result >= 1 ;
	}
	
	/**
	 * 更新客户
	 * @param id ID
	 * @param fieldMap 客户信息
	 * @return
	 */
	public boolean updateCustomer( long id, Map<String, Object> fieldMap ) {
		Connection conn = null;
		int result = 0;
		try {
			
			String sql = "update customer set name =?, contact = ?, telephone = ?, email = ?, remark = ? where id = ?";
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, CastUtil.castString(fieldMap.get("name")));
			stmt.setString(2, CastUtil.castString(fieldMap.get("contact")));
			stmt.setString(3, CastUtil.castString(fieldMap.get("telephone")));
			stmt.setString(4, CastUtil.castString(fieldMap.get("email")));
			stmt.setString(5, CastUtil.castString(fieldMap.get("remark")));
			stmt.setString(6, CastUtil.castString(id));
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("execute sql failure", e);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		
		return result >= 1 ;
	}
	
	/**
	 * 删除客户
	 * @param id ID
	 * @return
	 */
	public boolean deleteCustomer( long id ) {
		Connection conn = null;
		int result = 0;
		try {
			
			String sql = "delete from customer where id = ?";
			conn = DatabaseHelper.getConnection();
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, CastUtil.castString(id));
			result = stmt.executeUpdate();
		} catch (SQLException e) {
			LOGGER.error("execute sql failure", e);
		} finally {
			DatabaseHelper.closeConnection(conn);
		}
		
		return result >= 1 ;
	}
}
