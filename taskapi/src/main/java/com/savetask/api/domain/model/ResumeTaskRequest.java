package com.savetask.api.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class ResumeTaskRequest {

	@Valid
	@NotNull(message = "Task Data is missing")
	private SaveTaskInfo data;
	
	@Valid
	@NotNull(message = "Security Info is missing")
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
