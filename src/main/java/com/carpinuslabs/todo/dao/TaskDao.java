package com.carpinuslabs.todo.dao;

import java.util.List;

import com.carpinuslabs.todo.model.Task;

public interface TaskDao {

	public List<Task> listTasks();

	public Task getTask(String id);

	public void saveTask(Task task);

	public void deleteTask(String id);

}
