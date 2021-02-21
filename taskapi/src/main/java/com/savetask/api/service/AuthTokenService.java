package com.savetask.api.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savetask.api.domain.model.SecurityInfo;
import com.savetask.api.exceptions.InvalidAuthTokenException;

@Service
public class AuthTokenService {
	
	@Autowired
	private MockAuthService authService;
	
	private Logger logger = LoggerFactory.getLogger(AuthTokenService.class);
	
	public void validateToken(SecurityInfo security) throws Exception {
		
		if(security == null) {
			throw new IllegalArgumentException("Invalid Auth token");
		}
		
		if(!authService.isValid(security.getAuthToken(),security.getClientIp())) {
			logger.info("Auth token invalid!");
			throw new InvalidAuthTokenException("Security token is invalid!");
		}
		
		logger.info("Auth token successfully validated!");
	}

}
