package com.yu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcUtils {
	private static final Connection conn;

	static {
		try {
			String driverClass = Config.get("druid.driverClassName");
			String connectionUrl = Config.get("druid.url");
			String username = Config.get("druid.username");
			String password = Config.get("druid.password");
			Class.forName(driverClass);
			conn = DriverManager.getConnection(connectionUrl, username, password);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static ResultSet query(String sql, List<Object> params) {
		System.out.println("sql: " + sql);
		System.out.println("params: " + params);
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			if(params != null) {
				for (int i = 0; i < params.size(); i++) {
					psmt.setObject(i+1, params.get(i));
				}
			}	
			return psmt.executeQuery();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public static void update(String sql, List<Object> params) {
		System.out.println("sql: " + sql);
		System.out.println("params: " + params);
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			if(params != null) {
				for (int i = 0; i < params.size(); i++) {
					psmt.setObject(i+1, params.get(i));
				}
			}	
			psmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public static void executeBatch(String sql, List<Object> params) {
		System.out.println("sql: " + sql);
		System.out.println("params: " + params);
		try {
			PreparedStatement psmt = conn.prepareStatement(sql);
			if(params != null) {
				for (int i = 0; i < params.size(); i++) {
					psmt.setObject(i+1, params.get(i));
				}
			}	
			psmt.addBatch();
			int[] arr = psmt.executeBatch();
			System.out.println(arr.length);
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
