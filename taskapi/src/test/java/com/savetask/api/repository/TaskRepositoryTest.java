package com.savetask.api.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.savetask.api.domain.entity.Task;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskRepositoryTest {

    private final String TASK_TOKEN = "347af4b1-759b-45c8-95fe-237386397b1b1613838115948";

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void givenEmptyDBWhenFindByTaskTokenThenReturnNull() {
       Task task = taskRepository.findByTaskToken(TASK_TOKEN);

        assertNull(task);
    }

    @Test
    public void givenTaskInDBWhenByTaskTokenThenReturnTask() {
        Task task = new Task();
        task.setChannel("ch1");
        task.setCountry("USA");
        task.setStatus("COMPLETED");
        task.setConversationId("1234");
        task.setType("THREAT_ME");
        task.setTaskToken(TASK_TOKEN);
        task.setTaskData(new String("test").getBytes());
        task.setCreatedAt(""+System.currentTimeMillis());
		long expiry = System.currentTimeMillis() + (1000 * 60 * 60 * 5);
		task.setExpiringAt(""+expiry);
		task.setUpdatedAt(""+System.currentTimeMillis());
        
        taskRepository.save(task);

        Task task1 = taskRepository.findByTaskToken(TASK_TOKEN);

        assertNotNull(task1);
        assertEquals(task.getTaskToken(), task1.getTaskToken());
        assertEquals(task.getStatus(), task1.getStatus());
    }

    @AfterEach
    public void cleanUp() {
        taskRepository.deleteAll();
    }

}
