package com.savetask.api.domain.model;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class SaveTaskInfo {

	@JsonProperty("created_at")
	private String createdAt;
	
	@JsonProperty("expiring_at")
	private String expiringAt;
	
	private String type;
	
	@JsonProperty("updated_at")
	private String updatedAt;
	
	@NotBlank(message = "Task Token is mandatory")
	@JsonProperty("task_token")
	private String taskToken;

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getExpiringAt() {
		return expiringAt;
	}

	public void setExpiringAt(String expiringAt) {
		this.expiringAt = expiringAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getTaskToken() {
		return taskToken;
	}

	public void setTaskToken(String taskToken) {
		this.taskToken = taskToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
