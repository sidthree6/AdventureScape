package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Enemy extends JPanel {

	private static final long serialVersionUID = 1L; 
	
	public int health = 0;
	
	int speed = 5;
	
	boolean itemSet = false;
	boolean action = false;
	boolean dead = false;

	public ArrayList<Projectile> fireBall = new ArrayList<Projectile>();
	
	int direction = 1;
	
	int maxTravel = 70;
	
	ImageIcon NPC1 = new ImageIcon(getClass().getResource("/Game/Image/AI_1.png"));
	ImageIcon NPC1_1 = new ImageIcon(getClass().getResource("/Game/Image/AI_1_2.png"));
	
	ImageIcon GhostBoss = new ImageIcon(getClass().getResource("/Game/Image/Ghost_1.png"));
	
	ImageIcon plant[] = { new ImageIcon(getClass().getResource("/Game/Image/man_eater_flower_1.png")),
						  new ImageIcon(getClass().getResource("/Game/Image/man_eater_flower_2.png")),
						  new ImageIcon(getClass().getResource("/Game/Image/man_eater_flower_3.png")), };
	
	Graphics g;
	
	public Location enemyLocation = new Location();
	public Location enemyStartLocation = new Location();
	
	Rectangle eCollision;
	Rectangle eLookZone;
	
	long ShotTimeEnd;
	long interval = 500;
	
	public int enemyId;
	public int enemyAnimationIndex = 0;
	
	public Enemy(int id, int x, int y)
	{
		// 0 = mage
		// 1 = mage_diff
		// 2 = plant
		// 3 = boss
		enemyId = id;
		
		if(enemyId == 0 || enemyId == 1)
		{
			health = 20;
		}
		else if(enemyId == 2)
		{
			health = 40;
		}
		else if(enemyId == 3)
		{
			health = 100;
		}
		
		ShotTimeEnd = System.currentTimeMillis();
		
		setEnemyLocation(x, y);
	}
	
	// Overloading constructor
	public Enemy(int id, int x, int y, int howMuchTravel)
	{
		// 0 = mage
		// 1 = mage_diff
		// 2 = plant
		// 3 = boss
		enemyId = id;
			
		if(enemyId == 0 || enemyId == 1)
		{
			health = 20;
		}
		else if(enemyId == 2)
		{
			health = 40;
		}
		else if(enemyId == 3)
		{
			health = 100;
		}
		
		maxTravel = howMuchTravel;
		
		ShotTimeEnd = System.currentTimeMillis();
		
		setEnemyLocation(x, y);
	}
	
	public void setEnemyLocation(int x, int y)
	{
		enemyLocation.setLocation(x, y);
		enemyStartLocation.setLocation(x, y);
	}
	
	public void Set(Graphics graph)
	{
		g = graph;	
		
		
		itemSet = true;
	}
	
	public void paintCollider()
	{		
		if(itemSet == true)
		{
			g.setColor(Color.green);
			
			g.fillRect(eCollision.x, eCollision.y, eCollision.width, eCollision.height);
		}
	}
	
	public void paintLookZone()
	{		
		if(itemSet == true)
		{
			g.setColor(Color.yellow);
			
			g.fillRect(eLookZone.x, eLookZone.y, eLookZone.width, eLookZone.height);
		}
	}
	
	public void drawEnemy()
	{
		if(enemyId == 0)
		{
			eCollision = new Rectangle(enemyLocation.X + 15, enemyLocation.Y , 20, 50);		
			eLookZone = new Rectangle(enemyLocation.X - 250, enemyLocation.Y-50 , 270, 150);
			
			int outOfHundred = (50 * health) / 20;
			
			//Paint healthBar
			g.setColor(Color.darkGray);
			g.fillRect(enemyLocation.getX(), enemyLocation.getY()- 15, 50, 10);
			g.setColor(Color.red);
			g.fillRect(enemyLocation.getX(), enemyLocation.getY()- 15, outOfHundred, 10);
			
			NPC1.paintIcon(this, g, enemyLocation.getX(), enemyLocation.getY());
		}
		else if(enemyId == 1)
		{
			eCollision = new Rectangle(enemyLocation.X + 15, enemyLocation.Y , 20, 50);
			eLookZone = new Rectangle(enemyLocation.X + 15, enemyLocation.Y , 20, 50);
			NPC1_1.paintIcon(this, g, enemyLocation.getX(), enemyLocation.getY());
		}
		else if(enemyId == 2)
		{					
			eLookZone = new Rectangle(enemyLocation.X-150, enemyLocation.Y-150, 400, 380);
			eCollision = new Rectangle(enemyLocation.X+15, enemyLocation.Y , 70, 80);
			
			int outOfHundred = (50 * health) / 40;
			
			//Paint healthBar
			g.setColor(Color.darkGray);
			g.fillRect(enemyLocation.getX() + 25, enemyLocation.getY()- 15, 50, 13);
			g.setColor(Color.red);
			g.fillRect(enemyLocation.getX() + 25, enemyLocation.getY()- 15, outOfHundred, 13);
			
			plant[enemyAnimationIndex].paintIcon(this, g, enemyLocation.getX(), enemyLocation.getY());
		}
		else if(enemyId == 3)
		{
			eCollision = new Rectangle(enemyLocation.X, enemyLocation.Y , 70, 70);
			eLookZone = new Rectangle(enemyLocation.X - 550, enemyLocation.Y - 550, 1170, 1150);
			
			int outOfHundred = (50 * health) / 100;
			
			//Paint healthBar
			g.setColor(Color.darkGray);
			g.fillRect(enemyLocation.getX() + 10, enemyLocation.getY()- 20, 50, 13);
			g.setColor(Color.red);
			g.fillRect(enemyLocation.getX() + 10, enemyLocation.getY()- 20, outOfHundred, 13);
			
			GhostBoss.paintIcon(this, g, enemyLocation.getX(), enemyLocation.getY());
		}
	}
	
	public void moveEnemy()
	{
		if(enemyId == 3)
		{
			maxTravel = 150;
			
			enemyLocation.setLocation(enemyLocation.getX() + (direction * speed), enemyLocation.getY() + (direction * speed));
			
			if(enemyLocation.getY() > (enemyStartLocation.getY() + maxTravel) || enemyLocation.getY() < (enemyStartLocation.getY() - maxTravel))
			{			
				direction *= -1;
			}
		}
		else
		{
			enemyLocation.setLocation(enemyLocation.getX(), enemyLocation.getY() + (direction * speed));
		
			if(enemyLocation.getY() > (enemyStartLocation.getY() + maxTravel) || enemyLocation.getY() < (enemyStartLocation.getY() - maxTravel))
			{			
				direction *= -1;
			}
		}
		
	}
	
	public void actionPerform()
	{
		if(action)
		{
			if(enemyId == 2)
			{
				// Not move
			}
			else
			{
				// move enemy up and down
				moveEnemy();
			}
			// make enemy shoot
			shoot();
			
		}
		// move fireball
		moveShot();
	}
	
	public void reduceHealth()
	{
		// reduce 10 health
		//System.out.println(health);
		health -= 10;		
		//System.out.println(health);
		if(health <= 0)
		{
			//System.out.println("dead");
			// enemy dead
			dead = true;
			action  = false;
		}
	}
	
	public boolean isDead()
	{
		return dead;
	}
	
	public void shoot()
	{
		if(System.currentTimeMillis() > (ShotTimeEnd + interval))
		{
			if(enemyId == 0)
			{
				fireBall.add(new Projectile(g, enemyLocation.getX(), enemyLocation.getY(), Direction.LEFT));
			}
			else if(enemyId == 2)
			{
				fireBall.add(new Projectile(g, enemyLocation.getX() + 40 , enemyLocation.getY() + 35, Direction.LEFT));
				fireBall.add(new Projectile(g, enemyLocation.getX() + 40 , enemyLocation.getY() + 35, Direction.UP));
				fireBall.add(new Projectile(g, enemyLocation.getX() + 40 , enemyLocation.getY() + 35, Direction.RIGHT));
				fireBall.add(new Projectile(g, enemyLocation.getX() + 40 , enemyLocation.getY() + 35, Direction.DOWN));
			}
			else if(enemyId == 3)
			{
				fireBall.add(new Projectile(g, enemyLocation.getX() + 20 , enemyLocation.getY() + 15, Direction.LEFT));
				fireBall.add(new Projectile(g, enemyLocation.getX() + 20 , enemyLocation.getY() + 15, Direction.UP));
				fireBall.add(new Projectile(g, enemyLocation.getX() + 20 , enemyLocation.getY() + 15, Direction.RIGHT));
				fireBall.add(new Projectile(g, enemyLocation.getX() + 20 , enemyLocation.getY() + 15, Direction.DOWN));
			}
			
			ShotTimeEnd = System.currentTimeMillis() + interval;
		}
	}
	
	public void drawShot()
	{
		for(Projectile c : fireBall)
		{			
			//System.out.println(c.bulletLoc.getX() + " " + c.bulletLoc.getY());
			if(c.shot && dead == false)
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
	
	public ArrayList<Projectile> getShotColliders()
	{
		return fireBall;
	}
	
	public Rectangle getLookZone()
	{
		return eLookZone;
	}
	
	public Rectangle getHitZone()
	{
		return eCollision;
	}
}
