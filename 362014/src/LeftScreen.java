import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;


public class LeftScreen extends JPanel{

	BufferedImage car1_text;
	BufferedImage accelx;
	
	ArrayList<Dimension> acceleration;
	ArrayList<Dimension> velocity;
	JTextField x;
	JTextField y;
	
	Car car;
	JLabel speedometer;
	JLabel velx;
	JLabel vely;
	JLabel accx;
	JLabel accy;
	JLabel posx;
	JLabel posy;
	double velocityA;
	double velocityB;
	double step;
	int steps = 20;
	int currentSteps = 0;
	JPanel grid;
	
	public LeftScreen()
	{
		addKeyListener(new EnterListener());
		
		acceleration = new ArrayList<Dimension>();
		velocity = new ArrayList<Dimension>();
		try { 
		    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		    e.printStackTrace();
		}
		
		try{
			car1_text = ImageIO.read(new File("car1.png"));
			accelx = ImageIO.read(new File("accelx.png"));
			
		}
		catch(Exception e)
		{
			
		}
		setBorder(BorderFactory.createLineBorder(Color.black, 20));
		HeaderPanel top = new HeaderPanel();
		JPanel bottom = new JPanel();
		grid = new JPanel();
		grid.setLayout(new GridLayout(0 ,6, 0, 0));
		//bottom.setLayout(new GridLayout(0, 1));
		bottom.setLayout(new BorderLayout(0, 0));
		top.setLayout(new GridLayout(0, 3, 10, 0));
		top.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		posx = new JLabel("Pos(x)", SwingConstants.CENTER);
		posy = new JLabel("Pos(y)", SwingConstants.CENTER);
		velx = new JLabel("Vel(x)", SwingConstants.CENTER);
		vely = new JLabel("Vel(y)", SwingConstants.CENTER);
		accx = new JLabel("Acc(x)", SwingConstants.CENTER);
		accy = new JLabel("Acc(y)", SwingConstants.CENTER);
		posx.setFont(new Font("Serif", Font.BOLD, 16));
		posy.setFont(new Font("Serif", Font.BOLD, 16));
		velx.setFont(new Font("Serif", Font.BOLD, 16));
		vely.setFont(new Font("Serif", Font.BOLD, 16));
		accx.setFont(new Font("Serif", Font.BOLD, 16));
		accy.setFont(new Font("Serif", Font.BOLD, 16));
		
		posx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		posy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		velx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		vely.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		accx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		accy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
		grid.add(posx);
		grid.add(posy);
		grid.add(velx);
		grid.add(vely);
		grid.add(accx);
		grid.add(accy);
		
		
		JLabel xl = new JLabel("Accel. (x)");
		JLabel yl = new JLabel("Accel. (y)");
		JLabel b1 = new JLabel(" ");
		JLabel b2 = new JLabel(" ");
		JLabel b3 = new JLabel(" ");
		JLabel b4 = new JLabel(" ");
		JLabel b5 = new JLabel(" ");
		
		
		
		
		
		xl.setForeground(Color.white);
		yl.setForeground(Color.white);
		JButton go = new JButton("Enter");
		//go.setIcon(new ImageIcon(accelx));
		//go.setBackground(Color.black);
		//go.setForeground(Color.white);
		
		go.addActionListener(new GoListener());
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(0, 1, 10, 0));
		buttonPanel.setBackground(Color.black);
		b5.setBackground(Color.black);
		b4.setBackground(Color.black);
		//buttonPanel.add(b5);
		buttonPanel.add(go);
		//buttonPanel.add(b4);
		go.setPreferredSize(new Dimension(buttonPanel.getWidth()/2, buttonPanel.getHeight()));
		//JLabel picLabel = new JLabel(new ImageIcon(accelx));
		
		//picLabel.setPreferredSize(new Dimension((int)(Runner.width/4.0), (int)(Runner.height/20.0)));
		x = new JTextField(3);
		y = new JTextField(3);
		x.addKeyListener(new EnterListener());
		y.addKeyListener(new EnterListener());
		top.add(b1);
		top.add(b2);
		top.add(b3);
		//top.add(b4);
		//top.add(b5);
		//top.add(xl);
		top.add(x);
		//top.add(yl);
		top.add(y);
		top.add(buttonPanel);
		top.setPreferredSize(new Dimension((int)(Runner.width/4.0), (int)(Runner.height/15.0)));
		bottom.setPreferredSize(new Dimension((int)(Runner.width/4.0), (int)(Runner.height * 14.0 /15.0)));
		top.setBackground(Color.black);
		//bottom.setBackground(Color.black);
		
		int s = 0;
		
		if(velocity.size()>0)
		{
			double c = velocity.get(velocity.size()-1).getWidth();
			double d = velocity.get(velocity.size()-1).getHeight();
			double e = Math.sqrt((Math.pow(c, 2) + Math.pow(d,  2)));
			s = (int)e;
		}
		speedometer = new JLabel("" +  s + " MPH", SwingConstants.CENTER);
		//speedometer.setForeground(Color.white);
		JPanel speedLayout = new JPanel();
		//speedLayout.setLayout(new Bord)
		
