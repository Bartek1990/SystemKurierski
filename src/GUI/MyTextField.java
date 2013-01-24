package GUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JTextField;
import javax.swing.JPanel;

public class MyTextField extends JTextField
{

	MyTextField(int liczba,int x, int y, int szer, int wys, JPanel panel)
	{
		super(liczba);
		this.setBounds(x, y,szer,wys);
		this.setFont(new Font("Arial", Font.BOLD, 12));
		this.setForeground(Color.black);
		this.setBackground(Color.GRAY);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.add(this);
	}
	
}
