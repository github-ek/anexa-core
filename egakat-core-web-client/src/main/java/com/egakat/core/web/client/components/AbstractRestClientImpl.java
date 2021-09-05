package com.egakat.core.web.client.components;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import lombok.val;

abstract public class AbstractRestClientImpl implements RestClient {

	abstract public RestTemplate getRestTemplate();

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP GET METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public <T, ID> ResponseEntity<T> get(String resourcePath, Class<T> responseType, ID id) {
		return get(resourcePath, getHttpHeaders(), responseType, id);
	}

	@Override
	public <T, ID> ResponseEntity<T> get(String resourcePath, HttpHeaders headers, Class<T> responseType, ID id) {
		return getOneQuery(resourcePath, "{id}", headers, responseType, id);
	}

	@Override
	public <T> ResponseEntity<T> getOneQuery(String resourcePath, String query, Class<T> responseType,
			Object... uriVariables) {
		return getOneQuery(resourcePath, query, getHttpHeaders(), responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> getOneQuery(String resourcePath, String query, HttpHeaders headers,
			Class<T> responseType, Object... uriVariables) {
		HttpEntity<?> request = new HttpEntity<>(headers);

		val url = buildUrl(resourcePath, query);
		val result = getRestTemplate().exchange(url, HttpMethod.GET, request, responseType, uriVariables);
		return result;
	}

	@Override
	public <T> ResponseEntity<T[]> getAllQuery(String resourcePath, String query, Class<T[]> responseType,
			Object... uriVariables) {
		return getAllQuery(resourcePath, query, getHttpHeaders(), responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T[]> getAllQuery(String resourcePath, String query, HttpHeaders headers,
			Class<T[]> responseType, Object... uriVariables) {
		HttpEntity<?> request = new HttpEntity<>(headers);

		val url = buildUrl(resourcePath, query);
		val result = getRestTemplate().exchange(url, HttpMethod.GET, request, responseType, uriVariables);
		return result;
	}

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP POST, PUT, DELETE METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public <T> ResponseEntity<T> post(String resourcePath, Object model, Class<T> responseType,
			Object... uriVariables) {
		return post(resourcePath, getHttpHeaders(), model, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> post(String resourcePath, HttpHeaders headers, Object model, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<?> request = new HttpEntity<>(mapModelToRequestBody(model), headers);

		return getRestTemplate().exchange(resourcePath, HttpMethod.POST, request, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> put(String resourcePath, Object model, Class<T> responseType, Object... uriVariables) {
		return put(resourcePath, getHttpHeaders(), model, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> put(String resourcePath, HttpHeaders headers, Object model, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<?> request = new HttpEntity<>(mapModelToRequestBody(model), headers);

		return getRestTemplate().exchange(resourcePath, HttpMethod.PUT, request, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> patch(String resourcePath, Object model, Class<T> responseType,
			Object... uriVariables) {
		return patch(resourcePath, getHttpHeaders(), model, responseType, uriVariables);
	}

	@Override
	public <T> ResponseEntity<T> patch(String resourcePath, HttpHeaders headers, Object model, Class<T> responseType,
			Object... uriVariables) {
		HttpEntity<?> request = new HttpEntity<>(mapModelToRequestBody(model), headers);

		return getRestTemplate().exchange(resourcePath, HttpMethod.PATCH, request, responseType, uriVariables);
	}

	@Override
	public <ID> void delete(String resourcePath, ID id) {
		getRestTemplate().delete(resourcePath, id);
	}

	@Override
	public <ID> void delete(String resourcePath, ID id, int version) {
		getRestTemplate().delete(resourcePath, id, version);
	}

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// --
	// ------------------------------------------------------------------------------------------------------------------------------------
	@Override
	public HttpHeaders getHttpHeaders() {
		return getHttpHeaders(getDefaultMediaType());
	}

	@Override
	public HttpHeaders getHttpHeaders(MediaType mediaType) {
		return getHttpHeaders(mediaType, getDefaultHeaders());
	}

	@Override
	public HttpHeaders getHttpHeaders(MediaType mediaType, MultiValueMap<String, String> values) {
		HttpHeaders result = new HttpHeaders();
		result.setContentType(mediaType);

		if (values != null) {
			result.addAll(values);
		}

		return result;
	}

	@Override
	public MultiValueMap<String, String> getDefaultHeaders() {
		return new LinkedMultiValueMap<String, String>();
	}

	protected MediaType getDefaultMediaType() {
		return MediaType.APPLICATION_JSON;
	}

	public static String buildUrl(String resourcePath, String query) {
		val sb = new StringBuilder();
		sb.append(resourcePath);

		query = query.trim();

		if (!query.isEmpty()) {
			char c = query.charAt(0);
			if (c != '?' && c != '/') {
				sb.append('/');
			}
			sb.append(query);
		}

		return sb.toString();
	}

	protected <T> Object mapModelToRequestBody(T model) {
		return model;
	}
}
