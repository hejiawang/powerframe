package com.wang.powerframe.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.wang.powerframe.util.PropsUtil;

/**
 * 数据库操作助手类
 * @author HeJiawang
 * @date   2017.07.31
 */
public final class DatabaseHelper {

	/**
	 * logger manager
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseHelper.class);
	
	/**
	 * dbutil
	 */
	private static final QueryRunner QUERY_RUNNER;
	
	/**
	 * 隔离线程的容器
	 */
	private static final ThreadLocal<Connection> CONNECTION_HOLDER ;
	
	private static final String DRIVER;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;
	
	static {
		QUERY_RUNNER = new QueryRunner();
		
		CONNECTION_HOLDER = new ThreadLocal<Connection>();
		
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
		Connection conn = CONNECTION_HOLDER.get();
		if( conn == null ) {
			
			try {
				conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			} catch (SQLException e) {
				LOGGER.error("get connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.set(conn);
			}
		}
		
		return conn;
	}
	
	/**
	 * 关闭数据库链接
	 * @param conn
	 */
	public  static void closeConnection( ) {
		Connection conn = CONNECTION_HOLDER.get();
		if( conn != null ) {
			
			try {
				conn.close();
			} catch (SQLException e) {
				LOGGER.error("close connection failure", e);
				throw new RuntimeException(e);
			} finally {
				CONNECTION_HOLDER.remove();
			}
		}	
	}
	
	/**
	 * 查询实体列表
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> List<T> queryEntityList( Class<T> entityClass, String sql, Object... params ) {
		List<T> entityList;
		try {
			Connection conn = getConnection();
			entityList = QUERY_RUNNER.query(conn, sql, new BeanListHandler<T>(entityClass), params); 
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		
		return entityList;
	}
	
	/**
	 * 查询实体
	 * @param entityClass
	 * @param sql
	 * @param params
	 * @return
	 */
	public static <T> T queryEntity( Class<T> entityClass, String sql, Object...params ) {
		T entity;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql, new BeanHandler<T>(entityClass), params); 
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		
		return entity;
	}
	
	/**
	 * 执行查询语句
	 * @param sql
	 * @param params
	 * @return
	 */
	public static List<Map<String, Object>> executeQuery( String sql, Object...params ) {
		List<Map<String, Object>> entity;
		try {
			Connection conn = getConnection();
			entity = QUERY_RUNNER.query(conn, sql, new MapListHandler(), params); 
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		
		return entity;
	}
	
	/**
	 * 执行更新语句 ( 包括 update, insert, delete ) 
	 * @param sql
	 * @param params
	 * @return 受影响的行数
	 */
	public static int executeUpdate( String sql, Object...params ) {
		int rows = 0;
		try {
			Connection conn = getConnection();
			rows = QUERY_RUNNER.update(conn, sql, params); 
		} catch (SQLException e) {
			LOGGER.error("query entity list failure", e);
			throw new RuntimeException(e);
		} finally {
			closeConnection();
		}
		
		return rows;
	}
}
