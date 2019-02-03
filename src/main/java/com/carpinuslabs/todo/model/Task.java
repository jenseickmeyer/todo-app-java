package com.carpinuslabs.todo.model;

public class Task {
  private String title;
  private String description;
  private boolean done;

  public Task() {
    super();
    this.done = false;
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
