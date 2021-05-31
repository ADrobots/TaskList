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

public class CustomerWindow{
	
	private JLabel mailLabelCustomer;
	private JButton createCustomer;
	private JButton deleteCustomer;
	private JButton sendCustomer;
	private JTextField mailCustomer;
	private JTextField companyCustomer;
	private JList jList;
	private DefaultListModel<String> model;
	
	public CustomerWindow() {
		
		JFrame jf=new JFrame("Hello");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				model.addElement(mailCustomer.getText()+" "+companyCustomer.getText());
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
		leftSouthListCustomer.add(new JTextArea(2, 50));
		
		sendCustomer=new JButton("Send");
		rightSouthListCustomer.add(sendCustomer);
		sendCustomer.addMouseListener(new MouseListener() {
			
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				//model.remove(jList.getSelectedIndex());
				
				System.out.println(model.get(jList.getSelectedIndex()));
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
		
		mailLabelCustomer=new JLabel();
		mailLabelCustomer.setName(model.get(jList.getSelectedIndex()));
		statusCustomer.add(mailLabelCustomer);
		statusCustomer.add(new JLabel("company"));
		statusCustomer.add(new JLabel("manager"));
		statusCustomer.add(new JLabel("comments"));
		
		southStatusCustomer.add(new JTextField("Find...",10));
		southStatusCustomer.add(new JButton("Search"));
		
		jf.getContentPane().add(mainPanel);
		

	}
	
	
	
	
}
