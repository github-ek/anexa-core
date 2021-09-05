package com.egakat.core.web.client.configuration;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.HeaderElement;
import org.apache.http.HeaderElementIterator;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import lombok.val;

public abstract class RestTemplateConfiguration {

	@Bean
	public HttpComponentsClientHttpRequestFactory clientHttpRequestFactory()
			throws KeyStoreException, NoSuchAlgorithmException, KeyManagementException {
		// val httpClient = getHttpClientBuilder().build();

		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy)
				.build();

		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext,NoopHostnameVerifier.INSTANCE);

		CloseableHttpClient httpClient = HttpClients.custom().setSSLSocketFactory(csf).build();

		return new HttpComponentsClientHttpRequestFactory(httpClient);
	}

	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder,
			HttpComponentsClientHttpRequestFactory requestFactory) {

		// val result = builder.build();
		// result.setRequestFactory(requestFactory);
		// return result;

		// requestFactory.setHttpClient(httpClient);
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		return restTemplate;
	}

	protected HttpClientBuilder getHttpClientBuilder() {
		RequestConfig config = getRequestConfigBuilder().build();

		val result = HttpClientBuilder.create().setDefaultRequestConfig(config)
				.setKeepAliveStrategy(getKeepAliveStrategy());
		return result;
	}

	protected RequestConfig.Builder getRequestConfigBuilder() {
		int timeout = getTimeout();

		// @formatter:off
		val result = RequestConfig.custom()
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.setSocketTimeout(timeout);
		// @formatter:on
		return result;
	}

	protected int getTimeout() {
		return 5 * 60 * 1000;
	}

	ConnectionKeepAliveStrategy getKeepAliveStrategy() {
		ConnectionKeepAliveStrategy result = new ConnectionKeepAliveStrategy() {

			@Override
			public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
				HeaderElementIterator it = new BasicHeaderElementIterator(
						response.headerIterator(HTTP.CONN_KEEP_ALIVE));
				while (it.hasNext()) {
					HeaderElement he = it.nextElement();
					String param = he.getName();
					String value = he.getValue();
					if (value != null && param.equalsIgnoreCase("timeout")) {
						return Long.parseLong(value) * 1000;
					}
				}
				return 60 * 1000;
			}
		};

		return result;
	}
}
