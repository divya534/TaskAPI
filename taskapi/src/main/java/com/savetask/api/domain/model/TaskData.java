package com.savetask.api.domain.model;

import javax.validation.constraints.NotBlank;

public class TaskData {

	@NotBlank(message = "blob data is mandatory")
	private String blob;
	
	@NotBlank(message = "type is mandatory")
	private String type;
	
	
	public String getBlob() {
		return blob;
	}
	public void setBlob(String blob) {
		this.blob = blob;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
}
