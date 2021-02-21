package com.savetask.api.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockAuthServiceTest {
	
	@InjectMocks
	private MockAuthService authService;
	
	@Test
	public void testIsValidReturnsTrue() {
		Assertions.assertTrue(authService.isValid("asd-567", "5678230"));
	}

}
