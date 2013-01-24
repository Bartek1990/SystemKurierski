package GUI;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MyButton extends JButton{
	
	MyButton(String nazwa,int x, int y, final int szer, final int wys, JPanel panel)
	{
		super(nazwa,new ImageIcon(((new ImageIcon("images/button.jpg")).getImage()).getScaledInstance(szer, wys, java.awt.Image.SCALE_SMOOTH)));
		this.setHorizontalTextPosition(SwingConstants.CENTER);
		this.setBounds(x,y,szer,wys);
		this.setBorderPainted(false);
		this.setForeground(Color.white);
		panel.add(this);
		
		this.addMouseListener(new MouseListener()
		{
		        public void mouseEntered(MouseEvent e)
		        {
		        	setIcon(new ImageIcon(((new ImageIcon("images/button2.jpg")).getImage()).getScaledInstance(szer, wys, java.awt.Image.SCALE_SMOOTH)));
		        }

				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO Auto-generated method stub
			
				}

				@Override
				public void mouseExited(MouseEvent arg0) 
				{
					setIcon(new ImageIcon(((new ImageIcon("images/button.jpg")).getImage()).getScaledInstance(szer, wys, java.awt.Image.SCALE_SMOOTH)));
				}

				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void mouseReleased(MouseEvent arg0) {
					// TODO Auto-generated method stub
					setIcon(new ImageIcon(((new ImageIcon("images/button.jpg")).getImage()).getScaledInstance(szer, wys, java.awt.Image.SCALE_SMOOTH)));
				}
		});
	}
	
	


}
