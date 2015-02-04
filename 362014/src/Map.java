import java.awt.Dimension;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RectangularShape;


public class Map {

	Car car1;
	Car car2;
	Dimension dim;
	Ellipse2D.Double outer;
	Ellipse2D.Double inner;
	Rectangle2D.Double finish;
	boolean start;
	
	//Oval 
	
	public Map()
	{
		dim = new Dimension(600, 1000);
		car1 = new Car(20, 450);
		car2 = new Car(100, 450);
		outer = new Ellipse2D.Double(0,0, (int)dim.getWidth(), (int)dim.getHeight());
		inner = new Ellipse2D.Double(150,250, (int)dim.getWidth()/2, (int)dim.getHeight()/2);
		finish = new Rectangle2D.Double(0, 475, 150, 50);
	}
	
	public Rectangle2D getFinish()
	{
		return finish;
	}
	
	public Car getCar(int number)
	{
		if(number == 1)
			return car1;
		else if(number == 2)
			return car2;
		else
			return null;		
	}
	
	public void tick()
	{
		Rectangle2D bound1 = car1.getBounds();
		Area a = new Area(bound1);
		Area b = new Area(outer);
		Area c = new Area(inner);
		car1.tick();
		car2.tick();
		
		if(car1.boomSteps == 0)
			start = false;
		if(c.contains(bound1))
		{
			if(car1.hit)
				start = true;
			car1.hit = false;
			if(car1.boomSteps==0 && start)
			{
				car1.boomSteps = 36;
				start = true;
			}	
		}
					
		else if(b.intersects(bound1))
		{
			car1.hit = true;
		}
		else
		{
			if(car1.hit)
				start = true;
			car1.hit = false;
			if(car1.boomSteps==0 && start)
				car1.boomSteps = 36;	
		}
		
		bound1 = car2.getBounds();
		a = new Area(bound1);
		b = new Area(outer);
		c = new Area(inner);
		if(car2.boomSteps == 0)
			start = false;
		if(c.contains(bound1))
		{
			if(car2.hit)
				start = true;
			car2.hit = false;
			if(car2.boomSteps==0 && start)
			{
				car2.boomSteps = 36;
				start = true;
			}	
		}
					
		else if(b.intersects(bound1))
		{
			car2.hit = true;
		}
		else
		{
			if(car2.hit)
				start = true;
			car2.hit = false;
			if(car2.boomSteps==0 && start)
				car2.boomSteps = 36;	
		}	
	}
	
	
	public RectangularShape getBound(int number)
	{
		if(number == 1)
			return outer;
		else if(number == 2)
			return inner;
		else
			return null;		
	}	
}

