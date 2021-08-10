package com.anexa.core.web.client.components;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

public interface RestClient {

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP GET METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	<T, ID> ResponseEntity<T> get(String resourcePath, Class<T> responseType, ID id);

	<T, ID> ResponseEntity<T> get(String resourcePath, HttpHeaders headers, Class<T> responseType, ID id);

	<T> ResponseEntity<T> getOneQuery(String resourcePath, String query, Class<T> responseType, Object... uriVariables);

	<T> ResponseEntity<T> getOneQuery(String resourcePath, String query, HttpHeaders headers, Class<T> responseType,
			Object... uriVariables);

	<T> ResponseEntity<T[]> getAllQuery(String resourcePath, String query, Class<T[]> responseType,
			Object... uriVariables);

	<T> ResponseEntity<T[]> getAllQuery(String resourcePath, String query, HttpHeaders headers, Class<T[]> responseType,
			Object... uriVariables);

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// -- HTTP POST, PUT, DELETE METHODS
	// ------------------------------------------------------------------------------------------------------------------------------------
	<T> ResponseEntity<T> post(String resourcePath, Object model, Class<T> responseType, Object... uriVariables);

	<T> ResponseEntity<T> put(String resourcePath, Object model, Class<T> responseType, Object... uriVariables);

	<T> ResponseEntity<T> patch(String resourcePath, Object model, Class<T> responseType, Object... uriVariables);

	<ID> void delete(String resourcePath, ID id);

	<ID> void delete(String resourcePath, ID id, int version);

	// -----------------------------------------------'-------------------------------------------------------------------------------------
	// --
	// ------------------------------------------------------------------------------------------------------------------------------------
	HttpHeaders getHttpHeaders();

	HttpHeaders getHttpHeaders(MediaType mediaType);

	HttpHeaders getHttpHeaders(MediaType mediaType, MultiValueMap<String, String> values);

	MultiValueMap<String, String> getDefaultHeaders();
}