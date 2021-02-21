package com.savetask.api.service;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.savetask.api.domain.model.SecurityInfo;
import com.savetask.api.exceptions.InvalidAuthTokenException;

@ExtendWith(MockitoExtension.class)
public class AuthTokenServiceTest {

	@Mock
	private MockAuthService authService;
	
	@InjectMocks
	private AuthTokenService authTokenService;
	
	@Test
	public void validateTokenThrowsExceptionWhenSecurityInfoIsNull() {
		Assertions.assertThrows(IllegalArgumentException.class, ()->{
			authTokenService.validateToken(null);
		});
	}
	
	@Test
	public void validateTokenThrowsInvalidAuthTokenException() {
		Mockito.when(authService.isValid(Mockito.any(), Mockito.any())).thenReturn(false);
		Assertions.assertThrows(InvalidAuthTokenException.class, ()->{
			authTokenService.validateToken(Mockito.mock(SecurityInfo.class));
		});
	}
	
}
