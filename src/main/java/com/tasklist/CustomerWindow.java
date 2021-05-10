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

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
		
		JTextField jtfMail=new JTextField("mail",10);
		JTextField jtfCompany=new JTextField("company",10);
		JTextField jtfManager=new JTextField("manager",10);
		JTextField jtfComments=new JTextField("comments",10);
		
		mainPanel.add(addCustomer, BorderLayout.NORTH);
		mainPanel.add(listCustomer, BorderLayout.CENTER);
		mainPanel.add(statusCustomer, BorderLayout.SOUTH);
		
		leftAddCustomer.add(jtfMail);
		leftAddCustomer.add(jtfCompany);
		leftAddCustomer.add(jtfManager);
		leftAddCustomer.add(jtfComments);
		rightAddCustomer.add(new JButton("Добавить"));
		
		rightAddCustomer.add(new JButton("Удалить"));
		
		northListCustomer.add(new JScrollPane(new JList()));
		leftSouthListCustomer.add(new JTextArea(2, 50));
		rightSouthListCustomer.add(new JButton("Отправить"));
		rightSouthListCustomer.add(new JLabel("Status"));
		
		statusCustomer.add(new JLabel("mail"));
		statusCustomer.add(new JLabel("company"));
		statusCustomer.add(new JLabel("manager"));
		statusCustomer.add(new JLabel("comments"));
		
		
		jf.getContentPane().add(mainPanel);
		

	}
	
	
	
	
}
