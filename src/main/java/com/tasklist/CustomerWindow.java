package com.tasklist;

import static javax.swing.BorderFactory.createEmptyBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerWindow{
	
	private JFrame jf;
	private JPanel panelAddCustomer;
	private JPanel panelListCustomers;
	
	private JTextField textFieldEmail;
	private JTextField textFieldCompany;
	private JTextField textFieldCustomer;
	private JTextField textFieldInformation;
	
	private JButton button;
	
	public CustomerWindow() {
		
		jf=new JFrame();
		jf.setTitle("Customers");
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setVisible(true);
		jf.setMinimumSize(new Dimension(550, 550));
		
		
		panelAddCustomer=new JPanel();
		panelAddCustomer.setLayout(new FlowLayout(FlowLayout.LEFT,3,3)); //Components aligned to left
		textFieldEmail=new JTextField("Email",30);
		textFieldCompany=new JTextField("Company",30);
		textFieldCustomer=new JTextField("Manager",30);
		textFieldInformation=new JTextField("Information",30);
		button=new JButton("Добавить");
		
		panelAddCustomer.add(textFieldEmail,BorderLayout.EAST);
		panelAddCustomer.add(textFieldCompany,BorderLayout.EAST);
		panelAddCustomer.add(textFieldCustomer,BorderLayout.EAST);
		panelAddCustomer.add(textFieldInformation,BorderLayout.EAST);
		panelAddCustomer.add(button);
		
		
		panelListCustomers=new JPanel();
		panelListCustomers.add(new JTextField(5));
		panelListCustomers.add(button);
		
		jf.add( panelAddCustomer );
		jf.add(panelListCustomers);
		

	}
	
	
	
	
}
