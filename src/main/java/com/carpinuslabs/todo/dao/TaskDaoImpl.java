package com.carpinuslabs.todo.dao;

import java.util.List;
import java.util.UUID;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.carpinuslabs.todo.model.Task;

public class TaskDaoImpl implements TaskDao {

	private AmazonDynamoDB client;
	private DynamoDBMapper mapper;

	public TaskDaoImpl() {
		this.client = AmazonDynamoDBClientBuilder.standard().build();
		this.mapper = new DynamoDBMapper(this.client);
	}

	public List<Task> listTasks() {
		return this.mapper.scan(Task.class, new DynamoDBScanExpression());
	}

	public Task getTask(String id) {
		return this.mapper.load(Task.class, id);
	}

	public void saveTask(Task task) {
		if(task.getId() == null) {
			task.setId(UUID.randomUUID().toString());
		}
		this.mapper.save(task);
	}

	public void deleteTask(String id) {
		Task task = this.getTask(id);
		if(task != null) {
			this.mapper.delete(task);
		}
	}

}
