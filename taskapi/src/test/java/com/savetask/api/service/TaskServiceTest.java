package com.savetask.api.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.savetask.api.domain.entity.Task;
import com.savetask.api.domain.model.ResumeTaskRequest;
import com.savetask.api.domain.model.ResumeTaskResponse;
import com.savetask.api.domain.model.SaveTaskRequest;
import com.savetask.api.domain.model.SaveTaskResponse;
import com.savetask.api.exceptions.InvalidAuthTokenException;
import com.savetask.api.repository.TaskRepository;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class TaskServiceTest {
	@Autowired
	private TaskRepository taskRepository;
	
	@MockBean
	private AuthTokenService authTokenService;
	
	@Autowired
	private TaskService taskService;
	
	@Test
	public void saveTaskSuccessfullWhenAuthValidationIsFine() throws JsonMappingException, JsonProcessingException, Exception {
		SaveTaskResponse saveTaskResponse = taskService.saveTask(createSaveTaskRequest());
		assertNotNull(saveTaskResponse);
		String taskToken = saveTaskResponse.getData().getTaskToken();
		Task findByTaskToken = taskRepository.findByTaskToken(taskToken);
		assertNotNull(findByTaskToken);
	}
	
	@Test
	public void saveTaskFailedWhenAuthValidationThrowsException() throws JsonMappingException, JsonProcessingException, Exception {
		Mockito.doThrow(InvalidAuthTokenException.class).when(authTokenService).validateToken(Mockito.any());

		Assertions.assertThrows(InvalidAuthTokenException.class, ()->{
			taskService.saveTask(createSaveTaskRequest());
		});

	}
	
	@Test
	public void resumeTaskSuccessfullWhenAuthValidationIsFine() throws JsonMappingException, JsonProcessingException, Exception {
		SaveTaskResponse saveTaskResponse = taskService.saveTask(createSaveTaskRequest());
		assertNotNull(saveTaskResponse);
		String taskToken = saveTaskResponse.getData().getTaskToken();
		Task findByTaskToken = taskRepository.findByTaskToken(taskToken);
		assertNotNull(findByTaskToken);
		ResumeTaskRequest resumeTaskRequest = createResumeTaskRequest();
		resumeTaskRequest.getData().setTaskToken(saveTaskResponse.getData().getTaskToken());
		ResumeTaskResponse resumetask = taskService.resumeTask(resumeTaskRequest);
		assertEquals("success", resumetask.getStatus());
		assertEquals("task has been resumed", resumetask.getMsg());
	}
	
	@Test
	public void resumeTaskFailedWhenAuthValidationThrowsException() throws JsonMappingException, JsonProcessingException, Exception {
		Mockito.doThrow(InvalidAuthTokenException.class).when(authTokenService).validateToken(Mockito.any());

		Assertions.assertThrows(InvalidAuthTokenException.class, ()->{
			taskService.resumeTask(createResumeTaskRequest());
		});

	}
	
	
	ResumeTaskRequest createResumeTaskRequest() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		ResumeTaskRequest resumeTaskRequest = mapper.readValue("{\r\n" + 
				"     \"data\": {\r\n" + 
				"        \"type\": \"THREAT_MET\",\r\n" + 
				"        \"created_at\": \"1612874449626\",\r\n" + 
				"        \"expiring_at\": \"1612892449626\",\r\n" + 
				"        \"updated_at\": \"1612874449626\",\r\n" + 
				"        \"task_token\": \"69bf56c0-517a-4af6-9954-72b97e163ea81612961491329\"\r\n" + 
				"    },\r\n" + 
				"    \"security\" : {\r\n" + 
				"		\"client_ip\" : \"172058014251\",\r\n" + 
				"		\"auth_token\" : \"web-c1696899-d015-45c9-9472-9a665e28cef9\"\r\n" + 
				"	}\r\n" + 
				"}", ResumeTaskRequest.class);
		return resumeTaskRequest;
	}
	
	
	SaveTaskRequest createSaveTaskRequest() throws JsonMappingException, JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		SaveTaskRequest saveTaskRequest = mapper.readValue("{\r\n" + 
				"	\"channel\" : \"ch1\",\r\n" + 
				"	\"conversationId\" : \"123\",\r\n" + 
				"	\"country\" : \"USA\",\r\n" + 
				"	\"json_data\" : {\r\n" + 
				"		\"blob\" : \"TXkgZmlyc3QgdGFzaw==\",\r\n" + 
				"		\"type\" : \"THREAT_MET\"\r\n" + 
				"	},\r\n" + 
				"	\"security\" : {\r\n" + 
				"		\"client_ip\" : \"172058014251\",\r\n" + 
				"		\"auth_token\" : \"web-c1696899-d015-45c9-9472-9a665e28cef9\"\r\n" + 
				"	}\r\n" + 
				"}", SaveTaskRequest.class);
		return saveTaskRequest;
	}

}
