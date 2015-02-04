import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Transparency;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.RectangularShape;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JPanel;


public class GameScreen extends JPanel{

	Map map;
	public static  final double WIDTH_0 = 600;
	public static final double  HEIGHT_0 = 1000;
	int height_0;
	int width;
	int height;
	BufferedImage outer;
	BufferedImage grass;
	BufferedImage car;
	BufferedImage noo;
	BufferedImage border;
	BufferedImage finish;
	
	ArrayList<BufferedImage> rotations;
	
	LeftScreen left;
	RightScreen right;
	int tick;
	boolean north;
	boolean east;
	boolean south;
	boolean west;
	BufferedImage[] sprites;
	
	RotatedIcon vroom;
	
	public GameScreen()
	{
		map = new Map();
		height = Runner.height;
		width = (int)((double)Runner.width/2);
		addKeyListener(new KeyInput());
		vroom= new RotatedIcon(new ImageIcon("car2.png"), 45);
		
		rotations = new ArrayList<BufferedImage>();
		
		// The above line throws an checked IOException which must be caught.


		
		
		try{
			for(int i = 0; i< 360; i = i+10)
			{	
					rotations.add(ImageIO.read(new File("car" + i + ".png")));
			}
			System.out.println(rotations.size());
			outer = ImageIO.read(new File("outer.gif"));
			grass = ImageIO.read(new File("grass3.png"));
			car =  ImageIO.read(new File("car2.png"));
			border = ImageIO.read(new File("border.gif"));
			finish = ImageIO.read(new File("finishw2.png"));
			BufferedImage bigImg = ImageIO.read(new File("boom.png"));		
			final int width = 64;
			final int height = 64;
			final int rows = 4;
			final int cols = 4;
			sprites = new BufferedImage[rows * cols];
	
			for (int i = 0; i < rows; i++)
			{
			    for (int j = 0; j < cols; j++)
			    {
			        sprites[(i * cols) + j] = bigImg.getSubimage(
			            j * width,
			            i * height,
			            width,
			            height
			        );
			    }
			}
		}
		catch(Exception e)
		{
			
		}
		noo = rotate(car, 45);
	}
	
	public void setScreens(LeftScreen left, RightScreen right)
	{
		this.left = left;
		left.setCar(map.getCar(1));
		this.right = right;
		right.setCar(map.getCar(2));
	}
	
	public void checkSize()
	{
		height = getHeight();
		width = getWidth();
		System.out.println("real" + getHeight() + "," + getWidth());
	}
	
	public void tick()
	{
		tick++;
		map.tick();
		Car car = map.getCar(1);
		if(north)
			car.north();
		if(south)
			car.south();
		if(west)
			car.west();
		if(east)
			car.east();

	}
	
	public RectangularShape convert(RectangularShape s)
	{
		double width_multiplier =  width/WIDTH_0;
		double height_multiplier = height/HEIGHT_0;

		
		RectangularShape shape = (RectangularShape)((s.clone()));
		shape.setFrame((int)(shape.getX()*width_multiplier), (int)(shape.getY() * height_multiplier), (int)(shape.getWidth()*width_multiplier), (int)(shape.getHeight() * height_multiplier));

		return shape;
	}
	
	public int convert_x(int x)
	{
		double width_multiplier =  width/WIDTH_0;
		double height_multiplier = height/HEIGHT_0;
		
		return (int)(x * width_multiplier);
	}
	
	public int convert_y(int y)
	{
		double width_multiplier =  width/WIDTH_0;
		double height_multiplier = height/HEIGHT_0;
		
		return (int)(y * height_multiplier);
	}
	
