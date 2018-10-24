package com.yu.jdbc;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;

public class Config {

	private static final Map<String, String> properties = new HashMap<String, String>();

	static {
		try {
			Properties pps = new Properties();
			pps.load(Config.class.getClassLoader().getResourceAsStream("appSettings.properties"));
			for (Entry<Object, Object> entry : pps.entrySet()) {
				properties.put(entry.getKey().toString().trim(), entry.getValue().toString().trim());
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String get(String name) {
		return properties.get(name);
	}
}
