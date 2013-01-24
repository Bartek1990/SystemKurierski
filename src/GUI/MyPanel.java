package GUI;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics; 
import java.awt.image.BufferedImage; 
import java.io.File; 
import java.io.IOException; 
import java.util.logging.Level; 
import java.util.logging.Logger; 
import javax.imageio.ImageIO; 
import javax.swing.JLabel;
import javax.swing.JPanel; 

public class MyPanel extends JPanel 
{ 
	
	private BufferedImage image; 
	JLabel zal = new JLabel();
	
	public MyPanel(boolean log) 
	{ 
		try 
		{ 
			if(log==true)
			{
			image = ImageIO.read(new File("images/tlo2.png")); 
			}
			else image = ImageIO.read(new File("images/tlo3.png")); 
		} 
		catch (IOException ex) 
		{ 
			System.err.println("Brak Obrazka");
		} 
		this.setLayout(null);
		
		// INFORMACJA NA PANELU CZY ZALOGOWANY CZY NIE
		zal.setBounds(380,60,400,100);
		if(log==true)
		{
			
			zal.setForeground(Color.GREEN);
			zal.setText("ZALOGOWANY");
		}
		else
		{
			zal.setForeground(Color.gray);
			zal.setText("NIEZALOGOWANY");
		}
		this.add(zal);
		
	} 
			
	@Override public void paintComponent(Graphics g) 
			{ 
				super.paintComponent(g); g.drawImage(image, 0, 0, null);
		    } 
}
		
	
