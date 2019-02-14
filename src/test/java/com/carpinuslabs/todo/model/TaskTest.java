package com.carpinuslabs.todo.model;

import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
        Task task = new Task("Title", "Lorem ipsum dolor est");

        assertFalse(task.isDone());

        ObjectMapper mapper = new ObjectMapper();
        try {
          String jsonInString = mapper.writeValueAsString(task);
          System.out.println(jsonInString);
        } catch (JsonProcessingException e) {
          e.printStackTrace();
        }
    }
}
