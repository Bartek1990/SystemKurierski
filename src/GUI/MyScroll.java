package GUI;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class MyScroll extends JScrollPane{
	
   
	
	MyScroll(JList lista, JPanel panel)
	{
		super(lista);
		this.setBounds(320,200,400,220);
		panel.add(this);
		//this.setBackground(Color.green);
	}
	


}
