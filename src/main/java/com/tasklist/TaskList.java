package com.tasklist;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TaskList implements Iterable<String>{
	
	private List<String> list = new ArrayList<String>();

	public void add(String item) {
		list.add(item);

	}

	public void removeAt(int i) {
		if (i >= 0 && i < list.size()) {
			list.remove(i);
		}
	}
	
	public int size() {
		return list.size();
	}
	
	public String elementAt(int i) {
		return list.get(i);
	}
	
	public Iterator<String> iterator() {
		return list.iterator();
	}
}
