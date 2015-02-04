import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


public class HeaderPanel extends JPanel{

	BufferedImage accelx;
	BufferedImage accely;
	BufferedImage one;
	BufferedImage two;
	BufferedImage three;
	BufferedImage zero;
	
	
	
	public HeaderPanel()
	{	
		try{
			accelx = ImageIO.read(new File("accelx3.png"));
			accely = ImageIO.read(new File("accely3.png"));
			one = ImageIO.read(new File("1l.gif"));
			two = ImageIO.read(new File("2l.gif"));
			three = ImageIO.read(new File("3l.gif"));
			zero = ImageIO.read(new File("0l.gif"));
		}
		catch(Exception e){
			
		}
}
	
	
	public void paintComponent(Graphics g)
	{
		g.drawImage(accelx,  0,  0,  getWidth()/3,  getHeight()/2, null);
		g.drawImage(accely,  getWidth()/3 ,  0,  getWidth()/3,  getHeight()/2, null);
		g.drawImage(three,  getWidth()*2/3 ,  0,  getWidth()/3,  getHeight()/2, null);
	}
	
}
