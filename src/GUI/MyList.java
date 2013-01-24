package GUI;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class MyList extends JList{
	
	MyList(DefaultListModel modellisty)
	{
		
		super(modellisty);
		this.setBackground(Color.gray);
		this.setForeground(Color.white);
		//this.setBackground(Color.green);
	}

}
