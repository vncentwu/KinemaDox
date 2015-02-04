import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;




public class Runner {

	public static int width;
	public static int height;
	public static boolean running = true;
	
	public static void main(String[] args) {
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); 
		width = (int)(dim.getWidth());
		height = (int)(dim.getHeight());
		
		JFrame frame = new JFrame("WUCHENWIN");		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize((int)dim.getWidth(),(int)dim.getHeight());
		frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);		
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.black);
		panel.setLayout(new BoxLayout(panel, BoxLayout.LINE_AXIS));
		GameScreen game = new GameScreen();
		game.setPreferredSize(new Dimension((int)(width/2), height));

		
		LeftScreen left = new LeftScreen();
		RightScreen right = new RightScreen();
		left.setPreferredSize(new Dimension((int)(width/4), height));
		right.setPreferredSize(new Dimension((int)(width/4), height));		
		
		game.setScreens(left, right);
		panel.add(left);
		panel.add(game);
		panel.add(right);
        
        frame.setContentPane(panel);
        frame.setVisible(true);
		game.checkSize();
		
		panel.repaint();
		game.requestFocus();
		
		while(running)
		{

			try{
				Thread.sleep(40);
				game.tick();
				left.tick();
				right.tick();
				panel.repaint();
				//game.repaint();
			}
			catch(Exception e)
			{
				
			}
		
		}
	}
	
	
	
	
	
}
