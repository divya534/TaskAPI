package com.savetask.api.domain.model;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SaveTaskRequest {

	@NotBlank(message = "Channel is mandatory")
	private String channel;
	
	@NotBlank(message = "Conversation Id is mandatory")
	private String conversationId;
	
	@NotBlank(message = "Country is mandatory")
	private String country;

	@Valid
	@NotNull(message = "Task Data is missing")
	@JsonProperty("json_data")
	private TaskData taskData;

	@Valid
	@NotNull(message = "Security Info is missing")
	private SecurityInfo security;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getConversationId() {
		return conversationId;
	}

	public void setConversationId(String conversationId) {
		this.conversationId = conversationId;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public TaskData getTaskData() {
		return taskData;
	}

	public void setTaskData(TaskData taskData) {
		this.taskData = taskData;
	}

	public SecurityInfo getSecurity() {
		return security;
	}

	public void setSecurity(SecurityInfo security) {
		this.security = security;
	}
}
