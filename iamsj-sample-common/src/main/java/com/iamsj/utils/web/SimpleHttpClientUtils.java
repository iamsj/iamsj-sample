/**
 * 
 */
package com.iamsj.utils.web;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * @author sj
 *
 */
public class SimpleHttpClientUtils {

	public static String excuteSimplePost(String uri, Map<String, Object> paramMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);
		if (paramMap != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		System.out.println("SimpleHttpClientUtils excuteSimplePost url is :" + uriBuilder.build().toString());
		HttpPost httpPost = new HttpPost(uriBuilder.build());
		CloseableHttpResponse response = null;
		String result = null;

		response = closeableHttpClient.execute(httpPost);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		EntityUtils.consume(response.getEntity());
		response.close();
		return result;

	}

	public static String excuteSimpleGet(String uri, Map<String, Object> paramMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);

		if (paramMap != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		System.out.println("SimpleHttpClientUtils excuteGet url is :" + uriBuilder.build().toString());
		HttpGet httpGet = new HttpGet(uriBuilder.build());

		CloseableHttpResponse response = null;
		String result = null;

		response = closeableHttpClient.execute(httpGet);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		EntityUtils.consume(response.getEntity());
		response.close();
		return result;

	}

	public static CloseableHttpResponse excuteGet(String uri, Map<String, Object> paramMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);

		if (paramMap != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		System.out.println("SimpleHttpClientUtils excuteGet url is :" + uriBuilder.build().toString());
		HttpGet httpGet = new HttpGet(uriBuilder.build());

		return closeableHttpClient.execute(httpGet);
	}

	public static String excuteJsonPost(String uri, Map<String, Object> paramMap, Map<String, Object> addHeadMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);

		System.out.println("SimpleHttpClientUtils excuteJsonPost url is :" + uriBuilder.build().toString());
		HttpPost httpPost = new HttpPost(uriBuilder.build());
		httpPost.addHeader("Content-type", "application/json; charset=utf-8");
		httpPost.setHeader("Accept", "application/json");
		if (addHeadMap != null) {
			for (Entry<String, Object> entry : addHeadMap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue().toString());
			}
		}
		httpPost.setEntity(new StringEntity(JSON.toJSONString(paramMap), Charset.forName("UTF-8")));
		CloseableHttpResponse response = null;
		String result = null;

		response = closeableHttpClient.execute(httpPost);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		EntityUtils.consume(response.getEntity());
		response.close();
		return result;
	}

	public static String excuteGet(String uri, Map<String, Object> paramMap, Map<String, Object> addHeadMap)
			throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = SimpleHttpClientManager.getCloseableHttpClient();
		URIBuilder uriBuilder = new URIBuilder(uri);

		if (paramMap != null) {
			for (Entry<String, Object> entry : paramMap.entrySet()) {
				uriBuilder.addParameter(entry.getKey(), entry.getValue().toString());
			}
		}
		System.out.println("SimpleHttpClientUtils excuteGet url is :" + uriBuilder.build().toString());
		HttpGet httpGet = new HttpGet(uriBuilder.build());
		if (addHeadMap != null) {
			for (Entry<String, Object> entry : addHeadMap.entrySet()) {
				httpGet.addHeader(entry.getKey(), entry.getValue().toString());
			}
		}
		CloseableHttpResponse response = null;
		String result = null;

		response = closeableHttpClient.execute(httpGet);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		EntityUtils.consume(response.getEntity());
		response.close();
		return result;
	}

	public static String formatPrefixUrl(String url) {
		Pattern pattern = Pattern.compile("(http|ftp|https):\\/\\/([\\w.]+\\/?)\\S*");
		Matcher matcher = pattern.matcher(url);
		if (!matcher.matches()) {
			return "http://" + url;
		}
		return url;

	}

	public static String excutePostWithHttps(String uri, String paramContent, Map<String, Object> addHeadMap,
			String certPath) throws URISyntaxException, IOException {
		uri = formatPrefixUrl(uri);
		CloseableHttpClient closeableHttpClient = HttpClients.custom().setSSLSocketFactory(createSSLConnSocketFactory())
				.setConnectionManager(SimpleHttpClientManager.getInstance())
				.setDefaultRequestConfig(SimpleHttpClientManager.requestConfig).build();

		URIBuilder uriBuilder = new URIBuilder(uri);

		System.out.println("SimpleHttpClientUtils excuteJsonPost url is :" + uriBuilder.build().toString());
		HttpPost httpPost = new HttpPost(uriBuilder.build());
		if (addHeadMap != null) {
			for (Entry<String, Object> entry : addHeadMap.entrySet()) {
				httpPost.addHeader(entry.getKey(), entry.getValue().toString());
			}
		}

		httpPost.setEntity(new StringEntity(paramContent, Charset.forName("UTF-8")));
		CloseableHttpResponse response = null;
		String result = null;

		response = closeableHttpClient.execute(httpPost);
		result = EntityUtils.toString(response.getEntity(), "utf-8");
		EntityUtils.consume(response.getEntity());
		response.close();
		return result;
	}

	/**
	 * 创建SSL安全连接
	 * 
	 * @return
	 */
	private static SSLConnectionSocketFactory createSSLConnSocketFactory() {
		SSLConnectionSocketFactory sslsf = null;
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {

				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();
			sslsf = new SSLConnectionSocketFactory(sslContext,new HostnameVerifier() {
				
				@Override
				public boolean verify(String hostname, SSLSession session) {
					// TODO Auto-generated method stub
					return Boolean.TRUE;
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sslsf;
	}

	public static void main(String[] args) throws URISyntaxException, IOException {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("format", "json");
		paramMap.put("ip", "218.4.255.255");

		String result = SimpleHttpClientUtils.excuteSimpleGet("int.dpool.sina.com.cn/iplookup/iplookup.php", paramMap);
		JSONObject json = JSON.parseObject(result);
		System.out.println(json.get("ret"));
	}
}
