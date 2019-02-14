package com.carpinuslabs.todo.model;

import java.util.UUID;

public class Task {
	private String id;
	private String title;
	private String description;
	private boolean done;

	public Task() {
		super();
		this.done = false;
	}
	
	public Task(String title, String description) {
		this.setId(UUID.randomUUID().toString());
		this.title = title;
		this.description = description;
		this.done = false;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return this.title;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDone() {
		this.done = true;
	}

	public void setOpen() {
		this.done = false;
	}

	public boolean isDone() {
		return this.done;
	}
}
