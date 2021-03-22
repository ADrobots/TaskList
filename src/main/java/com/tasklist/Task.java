package com.tasklist;

public class Task {
	private static int count=0;
	public int id=0;
	public String info;
	
	public Task(String info) {
		id=count++;
		this.info=info;
	}
}
