package com.yu.toolMethod;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

public class CallHttpUrlData {
	private static Logger logger = Logger.getLogger(CallHttpUrlData.class);

	public static void main(String[] args) {
		String url = "http://api.map.baidu.com/telematics/v3/weather?location=嘉兴&output=json&ak=5slgyqGDENN7Sy7pw29IUvrZ";
		
		getHttpUrlData(url);
	}
	
	private static String getHttpUrlData(String url) {
        BufferedReader in = null;
        URLConnection conn = null;
        StringBuffer sb = new StringBuffer();
        logger.debug("[getHttpUrlData] start...");
        try {
            URL httpURL = new URL(url);
            // 打开和URL之间的连接
            conn = httpURL.openConnection();
            // 设置通用的请求属性
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setConnectTimeout(100000);
            conn.setReadTimeout(100000);
            // 建立实际的连接
            conn.connect();
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                line = new String(line.getBytes(), "utf-8");
                sb.append(line);
            }
            logger.info(sb);
            System.out.println(sb.toString());
        } catch (Exception e) {
            logger.error(e);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception ex) {
                logger.error(ex);
            }
        }
        logger.debug("[getHttpUrlData] end.");
        return sb.toString();
      }
}
