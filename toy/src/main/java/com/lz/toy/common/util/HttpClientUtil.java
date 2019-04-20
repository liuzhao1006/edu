package com.lz.toy.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;


public class HttpClientUtil {


	public HttpClientUtil() {
	}

	public static String postString(String url, String params) {
		String result = "";
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		StringEntity uefEntity;
		try {
			uefEntity = new StringEntity(params, "UTF-8");
			httppost.setEntity(uefEntity);
//			System.out.println("executing request " + httppost.getURI());
			try (CloseableHttpResponse response = httpclient.execute(httppost)) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
//					System.out.println("Response content: " + EntityUtils.toString(entity, "UTF-8"));
					result = EntityUtils.toString(entity, "UTF-8");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送 post请求
	 */
	public static String postObjectByForm(String url, Object object) {
		String result = "";
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		StringEntity uefEntity;
		try {
			// 构造最简单的字符串数据
			StringEntity reqEntity = new StringEntity(object.toString(),"UTF-8");
			// 设置类型
			reqEntity.setContentType("application/x-www-form-urlencoded");
			// 设置请求的数据
			httppost.setEntity(reqEntity);
			try (CloseableHttpResponse response = httpclient.execute(httppost)) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送 post请求
	 */
	public static String postObjectByJson(String url, Object object) {
		String result = "";
		// 创建默认的httpClient实例.
		CloseableHttpClient httpclient = HttpClients.createDefault();
		// 创建httppost
		HttpPost httppost = new HttpPost(url);
		StringEntity uefEntity;
		try {
			uefEntity = new StringEntity(JSON.toJSONString(object), "UTF-8");
			httppost.setEntity(uefEntity);
			httppost.setHeader("serviceURL", url);
			try (CloseableHttpResponse response = httpclient.execute(httppost)) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					result = EntityUtils.toString(entity, "UTF-8");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * 发送 get请求
	 */
	public static String getByString(String url) {
		String result = "";
		CloseableHttpClient httpclient = HttpClients.createDefault();
		try {
			// 创建httpget.
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.
			try (CloseableHttpResponse response = httpclient.execute(httpget)) {
				// 获取响应实体
				HttpEntity entity = response.getEntity();
				// 打印响应状态
//				System.out.println(response.getStatusLine());
				if (entity != null) {
					// 打印响应内容长度
//					System.out.println("Response content length: " + entity.getContentLength());
					// 打印响应内容
					result = EntityUtils.toString(entity);
				}
			}
		} catch (ParseException | IOException e) {
			e.printStackTrace();
		} finally {
			// 关闭连接,释放资源
			try {
				httpclient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


}
