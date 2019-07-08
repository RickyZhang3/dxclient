package org.com.dx.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;

public class LoginTest {
	private static final String HTTP_LOGIN_URL_STRING = "http://localhost:8080/login";
	
	public static void main(String[] args) throws IOException {
		HttpURLConnection connection = null;
		// 创建远程url连接对象
        URL url = new URL(HTTP_LOGIN_URL_STRING);
        
        connection = (HttpURLConnection) url.openConnection();
        
//        Map map = new HashMap();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", "123");
        params.put("password", "123");
        // post参数
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> param : params.entrySet()) {
            if (postData.length() != 0) {
                postData.append('&');
            }
            postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
        }
        System.out.println("请求参数:"+postData.toString());
        byte[] postDataBytes = postData.toString().getBytes("UTF-8");
        
        // 设置连接方式：POST
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setDoOutput(true);
        
        connection.getOutputStream().write(postDataBytes);
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
        	//responseStr={"code":"0","msg":"操作成功","data":{"JSESSIONID":"A8140D83CECB967876BF124E92C4A37C"}}
        	//登录成功返回结果示例：{"code":"0","msg":"操作成功","data":{"JSESSIONID":"A8140D83CECB967876BF124E92C4A37C"}}
        	//登录失败返回结果示例：{"code":"1","msg":"用户未登录","data":"请先登录"}
        	//登录成功返回结果中的JSESSIONID是用户凭证，之后在请求其他业务接口时进行设置，参照：InvokeWithCookieTest
        }
	}
}