		speedometer.setForeground(Color.yellow);
		speedometer.setBackground(new Color(14, 14, 14));
		speedometer.setFont(new Font("Serif", Font.BOLD, 70));
		speedometer.setBorder(BorderFactory.createLineBorder(Color.black, 20));
		speedometer.setOpaque(true);
		bottom.add(grid, BorderLayout.PAGE_START);
		bottom.add(speedometer, BorderLayout.PAGE_END);
		
		//Font labelFont = speedometer.getFont();
		//String labelText = speedometer.getText();

		//int stringWidth = speedometer.getFontMetrics(labelFont).stringWidth(labelText);
		//int componentWidth = speedometer.getWidth();

		 //Find out how much the font can grow in width.
		//double widthRatio = (double)componentWidth / (double)stringWidth;

		//int newFontSize = (int)(labelFont.getSize() * widthRatio);
		//int componentHeight = speedometer.getHeight();

		//Pick a new font size so it will not be larger than the height of label.
		//int fontSizeToUse = Math.min(newFontSize, componentHeight);

		// Set the label's font size to the newly determined size.
		//speedometer.setFont(new Font(labelFont.getName(), Font.PLAIN, fontSizeToUse));
		
		
		
		
		
		
		add(top);
		add(bottom);
		
		
	}
	
	public void tick()
	{
		car.tick();
		int s;
		if(currentSteps>0)
		{
			
			velocityA = velocityA + step;
			if(currentSteps == 1)
				{
					velocityA = velocityB;
				}
			currentSteps--;
		}
		int rep = (int)velocityA;
		speedometer.setText("" +  rep + " MPH");		
		
		
	}
	public void setCar(Car car)
	{
		this.car = car;
	}
	
	public void parseData()
	{
		String ex = x.getText();
		String why = y.getText();
		x.setText("");
		y.setText("");
		
		int exes = 0;
		int wise = 0;
		try{
			exes = Integer.parseInt(ex);
			wise = Integer.parseInt(why);
		}
		catch(NumberFormatException e)
		{
			return;
		}
		
		Dimension dim = new Dimension(exes, wise);
		acceleration.add(dim);
		
		
		Dimension v;
		if(velocity.size()>0)
			v = velocity.get(velocity.size() -1);
		else
			v = new Dimension(0, 0);
		Dimension a;
		if(acceleration.size()>0)
			a = acceleration.get(acceleration.size() -1);
		else
			a = new Dimension(0,0);
		
		velocity.add(new Dimension((int)(v.getWidth() + a.getWidth()), (int)(v.getHeight() + a.getHeight())));
		if(car!=null)
		{
			v = velocity.get(velocity.size() -1);
			System.out.println(v);
			car.setDestination(car.getPosition().getX() + v.getWidth(), car.getPosition().getY() + v.getHeight());
			
		}
		car.changeDirection(velocity.get(velocity.size()-1));
		double s = 0;
		double t = 0;
		if(velocity.size()>1)
		{
			double c = velocity.get(velocity.size()-2).getWidth();
			double d = velocity.get(velocity.size()-2).getHeight();
			double e = Math.sqrt((Math.pow(c, 2) + Math.pow(d,  2)));
			s = e;
		}
		if(velocity.size()>0)
		{
			double c = velocity.get(velocity.size()-1).getWidth();
			double d = velocity.get(velocity.size()-1).getHeight();
			double e = Math.sqrt((Math.pow(c, 2) + Math.pow(d,  2)));
			t = e;
		}
		v = velocity.get(velocity.size() -1);
		a = acceleration.get(acceleration.size() -1);
		
		posx = new JLabel("" + (int)car.getX(), SwingConstants.CENTER);
		posy = new JLabel("" + (int)car.getY(), SwingConstants.CENTER);
		velx = new JLabel("" + (int)v.getWidth(), SwingConstants.CENTER);
		vely = new JLabel("" + (int)v.getHeight(), SwingConstants.CENTER);
		accx = new JLabel("" + (int)a.getWidth(), SwingConstants.CENTER);
		accy = new JLabel("" + (int)a.getHeight(), SwingConstants.CENTER);
		posx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		posy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		velx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		vely.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		accx.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		accy.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		grid.add(posx);
		grid.add(posy);		
		grid.add(velx);
		grid.add(vely);
		grid.add(accx);
		grid.add(accy);		
		revalidate();
		
		
		
		velocityA = s;
		velocityB = t;
		step = (velocityB - velocityA)/20;
		currentSteps = 20;
		
		x.requestFocus();
		
		//speedometer.setText("" +  s + " MPH");		

		//velx.setText("" + velocity);
		//vely.setText("" + velocity);
		//accx.setText("" + acceleration);
		//accy.setText("" + acceleration);
	}
	
	
	public void paintComponent(Graphics x)
	{
		Graphics2D g =(Graphics2D) x;
		
		g.setColor(Color.black);
		g.fillRect(0,0,getWidth(), getHeight());
		
		//g.drawImage(car1_text, 0, 0, getWidth(), 100, null);
		
	}	
	
	public class GoListener implements ActionListener
	{
		public void actionPerformed(ActionEvent e)
		{
			if(((JButton)(e.getSource())).getText().equals("Enter"))
			{
				parseData();
			}
		}
	}
	
	public class EnterListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e) {
			switch(e.getKeyCode())
			{
			case KeyEvent.VK_ENTER:
				parseData();
				break;
			
			}
			
		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	
}
