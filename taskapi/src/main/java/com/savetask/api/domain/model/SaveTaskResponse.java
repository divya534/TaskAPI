package com.savetask.api.domain.model;


public class SaveTaskResponse {
	
	private SaveTaskInfo data;
	
	private SecurityInfo security;

	public SaveTaskInfo getData() {
		return data;
	}

	public void setData(SaveTaskInfo data) {
		this.data = data;
	}

	public SecurityInfo getSecurity() {
		return security;
	}

	public void setSecurity(SecurityInfo security) {
		this.security = security;
	}
}
