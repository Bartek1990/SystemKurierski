package GUI;

import javax.swing.DefaultListModel;
import javax.swing.JList;

public class MyListModel extends DefaultListModel{
	
	MyListModel()
	{
	}
	
	void Add(String text)
	{
		this.addElement(text);
	}
	
	void remove(JList list)
	{
		this.removeElement(list.getSelectedValue());
	}
	
	void removeall()
	{
		this.removeAllElements();
	}
}
