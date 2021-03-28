package com.tasklist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class TaskListModel extends AbstractListModel<String>{
private TaskList list;
private JSONObject object;
private JSONArray array;
private JSONParser parser;
private static final String filePath = "./src/main/resources/Tasks.json";
//private static final String filePath = "./target/classes/Tasks.json";

	
	public TaskListModel(TaskList list) {
		this.list = list;
	}
	
	public void removeAt(int i) {
		list.removeAt(i);
		fireContentsChanged(this, i, i);
	}
	
	public void add(String task) {
		list.add(task);
		fireContentsChanged(this,
				list.size() - 1, list.size() - 1);
	}
	
	public void write() throws IOException {
		FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
		//FileWriter file=new FileWriter("./target/classes/Tasks.json");
		array=new JSONArray();
		try {
				for(int i=0; i<list.size(); i++) {
					object=new JSONObject();
					object.put("info", list.elementAt(i));
					array.add(object);
				}
				file.write(array.toString());
		
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				file.flush();
				file.close();
			}
		
	}
	
	public void read() throws FileNotFoundException, IOException, ParseException {
        
		FileReader reader = new FileReader(filePath);
		 
        JSONParser jsonParser = new JSONParser();
        JSONArray a = (JSONArray) jsonParser.parse(reader);
		
		for (Object o : a)
		  {
		    JSONObject person = (JSONObject) o;

		    String name = (String) person.get("info");
		    list.add(name);
		    //System.out.println(name);
		  }
		
	}
	
	public int getSize() {
		return list.size();
	}
	public String getElementAt(int index) {
		return list.elementAt(index);
	}
}
