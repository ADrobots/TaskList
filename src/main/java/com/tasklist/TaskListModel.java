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

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class TaskListModel extends AbstractListModel<String>{
private TaskList list;
private JSONObject object;
private JSONArray array;
private JSONParser parser;
	
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
		//FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
		FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
		array=new JSONArray();
		try {
				for(int i=0; i<list.size(); i++) {
					object=new JSONObject();
					object.put("info", list.elementAt(i));
					array.put(object);
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
		
		File file=new File("./src/main/resources/Tasks.json");
		BufferedReader br = new BufferedReader(new FileReader(file));
		String st = br.readLine();
        String jsonFile = "";
        while (st != null) {
            jsonFile += st;
            st = br.readLine();
        }
        //List<String> list=Arrays.asList(jsonFile);
        //System.out.println(list);
        
        
		
		//JSONArray a = (JSONArray) parser.parse(new FileReader("./src/main/resources/Tasks.json"));
		//JSONObject a = (JSONObject) parser.parse(new FileReader("./target/classes/com/tasklist/Tasks.json"));
		/*for (Object o : a)
		  {
		    JSONObject person = (JSONObject) o;

		    String name = (String) person.get("info");
		    System.out.println(name);
		  }*/
		
	}
	
	public int getSize() {
		return list.size();
	}
	public String getElementAt(int index) {
		return list.elementAt(index);
	}
}
