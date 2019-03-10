package com.anexa.core.web.client.properties;

public interface RestTokenGeneratorProperties extends RestTokenProperties {
	String getApiTokenUrlGenerator();

	void setApiTokenUrlGenerator(String value);
}