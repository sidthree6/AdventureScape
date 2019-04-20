package Game;

import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Projectile extends JPanel {
 
	private static final long serialVersionUID = 1L;
	
	int speed = 15; // projectile speed
	public Location bulletLoc = new Location();
	
	Rectangle bounds;
	
	Direction shootDirection;
	
	Graphics g;
	
	public boolean shot = false;
	
	ImageIcon FireballRight = new ImageIcon(getClass().getResource("/Game/Image/Fireball.png"));
	ImageIcon FireballLeft = new ImageIcon(getClass().getResource("/Game/Image/Fireball_Left.png"));
	ImageIcon FireballUp = new ImageIcon(getClass().getResource("/Game/Image/Fireball_Up.png"));
	ImageIcon FireballDown = new ImageIcon(getClass().getResource("/Game/Image/Fireball_Down.png"));
	
	// constructor
	public Projectile(Graphics graph, int x, int y, Direction dir)
	{
		g = graph;
		shootDirection = dir;
		// spawn bullet
		bulletLoc.setLocation(x, y);
		
		shot = true;
		
	}
	
	public Rectangle getBounds()
	{
		return bounds;
	}
	
	public ImageIcon showBullet()
	{
		//spawn collider
		bounds = new Rectangle(bulletLoc.getX(), bulletLoc.getY(), 18, 18);
				
		if(shootDirection == Direction.UP)
		{
			return FireballUp;
		}
		else if(shootDirection == Direction.DOWN)
		{
			return FireballDown;
		}
		else if(shootDirection == Direction.LEFT)
		{
			return FireballLeft;
		}
		else if(shootDirection == Direction.RIGHT)
		{
			return FireballRight;
		}
		
		return FireballRight;
	}
	
	public void moveBullet()
	{		
		if(shootDirection == Direction.UP)
		{
			bulletLoc.setLocation(bulletLoc.getX(), bulletLoc.getY() - speed); 
		}
		else if(shootDirection == Direction.DOWN)
		{
			bulletLoc.setLocation(bulletLoc.getX(), bulletLoc.getY() + speed);
		}
		else if(shootDirection == Direction.LEFT)
		{
			bulletLoc.setLocation(bulletLoc.getX() - speed, bulletLoc.getY());
		}
		else if(shootDirection == Direction.RIGHT)
		{
			bulletLoc.setLocation(bulletLoc.getX() + speed, bulletLoc.getY());
		}
		
		if(bulletLoc.getX() < 0 || bulletLoc.getX() > 1400 || bulletLoc.getY() < 0 || bulletLoc.getY() > 1000)
		{
			shot = false;
		}
	}
	
	public void removeShot()
	{		
		
	}
	
	
	
}
