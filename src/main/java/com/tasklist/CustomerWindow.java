package com.tasklist;

import static javax.swing.BorderFactory.createEmptyBorder;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CustomerWindow extends JFrame{
	
	private JPanel mainContentPane;	
	private JPanel newTaskControls;
	private JTextField newTaskField;
	private JButton addTaskButton;
	
	public CustomerWindow() {
		
		setTitle("Customers");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
		setMinimumSize(new Dimension(250, 250));
		setContentPane( getMainContentPane() );
	}
	
	private Container getMainContentPane() {
		if (mainContentPane == null) {
			mainContentPane = new JPanel();
			mainContentPane.setLayout(new BorderLayout());
			
			mainContentPane.add(getNewTaskControls(), BorderLayout.NORTH);
			
		}
		return mainContentPane;
	}
	private Component getNewTaskControls() {
		if (newTaskControls == null) {
			newTaskControls = new JPanel();
			
			BorderLayout layout = new BorderLayout();
			newTaskControls.setLayout(layout);
			layout.setHgap(5);
			newTaskControls.setBorder(createEmptyBorder(10,10,10,10));
			
			newTaskControls.add(getNewTaskField());
			newTaskControls.add(getAddTaskButton());
			newTaskControls.add(new JButton());
		}
		
		return newTaskControls;
	}
	
	private JTextField getNewTaskField() {
		if (newTaskField == null) {
			newTaskField = new JTextField();
		}
		newTaskField.setSize(50, 100);
		return newTaskField;
	}
	
	
	private JButton getAddTaskButton() {
        if (addTaskButton == null) {
            addTaskButton = new JButton("Add");
            //addTaskButton.setIcon(createIcon("diary.png"));

            addTaskButton.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getNewTaskField().getText().length() > 0) {
                        String task = getNewTaskField().getText().trim();

                        //todoListModel.add(getNewTaskField().getText().trim());
                        //sessionDao.save(task);
                        /*try {
							todoListModel.write();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/

                        getNewTaskField().setText("");

                        //getTaskList().setSelectedIndex(getTaskList().getModel().getSize()-1);
                    }
                }
            });
        }

        return addTaskButton;
	}
}
