import java.awt.Dimension;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;


public class Car {

	Point2D pos;
	Dimension dim;
	int direction;
	Point2D destination;
	boolean go;
	int speed;
	int steps;
	boolean hit;
	int boomSteps;

	public Car()
	{
		pos = new Point2D.Double(100, 300);
		dim = new Dimension(30, 60);
		destination = new Point2D.Double(150,300);
		speed = 10;
	}
	
	
	public Car(int x, int y)
	{
		pos = new Point2D.Double(x, y);
		dim = new Dimension(30,60);
		destination = new Point2D.Double(x,y);
		speed = 10;
	}
	
	
	public int getX()
	{
		return (int)(pos.getX());
	}
	
	public Rectangle2D getBounds()
	{
		return new Rectangle2D.Double(pos.getX(), pos.getY(), dim.getWidth(), dim.getHeight());
	}
	
	
	public int getDirection()
	{
		return direction;
	}
	public void changeDirection(Dimension d)
	{
		
		double angel = Math.atan(d.getHeight()/d.getWidth()) - 0.5 * Math.PI;
		if(d.getWidth() < 0 && d.getHeight() <0)
		{
			angel = angel + Math.PI;
		}
		if(d.getWidth() < 0&& d.getHeight() > 0)
		{
			angel = angel + Math.PI;
		}
		
		while(angel< 0)
		{
			angel = angel + 2* Math.PI;
		}
		while(angel>= 2* Math.PI)
		{
			angel = angel - 2*Math.PI;
		}
		//System.out.println(angel);
		
		angel = Math.toDegrees(angel);
		direction = (int)(angel/10);
		//System.out.println(direction);
		
	}
	public int getY()
	{
		return (int)(pos.getY());
	}
	
	public int getWidth()
	{
		return (int)dim.getWidth();
	}
	
	public int getHeight()
	{
		return (int)dim.getHeight();
	}
	
	public void north()
	{
		pos.setLocation(pos.getX(), pos.getY() - 5);
		
	}
	
	public void south()
	{
		pos.setLocation(pos.getX(), pos.getY() + 5);
	}	
	
	public void west()
	{
		pos.setLocation(pos.getX() -5, pos.getY());
	}	
	
	double xMove;
	double yMove;
	
	public void tick()
	{
		if(steps > 0)
		{
			steps--;
			if(destination.getX() > 0 && destination.getX() != getX())
			{
				//boolean negative = getX() > destination.getX();
				//if(negative)
					pos.setLocation(getX() + xMove, pos.getY());
				//else
					//pos.setLocation(getX() + 1, pos.getY());
				
			}
			if(destination.getY() > 0 && Math.floor(destination.getY()) != Math.floor(getY()))
			{
				//boolean negative = getY() > destination.getY();
				//if(negative)
					pos.setLocation(getX(), pos.getY() + yMove);
				//else
					//pos.setLocation(getX(), pos.getY()+1);
				
			}
	
			System.out.println("location" + pos);
			System.out.println("position" + destination);	
			if(steps == 1)
				pos = destination;
		}
		if(boomSteps >0)
		{
			boomSteps--;
		}

		
	}
	
	public void east()
	{
		pos.setLocation(pos.getX() + 5, pos.getY());
	}		

	public void setDestination(double d, double e)
	{
		destination =new Point2D.Double(d, e);
		xMove = (destination.getX() - getX())/10;
		yMove = (destination.getY() - getY())/10;
		steps = 10;
		
	}
	

	
	public Point2D getPosition()
	{
		
			return pos;
		
	}
	
	
}
