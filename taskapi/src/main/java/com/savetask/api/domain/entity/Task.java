package com.savetask.api.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {

	@Id
    @GeneratedValue
	private Long id;
	
	@Column(name = "task_token")
	private String taskToken;
	
	@Column(name = "created_at")
	private String createdAt;
	
	@Column(name = "expiring_at")
	private String expiringAt;
	
	private String type;
	
	@Column(name = "updated_at")
	private String updatedAt;
	
	private String channel;
	
	@Column(name = "conversation_id")
	private String conversationId;
	
	private String country;
	
	@Column(name = "blob")
	private byte[] taskData;
	
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

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

	public byte[] getTaskData() {
		return taskData;
	}

	public void setTaskData(byte[] taskData) {
		this.taskData = taskData;
	}

	public String getTaskToken() {
		return taskToken;
	}

	public void setTaskToken(String taskToken) {
		this.taskToken = taskToken;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
