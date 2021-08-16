package com.anexa.core.mail.service.api;

import com.anexa.core.mail.dto.MailMessageDto;

public interface MailService {

	void sendMail(String to, MailMessageDto mailMessage);
	
	void sendMail(String to[], String cc[], MailMessageDto mailMessage);

	void sendMail(MailMessageDto mailMessage);
}