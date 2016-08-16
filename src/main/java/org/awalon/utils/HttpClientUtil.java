package org.awalon.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.SimpleHttpConnectionManager;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class HttpClientUtil {

	private static final Logger LOG = LoggerFactory.getLogger(HttpClientUtil.class);
    public static final String HTTP_POST = "POST";
    public static final String HTTP_GET = "GET";
	private static String DEFAULT_ENCODING = "UTF-8";

	/**
	 * http-post请求
	 * @param params
	 * @param url
	 * @return String
	 */
	public static String executePost(Map<String, String> params, String url) {
		HttpClient httpClient = new HttpClient();
		PostMethod postMethod = new PostMethod(url);
		postMethod.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded;charset=utf-8");
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				postMethod.addParameter(key, params.get(key));
			}
		}
		try {
			httpClient.executeMethod(postMethod);
			return new String(postMethod.getResponseBody(), DEFAULT_ENCODING);
		} catch (Exception e) {
			LOG.error("httpClient error:post " + url, e);
		} finally {
			postMethod.releaseConnection();
			((SimpleHttpConnectionManager) httpClient
					.getHttpConnectionManager()).shutdown();
		}
		return null;
	}
	
	/**
	 * http-get请求
	 * @param params
	 * @param url
	 * @return String
	 */
	public static String executeGet(Map<String, String> params, String url) {
		StringBuffer urlParams = new StringBuffer();
		if (params != null && params.size() > 0) {
			for (String key : params.keySet()) {
				if (urlParams.length() > 0) {
					urlParams.append("&");
				}
				String value = params.get(key);
				if (value != null) {
					try {
						value = URLEncoder.encode(value, DEFAULT_ENCODING);
					} catch (UnsupportedEncodingException e) {
						LOG.error("URLEncoder ERROR::" + e);
					}
				}
				urlParams.append(key).append("=").append(value);
			}
		}

		if (urlParams != null && urlParams.length() > 0) {
			url = url + "?" + urlParams;
		}
		HttpClient httpClient = new HttpClient();
		GetMethod getMethod = new GetMethod(url);
		try {
			httpClient.executeMethod(getMethod);
			return new String(getMethod.getResponseBody(), DEFAULT_ENCODING);
		} catch (Exception e) {
			LOG.error("httpClient error:get " + url, e);
		} finally {
			getMethod.releaseConnection();
			((SimpleHttpConnectionManager) httpClient
					.getHttpConnectionManager()).shutdown();
		}
		return null;
	}
	
	/**
     * http 请求
     * @param param
     * @param url
     * @param method
     * @return
     */
    public static String sendData(Map<String,String> params, String url, String method) {
    	String ret = null;
    	LOG.info("request url " + url + ", param " + params + ", method " + method);
    	if(method==null){
    		throw new RuntimeException("no such method exception:expect get/post but method is null");
    	}else if(method.equals(HTTP_POST)){
    		ret = executePost(params,url);
    	}else if(method.equals(HTTP_GET)){
    		ret = executeGet(params,url);
    	}else{
    		throw new RuntimeException("no such method exception:expect get/post but method is "+method);
    	}
    	LOG.info("request url " + url + " result " + JsonUtil.toString(ret));
    	return ret;
    }
    
    public  HttpClientUtil() {
	}
}
