package com.tasklist;

import static javax.swing.BorderFactory.createEmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import javax.mail.MessagingException;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CustomerWindow{
	
	private JTextField mailTextCustomer;
	private JTextField findCustomer;
	private JTextArea sendTextArea;
	private JButton searchCustomer;
	private JButton createCustomer;
	private JButton deleteCustomer;
	private JButton sendCustomer;
	private JTextField mailCustomer;
	private JTextField companyCustomer;
	private JList jList;
	private DefaultListModel<String> model;
	private JsonArray array;
	private JsonObject object;
	
	Sender tlsSender;
	
	public void writeCustomer() throws IOException{
		File dataJson=new File("CustomersSource.json");
    	FileWriter files=new FileWriter(dataJson);
			
		
		array=new JsonArray();
		try {
				for(int i = 0; i <=jList.getModel().getSize()-1; i++) {
					object=new JsonObject();
					object.addProperty("info", String.valueOf(jList.getModel().getElementAt(i)));
					array.add(object);
				}
				files.write(array.toString());
		
			}catch(IOException e) {
				e.printStackTrace();
			}finally {
				files.flush();
				files.close();
			}
	}
	
	public void addToArchive() throws IOException{
		Runtime rt1 = Runtime.getRuntime();
		rt1.exec(new String[]{"cmd.exe","/c",/*"start",*/"jar -uf TaskList-jar-with-dependencies.jar Customers.json"});
	}
	
	public void readCustomer() throws FileNotFoundException, IOException{
		InputStream in = getClass().getResourceAsStream("/Customers.json");
		BufferedReader br = new BufferedReader(new InputStreamReader(in));
		JsonParser parser = new JsonParser();
		JsonArray array = parser.parse(br).getAsJsonArray();
		System.out.println(array);
		
		for (Object o : array)
		  {
			JsonObject person = (JsonObject) o;

		    String name = person.get("info").getAsString();
		    //list.add(name);
		    //System.out.println(name);
		    model.addElement(name);
		  }
		br.close();
	}
	
	public CustomerWindow() {
		
		JFrame jf=new JFrame("Hello");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		jf.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent){
        		
            	/*for (int i = 0; i <=jList.getModel().getSize()-1; i++) {
					
		             System.out.println(String.valueOf(jList.getModel().getElementAt(i)));
		        }*/
            	
            	try {
					writeCustomer();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            	/*try {
					addToArchive();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
                System.out.println("Hello closing");
				
            }
        });
		
		jf.setPreferredSize(new Dimension(300,300));
		jf.setVisible(true);
		jf.pack();
		
		JPanel mainPanel=new JPanel();
		mainPanel.setBorder(BorderFactory.createEmptyBorder(50, 10, 0, 50));
		mainPanel.setBorder(BorderFactory.createLineBorder(Color.green));
		mainPanel.setLayout(new BorderLayout());

		JPanel addCustomer=new JPanel();
		addCustomer.setLayout(new BoxLayout(addCustomer, BoxLayout.X_AXIS));
		JPanel leftAddCustomer=new JPanel();
		addCustomer.add(leftAddCustomer);
		JPanel rightAddCustomer=new JPanel();
		addCustomer.add(rightAddCustomer);
		//leftAddCustomer.setBorder(BorderFactory.createLineBorder(Color.black));
		leftAddCustomer.setLayout(new GridLayout(0,1));
		rightAddCustomer.setLayout(new GridLayout(0,1));
		
		/*JPanel listCustomer=new JPanel();
		listCustomer.setBorder(BorderFactory.createLineBorder(Color.black));*/
		JSplitPane listCustomer=new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JPanel northListCustomer=new JPanel();
		northListCustomer.setLayout(new BoxLayout(northListCustomer, BoxLayout.X_AXIS));
		JPanel southListCustomer=new JPanel();
		southListCustomer.setLayout(new BoxLayout(southListCustomer, BoxLayout.X_AXIS));
		JPanel leftSouthListCustomer=new JPanel();
		JPanel rightSouthListCustomer=new JPanel();
		southListCustomer.add(leftSouthListCustomer);
		southListCustomer.add(rightSouthListCustomer);
		leftSouthListCustomer.setLayout(new GridLayout(0,1));
		rightSouthListCustomer.setLayout(new GridLayout(0,1));
		listCustomer.add(northListCustomer);
		listCustomer.add(southListCustomer);
		
		JPanel statusCustomer=new JPanel();
		statusCustomer.setBorder(BorderFactory.createLineBorder(Color.black));
		statusCustomer.setLayout(new GridLayout(0,1));
		
		JPanel southStatusCustomer=new JPanel();
		southStatusCustomer.setLayout(new BoxLayout(southStatusCustomer, BoxLayout.X_AXIS));
		southStatusCustomer.setLayout(new GridLayout(0,2));
		statusCustomer.add(southStatusCustomer, BorderLayout.SOUTH);
		
		//JTextField jtfMail=new JTextField("mail",10);
		//JTextField jtfCompany=new JTextField("company",10);
		JTextField jtfManager=new JTextField("manager",10);
		JTextField jtfComments=new JTextField("comments",10);
		
		mainPanel.add(addCustomer, BorderLayout.NORTH);
		mainPanel.add(listCustomer, BorderLayout.CENTER);
		mainPanel.add(statusCustomer, BorderLayout.SOUTH);
		
		mailCustomer=new JTextField("mail",10);
		leftAddCustomer.add(mailCustomer);
		companyCustomer=new JTextField("company",10);
		leftAddCustomer.add(companyCustomer);
		leftAddCustomer.add(jtfManager);
		leftAddCustomer.add(jtfComments);
		
		createCustomer=new JButton("ADD customer");
		rightAddCustomer.add(createCustomer);
		createCustomer.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e){
				// TODO Auto-generated method stub
				model.addElement(mailCustomer.getText()+" "+companyCustomer.getText());	
				try {
					writeCustomer();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			

			});
		
		
		
		deleteCustomer=new JButton("Delete customer");
		rightAddCustomer.add(deleteCustomer);
		deleteCustomer.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				model.remove(jList.getSelectedIndex());
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			

			});
		
		model = new DefaultListModel<String>();
		jList=new JList(model);
		northListCustomer.add(new JScrollPane(jList));
		try {
			readCustomer();
		} catch (FileNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		
		sendTextArea=new JTextArea(2,50);
		leftSouthListCustomer.add(sendTextArea);
		
		tlsSender=new Sender("prommetall66@gmail.com", "ronaldo_85");
		sendCustomer=new JButton("Send");
		rightSouthListCustomer.add(sendCustomer);
		sendCustomer.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//model.remove(jList.getSelectedIndex());
				String str=model.get(jList.getSelectedIndex());
				String[] arrStr=str.split(" ");
				
				System.out.println(arrStr[0]);
				
				try {
					tlsSender.send("", sendTextArea.getText(), "prommetall66@gmail.com", arrStr[0]);
				} catch (MessagingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			

			});
		rightSouthListCustomer.add(new JLabel("Status"));
		
		//mailTextCustomer=new JTextField("-",15);
		//mailTextCustomer.setEditable(false);
		//statusCustomer.add(mailTextCustomer);
		//statusCustomer.add(new JLabel("company"));
		//statusCustomer.add(new JLabel("manager"));
		final JTextArea commentsTextAreaCustomer=new JTextArea(2,8);
		commentsTextAreaCustomer.setEditable(false);
		statusCustomer.add(commentsTextAreaCustomer);
		
		findCustomer=new JTextField("Find...",10);
		southStatusCustomer.add(findCustomer);
		searchCustomer=new JButton("Search");
		southStatusCustomer.add(searchCustomer);
		searchCustomer.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//mailTextCustomer.setText(findCustomer.getText());
				//jList.setSelectedIndex(1);
				commentsTextAreaCustomer.setText("");
				String str1=findCustomer.getText();
				for (int i = 0; i <=jList.getModel().getSize()-1; i++) {
					String str2=String.valueOf(jList.getModel().getElementAt(i));
					
					if(str2.contains(str1)) {
						//mailTextCustomer.setText(str2);
						//System.out.println("find");
						
						commentsTextAreaCustomer.append(str2+"\n");
						jList.setSelectedIndex(i);
						
					}/*else {
						//mailTextCustomer.setText("-");
						commentsTextAreaCustomer.setText("-");
					}*/
		             //System.out.println(String.valueOf(jList.getModel().getElementAt(i)));
		        }
					
	
				
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			

			});
		
		
		jf.getContentPane().add(mainPanel);
		

	}
	
	
	
	
}
