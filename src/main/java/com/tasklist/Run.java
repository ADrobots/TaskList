package com.tasklist;

import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.tasklist.MainWindow;

public class Run {
	
	
	/*private static int count = 0;
	private int id=0;
	
	public Run() {
		id=count++;
	}
	
	
	public Object putTask(String info) {
		
		JSONObject obj=new JSONObject();
		obj.put("id", id);
		obj.put("info", info);
		
		return obj;
	}*/
	


	public static void main(String[] args) throws IOException {
				
		//array
		/*FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
		
		JSONArray array=new JSONArray();
		
		for(int i=0; i<11; i++) {
			array.put(new Run().putTask("Hello"));
		}
		
		try {
			
				file.write(array.toString());
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			file.flush();
			file.close();
		}
		System.out.println(array);*/
		
		
		//objects	
		/*FileWriter file=new FileWriter("./src/main/resources/Tasks.json");
		try {
			for(int i=0; i<11; i++) {
				file.write(new Run().putTask("Hello").toString());
			}	
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			file.flush();
			file.close();
		}*/
		MainWindow window = new MainWindow();
        window.setLocationRelativeTo( null );
        window.setVisible(true);
		
		
		
		


	}

}
