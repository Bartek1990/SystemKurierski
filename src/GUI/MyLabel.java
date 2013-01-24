package GUI;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class MyLabel extends JLabel
{
	
	MyLabel(String text,int x, int y, int szer, int wys, JPanel panel)
	{
		super(text);
		this.setBounds(x, y,szer,wys);
		this.setFont(new Font("Arial", Font.BOLD, 12));
		this.setForeground(Color.white);
		panel.add(this);
	}
	
	MyLabel(String text,int x, int y, int szer, int wys)
	{
		super(text);
		this.setBounds(x, y,szer,wys);
		this.setFont(new Font("Arial", Font.BOLD, 12));
		this.setForeground(Color.white);
	}
	
	MyLabel(String text,String color, JPanel panel) // Konstruktor dla komunikatu
	{
		super(text);
		this.setBounds(420,300,340,20);
		this.setFont(new Font("Arial", Font.BOLD, 16));
		if(color=="RED")this.setForeground(Color.red);
		if(color=="GREEN")this.setForeground(Color.green);
		else this.setForeground(Color.red);
		panel.add(this);
	}
	
	MyLabel(String text,String color) // Konstruktor dla komunikatu niewidocznych (aktywonwanych przyciskiem)
	{
		super(text);
		this.setBounds(420,300,340,20);
		this.setFont(new Font("Arial", Font.BOLD, 16));
		if(color=="RED")this.setForeground(Color.red);
		if(color=="GREEN")this.setForeground(Color.green);
		else this.setForeground(Color.red);

	}

}
