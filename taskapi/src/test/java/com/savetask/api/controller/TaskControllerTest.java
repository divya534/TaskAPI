package com.savetask.api.controller;


import org.hamcrest.core.Is;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.savetask.api.service.TaskService;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @MockBean
    private TaskService taskService;
    
    @Autowired
    TaskController taskController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void whenTaskControllerInjected_thenNotNull() throws Exception {
        Assertions.assertNotNull(taskController);
    }

    @Test
    public void whenPostRequestToSaveWithOutBody_thenErrorResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/task/save")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
    
    @Test
    public void whenPostRequestToSaveTaskIsValid_thenCorrectResponse() throws Exception {
        String saveTask = "{\r\n" + 
        		"	\"channel\" : \"ch\",\r\n" + 
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
        		"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/task/save")
                .content(saveTask)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void whenPostRequestToSaveTaskAndInValidRequest_thenCorrectReponse() throws Exception {
    	 String saveTask = "{\r\n" + 
    	 		"	\"channel\" : \"\",\r\n" + 
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
    	 		"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/task/save")
                .content(saveTask)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.channel", Is.is("Channel is mandatory")))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
    

    @Test
    public void whenPostRequestToResumeTaskWithOutBody_thenErrorResponse() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/task/resume")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is(400));
    }
    
    @Test
    public void whenPostRequestToResumeTaskIsValid_thenCorrectResponse() throws Exception {
        String resumeTask = "{\r\n" + 
        		"     \"data\": {\r\n" + 
        		"        \"type\": \"THREAT_MET\",\r\n" + 
        		"        \"created_at\": \"1612874449626\",\r\n" + 
        		"        \"expiring_at\": \"1612892449626\",\r\n" + 
        		"        \"updated_at\": \"1612874449626\",\r\n" + 
        		"        \"task_token\": \"cd12f67c-8531-473b-ad79-a79afc11f88e1613842742242\"\r\n" + 
        		"    },\r\n" + 
        		"    \"security\" : {\r\n" + 
        		"		\"client_ip\" : \"172058014251\",\r\n" + 
        		"		\"auth_token\" : \"web-c1696899-d015-45c9-9472-9a665e28cef9\"\r\n" + 
        		"	}\r\n" + 
        		"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/task/resume")
                .content(resumeTask)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
    
    @Test
    public void whenPostRequestToResumeTaskAndInValidRequest_thenCorrectReponse() throws Exception {
    	 String resumeTask = "{\r\n" + 
    	 		"     \"data\": {\r\n" + 
    	 		"        \"type\": \"THREAT_MET\",\r\n" + 
    	 		"        \"created_at\": \"1612874449626\",\r\n" + 
    	 		"        \"expiring_at\": \"1612892449626\",\r\n" + 
    	 		"        \"updated_at\": \"1612874449626\",\r\n" + 
    	 		"        \"task_token\": \"\"\r\n" + 
    	 		"    },\r\n" + 
    	 		"    \"security\" : {\r\n" + 
    	 		"		\"client_ip\" : \"172058014251\",\r\n" + 
    	 		"		\"auth_token\" : \"web-c1696899-d015-45c9-9472-9a665e28cef9\"\r\n" + 
    	 		"	}\r\n" + 
    	 		"}";
        mockMvc.perform(MockMvcRequestBuilders.post("/task/resume")
                .content(resumeTask)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
