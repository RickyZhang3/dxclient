package org.com.dx.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

public class InvokeWithCookieTest {
	
	private static final String HTTP_URL_STRING = "http://localhost:8080/yxTag/getYxTags?pageNo=1&pageSize=10";
	
	public static void main(String[] args) throws IOException {
		
		//Set-Cookie: JSESSIONID=8CF3E7665B2B8C591451B982CA6BC554; Path=/; HttpOnly
		HttpURLConnection connection = null;
		// 创建远程url连接对象
        URL url = new URL(HTTP_URL_STRING);
        
        connection = (HttpURLConnection) url.openConnection();
        
        // 设置连接方式：GET
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json;charset=UTF-8");
        connection.setRequestProperty("Cookie", "JSESSIONID=8CF3E7665B2B8C591451B982CA6BC554; Path=/; HttpOnly");
        connection.setDoOutput(true);
        
        Reader in = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        for (int c; (c = in.read()) >= 0;) {
            sb.append((char)c);
        }
        in.close();
        connection.disconnect();
        
        String responseStr = sb.toString();
       System.out.println("RequestUtils - responseStr <== " + responseStr);
        if (StringUtils.isEmpty(responseStr)) {
            responseStr = "{}";
        }
        int statusCode = connection.getResponseCode();
        System.out.println("RequestUtils - statusCode <== " + statusCode);
        if (HttpServletResponse.SC_OK == statusCode) {
        	System.out.println("responseStr="+responseStr);
        	
//            Jack dataJson = (JSONObject) JSONObject.parse(responseStr);
//            map = new HashMap(dataJson);
        }
	}

}
