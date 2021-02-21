package com.savetask.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.savetask.api.domain.model.ResumeTaskRequest;
import com.savetask.api.domain.model.ResumeTaskResponse;
import com.savetask.api.domain.model.SaveTaskRequest;
import com.savetask.api.domain.model.SaveTaskResponse;
import com.savetask.api.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	
	//@PostMapping(path = "/save", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping("/save")
	public ResponseEntity saveTask(@Valid @RequestBody SaveTaskRequest request) {
		try {
			 SaveTaskResponse saveTask = taskService.saveTask(request);
			 return ResponseEntity.ok(saveTask);
		} catch (Exception e) {
			logger.error("Save task request failed", e);
		}
		logger.info("Save task failure- Returing the default erro - 500 (Internal error) ");
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PostMapping(path ="/resume",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity retrieveTask(@Valid @RequestBody ResumeTaskRequest request) {
		
		try {
			 ResumeTaskResponse resumeTask = taskService.resumeTask(request);
			 return ResponseEntity.ok(resumeTask);
		} catch (Exception e) {
			logger.error("Resume task request failed", e);
		}
		logger.info("Resume task failure- Returing the default erro - 400 (Bad request) ");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}
}
