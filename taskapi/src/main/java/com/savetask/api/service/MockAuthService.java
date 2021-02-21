package com.savetask.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MockAuthService {

	private Logger logger = LoggerFactory.getLogger(MockAuthService.class);
	
	
	public boolean isValid(String authToken, String clientIp) {
		logger.info("validating the auth token with client ip - {}, token -  {}",clientIp, authToken);
		return true;
	}
}
