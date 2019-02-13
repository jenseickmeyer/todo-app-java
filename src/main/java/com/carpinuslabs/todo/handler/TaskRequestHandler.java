package com.carpinuslabs.todo.handler;

import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

import com.carpinuslabs.todo.model.Task;

/**
 * Entrypoints for API Gateway
 */
public class TaskRequestHandler {
	
	public APIGatewayProxyResponseEvent listTasks(APIGatewayProxyRequestEvent request, Context context) {
		Task task = new Task();
		task.setTitle("Some Task");
		task.setDescription("Lorem ipsum dolor est");

		List<Task> tasks = new ArrayList<Task>();
		tasks.add(task);

		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(tasks);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			return new APIGatewayProxyResponseEvent().withStatusCode(200).withHeaders(headers).withBody(jsonInString);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		}
	}

	public APIGatewayProxyResponseEvent getTask(APIGatewayProxyRequestEvent request, Context context) {
		Task task = new Task();
		task.setTitle("Some Task");
		task.setDescription("Lorem ipsum dolor est");

		try {
			ObjectMapper mapper = new ObjectMapper();
			String jsonInString = mapper.writeValueAsString(task);

			Map<String, String> headers = new HashMap<String, String>();
			headers.put("Content-Type", "application/json");
			return new APIGatewayProxyResponseEvent().withStatusCode(200).withHeaders(headers).withBody(jsonInString);
		} catch(JsonProcessingException e) {
			e.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		}
	}

}
