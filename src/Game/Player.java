package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Player extends JPanel {

	private static final long serialVersionUID = 1L;
	
	int Health;
	
	Direction playerDirection;
	State pState;
	public Location playerLocation = new Location();
	Location playerLastLocation = new Location();
	Rectangle pCollision;
	
	public ArrayList<Projectile> fireBall = new ArrayList<Projectile>();
	
	Graphics g;
	
	long ShotTimeEnd;
	long interval = 200;
	
	Map currentMap;
	
	public boolean shot = false;
	public boolean dead = false;
	
	private int speed;
	private int walkspeed = 5;
	private int runspeed = 10;
	
	public int animationIndex;
	
	public int enemyKilled = 0;
	
	ImageIcon Character_Down[] = {
			new ImageIcon(getClass().getResource("/Game/Image/Main_Character_Forward.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_2.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_3.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_4.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_5.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_6.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_7.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_8.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Forward_9.png"))
	};
	
	ImageIcon Character_Up[] = {
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_2.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_3.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_4.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_5.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_6.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_7.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_8.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Back_9.png"))
	};
	
	ImageIcon Character_Right[] = {
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_2.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_3.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_4.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_5.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_6.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_7.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_8.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Right_9.png"))
	};
	
	ImageIcon Character_Left[] = {
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_2.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_3.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_4.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_5.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_6.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_7.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_8.png")),
			new ImageIcon(getClass().getResource("/Game/Image/Character_Left_9.png"))
	};
	
	ImageIcon Staff_Right = new ImageIcon(getClass().getResource("/Game/Image/StaffR.png"));
	ImageIcon Staff_Left = new ImageIcon(getClass().getResource("/Game/Image/StaffL.png"));
	
	public Player() 
	{
		// Set health to 100
		Health = 100;
		// Set direction to right
		playerDirection = Direction.UP;
		
		/// Set loction to 0, 0
		playerLocation.setLocation(0, 0);
		playerLastLocation.setLocation(0, 0);
		
		// Animation index
		animationIndex = 0;
		
		// set default speed to walk
		speed = walkspeed;
		
		// Set current player state to idle;
		pState = State.IDLE;
		
		ShotTimeEnd = System.currentTimeMillis();
	}
	
	public void Set(Graphics graph, Map map)
	{
		g = graph;
		currentMap = map;
	}
	
	// get player speed
	public int getSpeed() { return speed; }
	// make player walk
	public void walk() { speed = walkspeed;	}
	// make player run
	public void run() {	speed = runspeed; }
	// Change player state to walking
	public void playerWalking()	{ pState = State.MOVING; }
	// Change player state to idle
	public void playerStop() { pState = State.IDLE;	}
	
	// Return player current locaion
	public Location getPlayerLocation()
	{
		return playerLocation;
	}
	
	public Location getPlayerLastLocation()
	{
		return playerLastLocation;
	}
	
	public void setPlayerLocation(int x, int y)
	{
		playerLocation.setLocation(x, y);
		//playerLastLocation.setLocation(x, y);
	}
	
	public void paintCollider()
	{
		g.setColor(Color.green);
		
		g.fillRect(pCollision.x, pCollision.y, pCollision.width, pCollision.height);
	}
	
	public void drawPlayer()
	{
		pCollision = new Rectangle(playerLocation.X + 15, playerLocation.Y , 20, 50);		
		
		switch(playerDirection)
		{
		case RIGHT:
			Character_Right[animationIndex].paintIcon(this, g, playerLocation.X, playerLocation.Y);
			Staff_Right.paintIcon(this, g, playerLocation.X + 7, playerLocation.Y + 5);
			break;
		case LEFT:
			Staff_Left.paintIcon(this, g, playerLocation.X, playerLocation.Y + 5);
			Character_Left[animationIndex].paintIcon(this, g, playerLocation.X, playerLocation.Y);
			break;
		case UP:			
			Character_Up[animationIndex].paintIcon(this, g, playerLocation.X, playerLocation.Y);
			Staff_Right.paintIcon(this, g, playerLocation.X + 7, playerLocation.Y + 5);
			break;
		case DOWN:
			Staff_Right.paintIcon(this, g, playerLocation.X + 7, playerLocation.Y + 5);
			Character_Down[animationIndex].paintIcon(this, g, playerLocation.X, playerLocation.Y);			
			break;
		default:
			Character_Right[animationIndex].paintIcon(this, g, playerLocation.X, playerLocation.Y);
			Staff_Right.paintIcon(this, g, playerLocation.X + 7, playerLocation.Y + 5);
		}
	}
	
	public void drawHealthBar()
	{
		int outOfHundred = (50 * Health) / 100;
		
		//Paint healthBar
		g.setColor(Color.darkGray);
		g.fillRect(playerLocation.getX(), playerLocation.getY()- 20, 50, 12);
		g.setColor(Color.red);
		g.fillRect(playerLocation.getX(), playerLocation.getY()- 20, outOfHundred, 13);
	}
	
	public void setDirection(Direction dir)
	{
		playerDirection = dir;
		playerWalking();
	}
	
	public void move()
	{		
		// set current location in last location
		playerLastLocation.setLocation(playerLocation.getX(), playerLocation.getY());
		
		if(playerDirection == Direction.UP)
		{			
			playerLocation.setLocation(playerLocation.getX(), playerLocation.getY() - getSpeed());		
		}
		else if(playerDirection == Direction.DOWN)
		{
			playerLocation.setLocation(playerLocation.getX(), playerLocation.getY() + getSpeed());
		}
		else if(playerDirection == Direction.LEFT)
		{
			playerLocation.setLocation(playerLocation.getX() - getSpeed(), playerLocation.getY());
		}
		else if(playerDirection == Direction.RIGHT)
		{
			playerLocation.setLocation(playerLocation.getX() + getSpeed(), playerLocation.getY());
		}
		
	}
	
	public void shoot()
	{
		if(System.currentTimeMillis() > (ShotTimeEnd + interval))
		{
			fireBall.add(new Projectile(g, playerLocation.getX(), playerLocation.getY(), playerDirection));
			ShotTimeEnd = System.currentTimeMillis() + interval;
		}
		
	}
	
	public void drawShot()
	{
		for(Projectile c : fireBall)
		{			
			//System.out.println(c.bulletLoc.getX() + " " + c.bulletLoc.getY());
			if(c.shot)
			{
				ImageIcon show = c.showBullet();
				
				show.paintIcon(this, g, c.bulletLoc.getX(), c.bulletLoc.getY());
			}
		}
	}
	
	public void moveShot()
	{
		for(Projectile c : fireBall)
		{
			if(c.shot)
			{
				c.moveBullet();
			}
		}
	}
	 
	public void takeDamage()
	{
		Health -= 10;
		
		if(Health <= 0)
		{
			dead = true;
		}
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public ArrayList<Projectile> getShotColliders()
	{
		return fireBall;
	}
	
	public Rectangle getCollider()
	{
		return pCollision;
	}
}