	public void paintComponent(Graphics x)
	{
		Graphics2D g = (Graphics2D) x;
		Color green = new Color(36, 89, 0);
		Color gray = new Color(59, 59, 59);
		
		g.setStroke(new BasicStroke(7));
		g.setColor(green);
		g.fillRect(0,0,getWidth(), getHeight());
		//g.drawImage(grass, 0, 0, getWidth(), getHeight(), null);
		
		
		g.setColor(gray);
		RectangularShape bound1 = map.getBound(1);
		g.fill(convert(bound1));
		
		g.setColor(Color.red);
		g.drawImage(finish, convert_x((int)map.getFinish().getX()), convert_y((int)map.getFinish().getY()), convert_x((int)map.getFinish().getWidth()), convert_y((int)map.getFinish().getHeight()), null);
		
		g.setColor(Color.black);
		g.draw(convert(bound1));
		//g.drawImage(outer, (int)convert(bound1).getX(), (int)convert(bound1).getY(), (int)convert(bound1).getWidth(), (int)convert(bound1).getHeight(), null);

		g.setColor(green);
		RectangularShape bound2 = map.getBound(2);
		g.fill(convert(bound2));
		
		g.setColor(Color.black);
		g.draw(convert(bound2));
		//g.drawImage(outer, (int)convert(bound2).getX(), (int)convert(bound2).getY(), (int)convert(bound2).getWidth(), (int)convert(bound2).getHeight(), null);
		
		Car one = map.getCar(1);
		if(one!=null)
		{
			g.setStroke(new BasicStroke(1));
			

			
			g.setColor(Color.red);
			
			//System.out.println(one.getDirection());
			car = rotations.get(one.getDirection());
			double dir = one.getDirection() * 10;
			dir = Math.toRadians(dir);
			//double cos = Math.cos(dir);
			//double sin
			if(!one.hit && one.boomSteps >0)
				g.drawImage(sprites[12-(int)(one.boomSteps/3)], convert_x(one.getX()) - convert_x((int)(one.getWidth()/2)), convert_y(one.getY()), convert_x((int)(one.getWidth()*2)), convert_y((int)(one.getHeight())), null);
			else if(one.hit)
				g.drawImage(car, convert_x(one.getX()) - convert_x((int)(one.getWidth()/2)), convert_y(one.getY()), convert_x((int)(one.getWidth()*2)), convert_y((int)(one.getHeight())), null);
			
			
			//g.drawRect(convert_x(one.getX()), convert_y(one.getY()), convert_x(one.getWidth()), convert_y(one.getHeight()));
			g.setColor(Color.white);
			g.drawString("(" + one.getX() + "," + one.getY() + ")", convert_x(one.getX()), convert_y(one.getY()));
			
			//g.drawImage(border, 0, 0, (int)getWidth()/100, (int)getHeight(), null);
			//g.drawImage(border, getWidth() - (int)getWidth()/100, 0, (int)getWidth()/100, (int)getHeight(), null);
		}
		one = map.getCar(2);
		if(one!=null)
		{
			g.setStroke(new BasicStroke(1));
			

			
			g.setColor(Color.red);
			
			//System.out.println(one.getDirection());
			car = rotations.get(one.getDirection());
			double dir = one.getDirection() * 10;
			dir = Math.toRadians(dir);
			//double cos = Math.cos(dir);
			//double sin
			if(!one.hit && one.boomSteps >0)
				g.drawImage(sprites[12 - (int)(one.boomSteps/3)], convert_x(one.getX()) - convert_x((int)(one.getWidth()/2)), convert_y(one.getY()), convert_x((int)(one.getWidth()*2)), convert_y((int)(one.getHeight())), null);
			else if(one.hit)
				g.drawImage(car, convert_x(one.getX()) - convert_x((int)(one.getWidth()/2)), convert_y(one.getY()), convert_x((int)(one.getWidth()*2)), convert_y((int)(one.getHeight())), null);
			
			
			//g.drawRect(convert_x(one.getX()), convert_y(one.getY()), convert_x(one.getWidth()), convert_y(one.getHeight()));
			g.setColor(Color.white);
			g.drawString("(" + one.getX() + "," + one.getY() + ")", convert_x(one.getX()), convert_y(one.getY()));
			
			//g.drawImage(border, 0, 0, (int)getWidth()/100, (int)getHeight(), null);
			//g.drawImage(border, getWidth() - (int)getWidth()/100, 0, (int)getWidth()/100, (int)getHeight(), null);
		}		
		
		
		
		g.drawString("" + map.getCar(1).hit, 20, 20);
		
		
		//g.drawImage(grass, 0, 0, getWidth(), getHeight(), null);
		
	}
	
	public BufferedImage rotate(BufferedImage image, double angle) {
	    double sin = Math.abs(Math.sin(angle)), cos = Math.abs(Math.cos(angle));
	    int w = image.getWidth();
	    int h = image.getHeight();
	    int neww = (int)Math.floor(w*cos+h*sin), newh = (int)Math.floor(h*cos+w*sin);
	    GraphicsConfiguration gc = getDefaultConfiguration();
	    BufferedImage result = gc.createCompatibleImage(neww, newh, Transparency.TRANSLUCENT);
	    Graphics2D g = result.createGraphics();
	    g.translate((neww-w)/2, (newh-h)/2);
	    g.rotate(angle, w*2, h*2);
	    g.drawRenderedImage(image, null);
	    g.dispose();
	    return result;
	}

    public static GraphicsConfiguration getDefaultConfiguration() {
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        return gd.getDefaultConfiguration();
    }
	
    static Image iconToImage(Icon icon) {
        if (icon instanceof ImageIcon) {
            return ((ImageIcon)icon).getImage();
        } else {
            int w = icon.getIconWidth();
            int h = icon.getIconHeight();
            GraphicsEnvironment ge =
              GraphicsEnvironment.getLocalGraphicsEnvironment();
            GraphicsDevice gd = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gd.getDefaultConfiguration();
            BufferedImage image = gc.createCompatibleImage(w, h, Transparency.TRANSLUCENT);
            Graphics2D g = image.createGraphics();
            icon.paintIcon(null, g, 0, 0);
            g.dispose();
            return image;
        }
    }	
	
	public class KeyInput  implements KeyListener
	{


		public void keyPressed(KeyEvent e) {
			
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP: 
					north = true;
					break;
				case KeyEvent.VK_DOWN: 
					south = true;
					break;
				case KeyEvent.VK_LEFT: 
					west = true;
					break;
				case KeyEvent.VK_RIGHT: 
					east = true;
					break;
				
			
			}
			
			
			
			
		}


		public void keyReleased(KeyEvent e) {
			switch(e.getKeyCode())
			{
				case KeyEvent.VK_UP: 
					north = false;
					break;
				case KeyEvent.VK_DOWN: 
					south = false;
					break;
				case KeyEvent.VK_LEFT: 
					west = false;
					break;
				case KeyEvent.VK_RIGHT: 
					east = false;
					break;
				case KeyEvent.VK_ESCAPE: 
					System.exit(0);
					break;
					
			
			}			
			
		}


		public void keyTyped(KeyEvent e) {
			
			
		}
		
		
		
	}
	
	
}
