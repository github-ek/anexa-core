package com.anexa.core.domain;

public interface ObjectAuditableByUser {
	
	String getCreadoPor();

	void setCreadoPor(String creadorPor);

	String getModificadoPor();

	void setModificadoPor(String modificadoPor);
}
