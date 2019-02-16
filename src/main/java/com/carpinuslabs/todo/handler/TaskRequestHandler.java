package com.carpinuslabs.todo.handler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.carpinuslabs.todo.dao.TaskDao;
import com.carpinuslabs.todo.dao.TaskDaoImpl;
import com.carpinuslabs.todo.model.Task;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Entrypoints for API Gateway
 */
public class TaskRequestHandler {
	
	private TaskDao dao;

	public void setTaskDao(TaskDao dao) {
		this.dao = dao;
	}

	private TaskDao getTaskDao() {
		if(this.dao == null) {
			this.dao = new TaskDaoImpl();
		}
		return this.dao;
	}
	
	public APIGatewayProxyResponseEvent listTasks(APIGatewayProxyRequestEvent request, Context context) {
		List<Task> tasks = this.getTaskDao().listTasks();

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
	
	public APIGatewayProxyResponseEvent createTask(APIGatewayProxyRequestEvent request, Context context) {
		String body = request.getBody();
		ObjectMapper mapper = new ObjectMapper();
		Task task;
		
		try {
			task = mapper.readValue(body, Task.class);
			this.getTaskDao().saveTask(task);
			return new APIGatewayProxyResponseEvent().withStatusCode(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		} catch (IOException e) {
			e.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		}
	}

	public APIGatewayProxyResponseEvent getTask(APIGatewayProxyRequestEvent request, Context context) {
		String taskId = request.getPathParameters().get("id");
		Task task = this.getTaskDao().getTask(taskId);
		
		if(task == null) {
			return new APIGatewayProxyResponseEvent().withStatusCode(404);
		}
		
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
	
	public APIGatewayProxyResponseEvent updateTask(APIGatewayProxyRequestEvent request, Context context) {
		String body = request.getBody();
		ObjectMapper mapper = new ObjectMapper();
		Task task;
		
		try {
			task = mapper.readValue(body, Task.class);
			this.getTaskDao().saveTask(task);
			return new APIGatewayProxyResponseEvent().withStatusCode(200);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		} catch (IOException e) {
			e.printStackTrace();
			return new APIGatewayProxyResponseEvent().withStatusCode(500);
		}
	}
	
	public APIGatewayProxyResponseEvent deleteTask(APIGatewayProxyRequestEvent request, Context context) {
		String taskId = request.getPathParameters().get("id");
		this.getTaskDao().deleteTask(taskId);
		
		return new APIGatewayProxyResponseEvent().withStatusCode(200);
	}

}
