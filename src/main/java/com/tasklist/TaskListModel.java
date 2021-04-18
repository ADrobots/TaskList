package com.tasklist;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;

import javax.swing.AbstractListModel;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;



public class TaskListModel extends AbstractListModel<String>{
private TaskList list;
//private JSONObject object;
//private JSONArray array;
//private JSONParser parser;
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
		/*FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
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
			}*/
		
	}
	
	public void read() throws FileNotFoundException, IOException/*, ParseException*/ {
		/*String path=this.getClass().getClassLoader().getResource("com/tasklist/Tasks.json").getPath();
		System.out.println(path);
		String decodePath=URLDecoder.decode(path, "utf-8");
		System.out.println(decodePath);
		System.out.println(getClass().getResource("/Tasks.json").getFile());
		
		
		String put=getClass().getResource("/Tasks.json").toString();*/
		
		/*InputStream in = getClass().getResourceAsStream("/Tasks.json");
		BufferedReader input = new BufferedReader(new InputStreamReader(in));
		String line;
		while ((line = input.readLine()) != null) {
			System.out.println(line);
		}*/
		
		
	
        
		//FileReader reader = new FileReader("/home/tony/eclipse-workspace/TaskList/target/classes/com/tasklist/Tasks.json");
		//FileReader reader = new FileReader("jar:"+decodePath); 
		//FileReader reader = new FileReader("jar:"+getClass().getResource("/Tasks.json").getPath()); 
		//FileReader reader = new FileReader("../com/tasklist/Tasks.txt");
		
        /*JSONParser jsonParser = new JSONParser();
        JSONArray a = (JSONArray) jsonParser.parse("[{\"info\":\"99\"},{\"info\":\"2\"},{\"info\":\"3\"}]");
		
		for (Object o : a)
		  {
		    JSONObject person = (JSONObject) o;

		    String name = (String) person.get("info");
		    list.add(name);
		    //System.out.println(name);
		  }*/
		
		//BufferedReader br = new BufferedReader(new FileReader(getClass().getResource("/Tasks.json").getPath()));
		InputStream in = getClass().getResourceAsStream("/Tasks.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(br).getAsJsonArray();
		System.out.println(array);
		
		for (Object o : array)
		  {
			JsonObject person = (JsonObject) o;

		    String name = person.get("info").getAsString();
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
