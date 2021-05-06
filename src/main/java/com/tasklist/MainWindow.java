package com.tasklist;



import com.tasklist.TaskList;

import javax.swing.*;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;





import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static javax.swing.BorderFactory.createEmptyBorder;
import static javax.swing.Box.createVerticalStrut;



public class MainWindow extends JFrame{
	private static final long serialVersionUID = 1L;

	//private SessionDao sessionDao = new SessionDao(HibernateService.getSessionFactory());
	
	private JPanel mainContentPane;	
	private JPanel newTaskControls;
	private JButton addTaskButton;
	private JTextField newTaskField;
	private JScrollPane taskListScrollPane;
	private JPanel taskListControls;
	private JButton deleteButton;
	private JButton writeButton;
	private JButton viewCustomers;
	private JList<String> taskList;
	private JLabel statusBar;
		
	private TaskList todoList;
	private TaskListModel todoListModel;

	public MainWindow(){
		todoList = new TaskList();
		todoListModel = new TaskListModel(todoList);
		
		try {
			todoListModel.read();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		setContentPane( getMainContentPane() );
		
		setTitle("Todo list");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
            	
            	todoListModel.deleteFile(new File("com"));        	
            	todoListModel.deleteFile(new File("META-INF"));
        		
                System.out.println("Frame closing");
				
            }
        });
		
		setMinimumSize(new Dimension(250, 250));
		pack();
	}

	private Container getMainContentPane() {
		if (mainContentPane == null) {
			mainContentPane = new JPanel();
			mainContentPane.setLayout(new BorderLayout());
			
			mainContentPane.add(getNewTaskControls(), BorderLayout.NORTH);
			mainContentPane.add(getTasksListScrollPane(), BorderLayout.CENTER);
			mainContentPane.add(getTasksListControls(), BorderLayout.EAST);
			//mainContentPane.add(getStatusBar(), BorderLayout.SOUTH);
			
		}
		return mainContentPane;
	}

	private Component getNewTaskControls() {
		if (newTaskControls == null) {
			newTaskControls = new JPanel();
			
			BorderLayout layout = new BorderLayout();
			newTaskControls.setLayout(layout);
			layout.setHgap(5);
			newTaskControls.setBorder(createEmptyBorder(10,0,10,10));
			
			newTaskControls.add(getNewTaskField(), BorderLayout.CENTER);
			newTaskControls.add(getAddTaskButton(), BorderLayout.EAST);
		}
		
		return newTaskControls;
	}

	private JTextField getNewTaskField() {
		if (newTaskField == null) {
			newTaskField = new JTextField();
		}
		return newTaskField;
	}

	private Component getTasksListScrollPane() {
		if (taskListScrollPane == null) {
			taskListScrollPane = new JScrollPane(getTaskList());
		}
		return taskListScrollPane;
	}

	private JList<String> getTaskList() {
		if (taskList == null) {
			taskList = new JList<String>();
			taskList.setModel(todoListModel);
		}
		return taskList;
	}

	private Component getTasksListControls() {

		if (taskListControls == null) {
			taskListControls = new JPanel();
			
			BoxLayout layout = new BoxLayout(taskListControls, BoxLayout.Y_AXIS);
			taskListControls.setLayout(layout);
			taskListControls.setBorder(createEmptyBorder(5, 5, 5, 5));
			
			taskListControls.add(createVerticalStrut(10));

			JButton button = getDeleteButton();
			button.setAlignmentX(CENTER_ALIGNMENT);
			taskListControls.add(button);
			
			JButton button2 = getWriteButton();
			button2.setAlignmentX(CENTER_ALIGNMENT);
			taskListControls.add(button2);
			
			JButton button3 = viewCustomers();
			button3.setAlignmentX(CENTER_ALIGNMENT);
			taskListControls.add(button3);
			
			
			
			taskListControls.add(createVerticalStrut(10));
		}
		
		return taskListControls;
	}


	private JButton getDeleteButton() {
		if (deleteButton == null) {
			deleteButton = new JButton("Delete");
			deleteButton.setIcon(createIcon("bin.png"));

			deleteButton.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					int removeIndex = getTaskList().getSelectedIndex();
					String task = todoListModel.getElementAt(removeIndex);
					todoListModel.removeAt(getTaskList().getSelectedIndex());
					//sessionDao.delete(task);
					/*try {
						todoListModel.write();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}*/
				}
			});
		}

		return deleteButton;
	}

	private JButton getWriteButton() {
		if (writeButton == null) {
			writeButton = new JButton("Write");
			writeButton.setIcon(createIcon("diary.png"));

			writeButton.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e) {
					try {
						todoListModel.write();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}

		return writeButton;
	}

    private JButton getAddTaskButton() {
        if (addTaskButton == null) {
            addTaskButton = new JButton("Add");
            addTaskButton.setIcon(createIcon("diary.png"));

            addTaskButton.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (getNewTaskField().getText().length() > 0) {
                        String task = getNewTaskField().getText().trim();

                        todoListModel.add(getNewTaskField().getText().trim());
                        //sessionDao.save(task);
                        /*try {
							todoListModel.write();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}*/

                        getNewTaskField().setText("");

                        getTaskList().setSelectedIndex(getTaskList().getModel().getSize()-1);
                    }
                }
            });
        }

        return addTaskButton;
    }

    private JButton viewCustomers() {
    	if (viewCustomers == null) {
    		viewCustomers = new JButton("Customers");

    		viewCustomers.addMouseListener(new MouseAdapter(){
                @Override
                public void mouseClicked(MouseEvent e) {
                    /*JFrame f=new JFrame();
                    f.setTitle("Customers");
                    f.setVisible(true);
                    
                    JPanel fp=new JPanel();*/
                	
                	new CustomerWindow();
                    
                }
            });
        }
    	return viewCustomers;
    }
    
	/*private JLabel getStatusBar() {
		if (statusBar == null) {
			statusBar = new JLabel("Number of tasks: 0");
			todoListModel.addListDataListener(new ListDataListener() {
				@Override
				public void contentsChanged(ListDataEvent e) {
					updateLabel(e);
				}
				
				private void updateLabel(ListDataEvent e) {
					getStatusBar().setText("Number of tasks: "+((TodoListModel)e.getSource()).getSize());
				}
				
				@Override
				public void intervalRemoved(ListDataEvent e) {}
				@Override
				public void intervalAdded(ListDataEvent e) {}
			});
		}
		
		return statusBar;
	}*/
	
	private Icon createIcon(String iconfilename) {
		System.out.println(getClass().getResource("/"+iconfilename));
		return new ImageIcon(
				getClass().
				getResource("/"+iconfilename));
	}

	/*public void initialize(){
		List<Task> tasks=sessionDao.getall();
		for (Task task: tasks){
			todoListModel.add(task.getTask());
		}
	}*/
	
}
