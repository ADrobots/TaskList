package com.tasklist;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

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
private JsonObject object;
private JsonArray array;
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
	
	public void write() throws IOException/*, URISyntaxException*/ {
		/*FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
		
		array=new JsonArray();
		try {
				for(int i=0; i<list.size(); i++) {
					object=new JsonObject();
					object.addProperty("info", list.elementAt(i));
					array.add(object);
				}
				file.write(array.toString());
		
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				file.flush();
				file.close();
			}*/
		
		//-------------------------------------
		
		//String put=getClass().getResource("/Tasks.json").toString();
		//System.out.println(put);
		//URL resourceUrl = getClass().getResource("Tasks.json");
		//File file = new File(resourceUrl.toURI());
		//File file = new File("/home/tony/eclipse-workspace/TaskList/target/classes/Tasks.json");
		//OutputStream output = new FileOutputStream(file);
		//System.out.println(output.toString());
		
		/*String text = "Hello world!"; // строка для записи
		FileOutputStream fos = new FileOutputStream("file.txt");
		try { 
		    BufferedOutputStream bos = new BufferedOutputStream(fos);
		    // Переводим текст в байты
		    byte[] buffer = text.getBytes();
		    bos.write(buffer, 0, buffer.length);
		} catch(IOException e) {
		    System.out.println(e.getMessage());
		}*/
		
		JarFile jar=new JarFile("TaskList-jar-with-dependencies.jar");
		Enumeration enumEntries=jar.entries();
		//File direct=new File("direct");
		//direct.mkdir();

		while(enumEntries.hasMoreElements()) {
			JarEntry file=(JarEntry)enumEntries.nextElement();
			File f=new File(/*direct+File.separator+*/file.getName());
			if(file.isDirectory()) {
				f.mkdir();
				continue;
			}
			InputStream is=jar.getInputStream(file);
			FileOutputStream fos=new FileOutputStream(f);
			while(is.available()>0) {
				fos.write(is.read());
			}
			fos.close();
			is.close();
		}
		jar.close();
		
		File dataJson=new File(/*direct+*/"Tasks.json");
		FileWriter files=new FileWriter(dataJson);
		
		array=new JsonArray();
		try {
				for(int i=0; i<list.size(); i++) {
					object=new JsonObject();
					object.addProperty("info", list.elementAt(i));
					array.add(object);
				}
				files.write(array.toString());
		
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				files.flush();
				files.close();
			}
		
		List<String> lines = Arrays.asList("Manifest-Version: 1.0", "Archiver-Version: Plexus Archiver", "Created-By: Apache Maven", "Built-By: work", "Build-Jdk: 1.8.0_131", "Main-Class: com.tasklist.Run");
		Path file = Paths.get(/*direct+*/"manifest.txt");
		Files.write(file, lines, StandardCharsets.UTF_8);
		
		Runtime rt = Runtime.getRuntime();
		rt.exec(new String[]{"cmd.exe","/c",/*"start",*/"jar -cfm TaskList-jar-with-dependencies.jar manifest.txt com META-INF Tasks.json *.png"});
		
		
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
		br.close();
		
	}
	
	public int getSize() {
		return list.size();
	}
	public String getElementAt(int index) {
		return list.elementAt(index);
	}
}
