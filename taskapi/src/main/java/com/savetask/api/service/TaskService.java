package com.savetask.api.service;

import java.util.Base64;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.savetask.api.domain.entity.Task;
import com.savetask.api.domain.model.ResumeTaskRequest;
import com.savetask.api.domain.model.ResumeTaskResponse;
import com.savetask.api.domain.model.SaveTaskInfo;
import com.savetask.api.domain.model.SaveTaskRequest;
import com.savetask.api.domain.model.SaveTaskResponse;
import com.savetask.api.domain.model.TaskData;
import com.savetask.api.exceptions.TaskNotFoundException;
import com.savetask.api.repository.TaskRepository;

@Service
public class TaskService {

	@Autowired
	private AuthTokenService authTokenService;

	@Autowired
	private TaskRepository taskRepository;
	
	private Logger logger = LoggerFactory.getLogger(TaskService.class);

	public SaveTaskResponse saveTask(SaveTaskRequest request) throws Exception {
		logger.info("Save task - validating the auth token");
		authTokenService.validateToken(request.getSecurity());
		
		Task taskEntity = createEntity(request);
		taskEntity = taskRepository.save(taskEntity);
		logger.info("Save task - task is saved in the DB with task token {}.",taskEntity.getTaskToken());
		return createResponse(request,taskEntity);
	}

	private SaveTaskResponse createResponse(SaveTaskRequest request, Task taskEntity) {
		SaveTaskResponse saveTaskResponse = new SaveTaskResponse();
		SaveTaskInfo  taskInfo = new SaveTaskInfo();
		taskInfo.setCreatedAt(taskEntity.getCreatedAt());
		taskInfo.setExpiringAt(taskEntity.getExpiringAt());
		taskInfo.setUpdatedAt(taskEntity.getUpdatedAt());
		taskInfo.setTaskToken(taskEntity.getTaskToken());
		taskInfo.setType(taskEntity.getType());
		saveTaskResponse.setData(taskInfo);
		saveTaskResponse.setSecurity(request.getSecurity());
		return saveTaskResponse;
	}

	private Task createEntity(SaveTaskRequest request) {
		Task task = new Task();
		task.setChannel(request.getChannel());
		task.setConversationId(request.getConversationId());
		task.setCountry(request.getCountry());
		task.setCreatedAt(""+System.currentTimeMillis());
		long expiry = System.currentTimeMillis() + (1000 * 60 * 60 * 5);
		task.setExpiringAt(""+expiry);
		task.setUpdatedAt(""+System.currentTimeMillis());
		task.setTaskData(decrypt(request.getTaskData()).getBytes());
		task.setType(request.getTaskData().getType());
		task.setTaskToken(UUID.randomUUID().toString()+System.currentTimeMillis());
		return task;
	}

	private String decrypt(TaskData taskData) {
		byte[] dataBytes = Base64.getDecoder().decode(taskData.getBlob());
		return new String(dataBytes);
	}

	public ResumeTaskResponse resumeTask(ResumeTaskRequest request) throws Exception {
		logger.info("Resume task - validating the auth token");
		authTokenService.validateToken(request.getSecurity());
		Task findByTaskToken = taskRepository.findByTaskToken(request.getData().getTaskToken());
		if(findByTaskToken != null) {
			findByTaskToken.setUpdatedAt(""+System.currentTimeMillis());
			findByTaskToken.setStatus("resumed");
			findByTaskToken = taskRepository.save(findByTaskToken);
		}else {
			logger.info("Resume task - task not found in the DB with task token {}.",request.getData().getTaskToken());
			throw new TaskNotFoundException("Couldn't find task with token="+request.getData().getTaskToken());
		}
		logger.info("Resume task - task is resumed in the DB with task token {}.",findByTaskToken.getTaskToken());
		return createResumeResponse(findByTaskToken);
	}

	private ResumeTaskResponse createResumeResponse(Task findByTaskToken) {
		ResumeTaskResponse resumeTaskResponse = new ResumeTaskResponse();
		resumeTaskResponse.setMsg("task has been resumed");
		resumeTaskResponse.setStatus("success");
		return resumeTaskResponse;
	}

}
