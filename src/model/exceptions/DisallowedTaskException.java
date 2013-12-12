package model.exceptions;

import model.tasks.Task;

public class DisallowedTaskException extends RuntimeException {

	private static final long serialVersionUID = -4791950896100974091L;
	private Task sourceTask;
	private String message;

	public DisallowedTaskException( Task task, String message ) {
		this.sourceTask = task;
		this.message = message;
	}
	
	public Task getSourceTask() {
		return this.sourceTask;
	}
	
	public String getMessage() {
		return this.message;
	}
}
