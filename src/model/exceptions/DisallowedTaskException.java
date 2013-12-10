package model.exceptions;

import model.tasks.Task;

public class DisallowedTaskException extends RuntimeException {

	private static final long serialVersionUID = -4791950896100974091L;
	private Task sourceTask;

	public DisallowedTaskException( Task task ) {
		this.sourceTask = task;
	}
	
	public Task getSourceTask() {
		return this.sourceTask;
	}
}
