package com.wang.powerframe.util;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 属性文件工具类 
 * @author HeJiawang
 * @date   2017.07.30
 */
public final class PropsUtil {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);
	
	/**
	 * 加载属性文件
	 * @param fileName 文件名
	 * @return
	 */
	public static Properties loadProps( String fileName ) {
		Properties props = null;
		InputStream is = null;
		
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);
			if( is == null ) throw new FileNotFoundException(fileName + " file is not found");
			
			props = new Properties();
			props.load(is);
		} catch (Exception e) {
			LOGGER.error("losd properties file failure", e);
		} finally {
			if( is != null ) {
				try {
					is.close();
				} catch (Exception e) {
					LOGGER.error("close input stream failure", e);
				}
			}
		}
		
		return props;
	}
	
	/**
	 * 获取字符型属性(默认值为空字符串)
	 * @param props Properties对象
	 * @param key KEY
	 * @return
	 */
	public static String getString( Properties props, String key ) {
		return getString( props, key, "" );
	}
	
	/**
	 * 获取字符型属性(可指定默认值)
	 * @param props Properties对象
	 * @param key KEY
	 * @param defaultValue 默认值
	 * @return
	 */
	public static String getString( Properties props, String key, String defaultValue ) {
		String value = defaultValue;
		if( props.containsKey(key) ) {
			value = props.getProperty(key);
		}
		return value;
	}
	
	/**
	 * 获取数值型属性(默认值为0)
	 * @param props Properties对象
	 * @param key KEY
	 * @return
	 */
	public static int getInt( Properties props, String key ) {
		return getInt( props, key, 0 );
	}
	
	/**
	 * 获取数值型属性(可指定默认值)
	 * @param props Properties对象
	 * @param key KEY
	 * @param defaultValue 默认值
	 * @return
	 */
	public static int getInt( Properties props, String key, int defaultValue ) {
		int value = defaultValue;
		if( props.containsKey(key) ) {
			//value = props.getProperty(key);
		}
		return value;
	}
}