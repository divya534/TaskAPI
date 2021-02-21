package com.savetask.api.domain.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SecurityInfo {

	@NotBlank(message = "Client Ip is mandatory")
	@JsonProperty("client_ip")
	private String clientIp;
	
	@NotBlank(message = "Auth Token is mandatory")
	@JsonProperty("auth_token")
	private String authToken;
	
	
	public String getClientIp() {
		return clientIp;
	}
	public void setClientIp(String clientIp) {
		this.clientIp = clientIp;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
}
