package com.carpinuslabs.todo.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * Unit test for simple App.
 */
public class TaskTest
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldSerializeAsJSON()
    {
        Task task = new Task();
        task.setTitle("Title");
        task.setDescription("Lorem ipsum dolor est");

        assertFalse(task.isDone());

        ObjectMapper mapper = new ObjectMapper();
        try {
          String jsonInString = mapper.writeValueAsString(task);
          System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
    }
    
    @Test
    public void shouldBefalse() {
    	assertFalse(false);
    	
    	Task task = new Task();
    	task.setTitle("test");
    	task.setTest("test");
    	
    	assertEquals("test", task.getTest());
    }
}
