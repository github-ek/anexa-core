package com.egakat.core.mail.service.api;

import com.egakat.core.mail.dto.MailMessageDto;

public interface MailService {

	void sendMail(String to, MailMessageDto mailMessage);
	
	void sendMail(String to[], String cc[], MailMessageDto mailMessage);

	void sendMail(MailMessageDto mailMessage);
}