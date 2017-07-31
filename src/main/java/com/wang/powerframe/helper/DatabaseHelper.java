package com.wang.powerframe.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wang.powerframe.util.PropsUtil;

/**
 * 数据库操作助手类
 * @author HeJiawang
 * @date   2017.07.31
 */
public final class DatabaseHelper {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	static {
		Properties conf = PropsUtil.loadProps("config.properties");
		DRIVER = conf.getProperty("jdbc.driver");
		URL = conf.getProperty("jdbc.url");
		USERNAME = conf.getProperty("jdbc.username");
		PASSWORD = conf.getProperty("jdbc.password");
		
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			LOGGER.error("can not load jdbc driver", e);
		}
	}
	
	/**
	 * 获取数据库链接
	 * @return
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			LOGGER.error("get connection failure", e);
		}
		
		return conn;
	}
	
	/**
	 * 关闭数据库链接
	 * @param conn
	 */
	public  static void closeConnection( Connection conn ) {
		if( conn != null ) {
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure", e);
			}
		}	
	}
}
