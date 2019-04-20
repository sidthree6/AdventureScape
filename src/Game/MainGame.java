package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

public class MainGame extends JPanel implements KeyListener, ActionListener, MouseListener
{
	
	private static final long serialVersionUID = 1L;
	
	public Map currentMap;  // Current map to load
	
	Timer time = new Timer(50, this); // Set timer
	
	// Get main menu instance
	MainMenu menu = new MainMenu();
	// Get map instance
	LoadMap map = new LoadMap();
	// Load Player
	Player player = new Player();
	// Load Enemies
	
	//enemy mages in 1st level
	ArrayList<Enemy> mages = new ArrayList<Enemy>();
	//enemy plant in 2nd level
	ArrayList<Enemy> plants = new ArrayList<Enemy>();
	//enemy boss in house
	ArrayList<Enemy> boss = new ArrayList<Enemy>();
	
	// Set debug mode
	boolean debug = false;
	
	// set time in milisecond for plant animation
	long SetEndTime;
	long interval = 120;
	
	// Main Function
	public static void main(String[] args)
	{
		MainGame s = new MainGame();
		JFrame window = new JFrame();

		window.setTitle("ADVENTURE");
		window.setSize(1306,866);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		s.setBackground(Color.WHITE);
		window.add(s); 
		window.setVisible(true);
		window.setResizable(false);
		s.requestFocus();
	}
	
	// Main Game Constructor
	public MainGame()
	{
		addKeyListener(this);
		addMouseListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		
		time.start();
		
		SetEndTime = System.currentTimeMillis();
		
		// When game loads, load title screen
		currentMap = Map.TITLESCREEN;
		
		// Set player location
		player.setPlayerLocation(940, 180);		
		
		// Set enemy location
		mages.add(new Enemy(0, 1100, 400));
		mages.add(new Enemy(0, 800, 500));
		mages.add(new Enemy(0, 1160, 200));
		mages.add(new Enemy(0, 670, 300, 20));
		
		// set plant location
		plants.add(new Enemy(2, 450, 570));
		plants.add(new Enemy(2, 850, 220));
		plants.add(new Enemy(2, 250, 200));
		
		// set boss location
		boss.add(new Enemy(3,625,300));
		
	}
	
	// Paint graphics on screen
	public void paintComponent(Graphics g)
	{		
		switch(currentMap)
		{
		case TITLESCREEN:
			TitleScreenMenu(g);
			break;
		case HELPSCREEN:
			HelpScreenMenu(g);
			break;
		case INSTRUCTIONSCREEN:
			InstrctionScreenMenu(g);
			break;
		case STARTINGHOUSE:
			PlayerHouse(g);
			break;
		case MAINLAND_ONE:
			MainLandOne(g);
			break;
		case MAINLAND_TWO:
			MainLandTwo(g);
			break;
		case BOSSHOUSE:
			BossHouse(g);
			break;
		case WIN:
			WinGame(g);
			break;
		case LOSE:
			LoseGame(g);
			break;
		default:
			TitleScreenMenu(g);
		}		
		
		repaint();
	}
	
	// Action performed by player
	public void actionPerformed(ActionEvent e)
	{
		
		// Paint colliders if in debug mode
		DebugMode();
		
		if(player.pState == State.MOVING)
		{
			player.move();
			
			player.animationIndex++;
			
			if(player.animationIndex > 8)
			{
				player.animationIndex = 0;
			}
		}
		
		player.moveShot();
		
		// Enemy actions
		for(Enemy c : mages)
		{
			// perform action
			c.actionPerform();
		}
		
		for(Enemy c : plants)
		{
			// perform action			
			c.actionPerform();
		}
		
		for(Enemy c : boss)
		{
			// perform action			
			c.actionPerform();
		}
		
		if(System.currentTimeMillis() > (SetEndTime + interval))
		{
			for(Enemy c : plants)
			{
				// animate plant				
				c.enemyAnimationIndex++;
				
				if(c.enemyAnimationIndex++ > 2)
				{
					c.enemyAnimationIndex = 0;
				}					
					
			}
			
			SetEndTime = System.currentTimeMillis() + interval;				
		}
		
		if(player.isDead())
		{
			currentMap = Map.LOSE;
		}
		if(player.enemyKilled == 8)
		{
			currentMap = Map.WIN;
		}
		
		repaint();
	}
	
	// Keypress event @override
	public void keyPressed(KeyEvent e) 
	{
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SHIFT)
		{
			player.run();
		}
		else if(key == KeyEvent.VK_L)
		{
			if(debug)				
				debug = false;
			else
				debug = true;
		}
		else if(key == KeyEvent.VK_SPACE)
		{			
			player.shoot();
		}
		else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
		{
			player.setDirection(Direction.DOWN);
		}
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
		{
			player.setDirection(Direction.LEFT);
		}
		else if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
		{
			player.setDirection(Direction.UP);
		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
		{
			player.setDirection(Direction.RIGHT);
		}
		e.consume();
	}
	
	// Keyrelease event @override
	public void keyReleased(KeyEvent e)
	{
		// default player animation to 0
		player.animationIndex = 0;
		
		int key = e.getKeyCode();
		
		if(key == KeyEvent.VK_SHIFT)
		{
			player.walk();
		}
		else if(key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN)
		{
			player.playerStop();
		}
		else if(key == KeyEvent.VK_A || key == KeyEvent.VK_LEFT)
		{
			player.playerStop();
		}
		else if(key == KeyEvent.VK_W || key == KeyEvent.VK_UP)
		{
			player.playerStop();
		}
		else if(key == KeyEvent.VK_D || key == KeyEvent.VK_RIGHT)
		{
			player.playerStop();
		}
		e.consume();
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) 
	{ 
		int x = e.getX();
		int y = e.getY();		
		
		// Which map to load based on click
		currentMap = menu.whichMapToLoad(x, y);
	}
	
	public void TitleScreenMenu(Graphics g)
	{
		// Load graphics for menu
		menu.Set(g, currentMap);
		
		/// Paint Title Screen
		menu.paintTitle("title"); // Paint Main Title
		menu.paintButton("play", 450, 400, 370, 70, Map.STARTINGHOUSE); // Paint play button
		menu.paintButton("help", 450, 525, 370, 70, Map.HELPSCREEN); // Paint help button
		menu.paintButton("instruction", 450, 650, 370, 70, Map.INSTRUCTIONSCREEN); // Paint instrction button
		
		g.setColor(new Color(176,173,33));
		
		menu.paintString("Adventure Scape", 175, 150);
	}
	
	public void WinGame(Graphics g)
	{
		// Load graphics for menu
		menu.Set(g, currentMap);
		
		menu.paintTitle("win"); // Paint Help menu
	}
	
	public void LoseGame(Graphics g)
	{
		// Load graphics for menu
		menu.Set(g, currentMap);
		
		menu.paintTitle("lose"); // Paint Help menu
	}
	
	public void HelpScreenMenu(Graphics g)
	{
		// Load graphics for menu
		menu.Set(g, currentMap);
		
		menu.paintTitle("help"); // Paint Help menu
		menu.paintButton("back", 0, 0, 370, 70, Map.TITLESCREEN); // Paint Back Button
	}
	
	public void InstrctionScreenMenu(Graphics g)
	{
		// Load graphics for menu
		menu.Set(g, currentMap);
		
		menu.paintTitle("help"); // Paint Help menu
		menu.paintButton("back", 0, 0, 370, 70, Map.TITLESCREEN); // Paint Back Button
	}
	
	public void PlayerHouse(Graphics g)
	{
		/// reset all values
		menu.Set(g, currentMap);
		map.Set(g,currentMap);
		player.Set(g, currentMap);
		
		// Paint player house		
		map.paintPlayerHouseTile();
		
		// Draw Player
		player.drawPlayer();		
				
		/// Create Cabinets		
		map.createCabinet(350, 100);			// create cabinet	
		map.createCabinet(550, 100);			// create cabinet	
		map.createCabinetTwo(350, 205);			// create cabinet
		map.createDivider(725, 100);			// create divider
		map.createDividerTwo(725, 300);			// create divider
		map.createRug(600, 775);				// create rug
		map.createTable(450, 325);				// create table
		map.createTable(450, 455);				// create table
		map.createTable(750, 455);				// create table
		map.createBed(775, 100);				// create bed
		map.createVase(940, 120);				// create vase
		
		map.createTrigger(600, 775, 100, 40);
		
		// Paint outside blocks
		map.paintPlayerBlockTile();	
		
		// draw player health bar
		player.drawHealthBar();
		
		CheckCollisions();
		
		// Get all triggers
		ArrayList<Rectangle> triggers = map.getTriggers();
		// Get player colliders
		Rectangle playerBox = player.getCollider();
				
		// If player collide with first trigger, which is rug
		if(playerBox.intersects(triggers.get(0)))
		{
			currentMap = Map.MAINLAND_ONE;
			player.setPlayerLocation(250, 355);
			player.fireBall.clear();
		}
		
		
		DebugMode();
	}
	
	public void MainLandOne(Graphics g)
	{
		/// reset all values
		menu.Set(g, currentMap);
		map.Set(g,currentMap);
		player.Set(g, currentMap);
		
		// Paint outside one map
		map.paintOutsideMapOne();
		
		map.createRock(500, 320);		// create rock
		map.createRock(950, 620);		// create rock
		map.createRock(1165, 300);
		
		// Draw player
		player.drawPlayer();
		player.drawShot();				// draw shot		
		
		// draw enemy
		for(Enemy e : mages)
		{			
			// set enemy
			e.Set(g);
			// draw enemy
			e.drawEnemy();
			//draw enemy bullet
			e.drawShot();
		}
		
			
		map.createTree(200, 500);		// create tree
		map.createTreeTwo(600, 500); 	// create tree 2
		map.createBush(100, 100);		// create bush
		map.createBush(200, 100);		// create bush
		map.createBush(100, 180);		// create bush
		map.createBush(100, 260);		// create bush
		map.createBush(700, 110);		// create bush
		map.createBush(700, 190);		// create bush
		map.createTreeTwo(800, 110); 	// create tree 2
		map.createTreeTwo(850, 110); 	// create tree 2
		map.createTreeTwo(900, 110); 	// create tree 2
		map.createTreeTwo(950, 110); 	// create tree 2
		map.createTreeTwo(1000, 110); 	// create tree 2
		map.createTreeTwo(1050, 110); 	// create tree 2
		map.createTreeTwo(1100, 110); 	// create tree 2
		
		map.createTreeTwo(850, 240); 	// create tree 2
		map.createTreeTwo(900, 240); 	// create tree 2
		map.createTreeTwo(950, 240); 	// create tree 2
		map.createTreeTwo(1000, 240); 	// create tree 2
		map.createTreeTwo(1050, 240); 	// create tree 2
		map.createTreeTwo(1100, 240); 	// create tree 2
		
		map.createBush(600, 610);		// create bush
		
		map.createTreeTwo(550, 670); 	// create tree 2
		map.createTreeTwo(650, 670); 	// create tree 2
		map.createTreeTwo(750, 670); 	// create tree 2
		map.createTreeTwo(850, 670); 	// create tree 2
		
		map.createTreeTwo(100, 540); 	// create tree 2
		map.createTreeTwo(100, 610); 	// create tree 2
		map.createTreeTwo(170, 670); 	// create tree 2
		map.createTreeTwo(100, 670); 	// create tree 2
		
		
		
		map.createHouse(1000, 500);		// create house
		
		map.createHouse(200, 110);		// create house
		
		map.createTree(250, 70);		// create tree
		
		map.createTrigger(1250, 350, 50, 150); // create map 2 trigger
		map.createTrigger(255, 330, 30, 10);   // create house trigger
		
		// draw player health bar
		player.drawHealthBar();
		
		// Check for any collisions
		CheckCollisions(); 
		// Check if player is in enemy look zone
		CheckEnemyLookZone(currentMap);
		// Check for fire collision
		CheckFireCollisions(currentMap);
		// Check if fire hit mages
		CheckBulletToEnemy(currentMap);
		// Check if player is hit
		CheckBulletToPlayer(currentMap);
		
		// Get all triggers
		ArrayList<Rectangle> triggers = map.getTriggers();
		// Get player colliders
		Rectangle playerBox = player.getCollider();
				
		// If player collide with first trigger, which is rug
		if(playerBox.intersects(triggers.get(0)))
		{
			currentMap = Map.MAINLAND_TWO;
			player.setPlayerLocation(50, 400);
			player.fireBall.clear();
		}
		else if(playerBox.intersects(triggers.get(1)))
		{
			currentMap = Map.STARTINGHOUSE;
			player.setPlayerLocation(625, 700);
			player.fireBall.clear();
		}
		
		
		DebugMode();
	}
	
	public void MainLandTwo(Graphics g)
	{
		/// reset all values
		menu.Set(g, currentMap);
		map.Set(g,currentMap);
		player.Set(g, currentMap);
		
		// Paint outside two map
		map.paintOutsideMapTwo();	
		
		// draw player
		player.drawPlayer();
		player.drawShot();					// draw shot
		
		
		// draw enemy
		for(Enemy e : plants)
		{			
			// set enemy
			e.Set(g);
			// draw enemy
			e.drawEnemy();
			//draw enemy bullet
			e.drawShot();
		}
			
		map.createTreeTwo(100, 100);			// create tree 2
		map.createTreeTwo(175, 100);			// create tree 2
		map.createTreeTwo(250, 100);			// create tree 2
		map.createTreeTwo(325, 100);			// create tree 2
		map.createTreeTwo(400, 100);			// create tree 2
		map.createTreeTwo(475, 100);			// create tree 2
		
		map.createTreeTwo(100, 175);			// create tree 2
		map.createTreeTwo(175, 175);			// create tree 2
		map.createTreeTwo(400, 175);			// create tree 2
		map.createTreeTwo(475, 175);			// create tree 2
		
		map.createTreeTwo(100, 250);			// create tree 2
		map.createTreeTwo(475, 250);			// create tree 2
		
		map.createTreeTwo(100, 670);			// create tree 2
		map.createTreeTwo(175, 670);			// create tree 2
		map.createTreeTwo(250, 670);			// create tree 2
		
		map.createTreeTwo(700, 100);			// create tree 2
		map.createTreeTwo(775, 100);			// create tree 2
		map.createTreeTwo(850, 100);			// create tree 2
		map.createTreeTwo(925, 100);			// create tree 2
		map.createTreeTwo(1000, 100);			// create tree 2
		
		map.createTreeTwo(700, 175);			// create tree 2
		map.createTreeTwo(775, 175);			// create tree 2
		
		map.createBush(710, 560);				// create bush
		
		map.createTree(870, 450);				// create tree
		
		map.createTreeTwo(700, 670);			// create tree 2
		map.createTreeTwo(775, 670);			// create tree 2
		map.createTreeTwo(850, 670);			// create tree 2
		map.createTreeTwo(925, 670);			// create tree 2		
		
		map.createBush(410, 660);			// create bush
		map.createBush(310, 660);			// create bush
		map.createBush(1100, 120);			// create bush
		
		map.createBossHouse(994, 100);		// create boss house		
		
		// draw player health bar
		player.drawHealthBar();
		
		// Check if player collide with environment
		CheckCollisions();		
		// Check if player is in enemy look zone
		CheckEnemyLookZone(currentMap);
		// check if fire colliding with anything
		CheckFireCollisions(currentMap);
		// Check if fire hit mages
		CheckBulletToEnemy(currentMap);
		// Check if player is hit
		CheckBulletToPlayer(currentMap);
				
		// create trigger
		map.createTrigger(0, 350, 50, 150);
		map.createTrigger(1100, 410, 50, 10);
		
		// Get all triggers
		ArrayList<Rectangle> triggers = map.getTriggers();
		// Get player colliders
		Rectangle playerBox = player.getCollider();
				
		// If player collide with first trigger, which is rug
		if(playerBox.intersects(triggers.get(0)))
		{
			currentMap = Map.MAINLAND_ONE;
			player.setPlayerLocation(1175, 400);
			player.fireBall.clear();
		}
		else if(playerBox.intersects(triggers.get(1)))
		{
			currentMap = Map.BOSSHOUSE;
			player.setPlayerLocation(625, 700);
			player.fireBall.clear();
		}
		
		DebugMode();
	}
	
	public void BossHouse(Graphics g)
	{
		/// reset all values
		menu.Set(g, currentMap);
		map.Set(g,currentMap);
		player.Set(g, currentMap);
		
		map.paintBossHouseTile();
		
		// draw player
		player.drawPlayer();
		player.drawShot();					// draw shot
		
		for(Enemy e : boss)
		{			
			// set enemy
			e.Set(g);
			// draw enemy
			e.drawEnemy();
			//draw enemy bullet
			e.drawShot();
		}
		
		
		map.paintBossHouseBlock();
		
		map.createTrigger(600, 800, 100, 50);
		
		// draw player health bar
		player.drawHealthBar();
		
		
		// Check if player collide with environment
		CheckCollisions();
		// Check if player is in enemy look zone
		CheckEnemyLookZone(currentMap);
		// check if fire colliding with anything
		CheckFireCollisions(currentMap);
		// Check if fire hit mages
		CheckBulletToEnemy(currentMap);
		// Check if player is hit
		CheckBulletToPlayer(currentMap);
		
		// Get all triggers
		ArrayList<Rectangle> triggers = map.getTriggers();
		// Get player colliders
		Rectangle playerBox = player.getCollider();
				
		// If player collide with first trigger, which is rug
		if(playerBox.intersects(triggers.get(0)))
		{
			currentMap = Map.MAINLAND_TWO;
			player.setPlayerLocation(1100, 420);
			player.fireBall.clear();
		}
		
		DebugMode();
	}
	
	public void CheckCollisions()
	{
		// Get all colliders
		ArrayList<Rectangle> allBoxes = map.getColliders();
		// Get player colliders
		Rectangle playerBox = player.getCollider();
		
		// Check if player intersects with any collider
		for(Rectangle c : allBoxes)
		{
			if(playerBox.intersects(c))
			{
				//player.playerStop();
				player.setPlayerLocation(player.getPlayerLastLocation().getX(), player.getPlayerLastLocation().getY());
			}
		}
	}
	
	public void CheckEnemyLookZone(Map whichMap)
	{
		// Get player colliders
		Rectangle playerBox = player.getCollider();
		
		if(whichMap == Map.MAINLAND_ONE)
		{
			for(Enemy e : mages)
			{
				if(playerBox.intersects(e.getLookZone()))
				{
					//System.out.println("In Look Zone");
					e.action = true;
				}
				else
				{
					e.action = false;
				}
			}
		}
		else if(whichMap == Map.MAINLAND_TWO)
		{
			for(Enemy e : plants)
			{
				if(playerBox.intersects(e.getLookZone()))
				{
					//System.out.println("In Look Zone");
					e.action = true;
				}
				else
				{
					e.action = false;
				}
			}
		}
		else if(whichMap == Map.BOSSHOUSE)
		{
			for(Enemy e : boss)
			{
				if(playerBox.intersects(e.getLookZone()))
				{
					//System.out.println("In Look Zone");
					e.action = true;
				}
				else
				{
					e.action = false;
				}
			}
		}
	}
	
	public void CheckFireCollisions(Map whichMap)
	{
		//get all shot colliderss
		ArrayList<Projectile> allShots = player.getShotColliders();
		//get all colliders
		ArrayList<Rectangle> allBoxes = map.getColliders();
		
		ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
		
		for(Projectile p : allShots)
		{
			Rectangle shotBox = p.getBounds();
			
			for(Rectangle c : allBoxes)
			{
				if(shotBox.intersects(c))
				{
					toRemove.add(p);
					//p.shot = false;
					//p.remove(this);
				}
			}
		}
		// remove all colliding projectile
		allShots.removeAll(toRemove);
		// clear list
		toRemove.clear();
		
		if(whichMap == Map.MAINLAND_ONE)
		{
			//Check all mages fire colliders
			for(Enemy e : mages) 
			{
				ArrayList<Projectile> allEShots = e.getShotColliders();
				
				for(Projectile p : allEShots)
				{
					for(Rectangle c : allBoxes)
					{
						if(p.getBounds().intersects(c))
						{
							toRemove.add(p);
						}
					}
				}
				
				// remove all colliding projectile
				allEShots.removeAll(toRemove);
				// clear list
				toRemove.clear();
			}
		}
		
		if(whichMap == Map.MAINLAND_TWO)
		{
			//Check all plants fire colliders
			for(Enemy e : plants)
			{
				ArrayList<Projectile> allEShots = e.getShotColliders();
				
				for(Projectile p : allEShots)
				{
					for(Rectangle c : allBoxes)
					{
						if(p.getBounds().intersects(c))
						{
							toRemove.add(p);
						}
					}
				}
				
				// remove all colliding projectile
				allEShots.removeAll(toRemove);
				// clear list
				toRemove.clear();
			}
		}
		
		if(whichMap == Map.BOSSHOUSE)
		{
			//Check all plants fire colliders
			for(Enemy e : boss)
			{
				ArrayList<Projectile> allEShots = e.getShotColliders();
				
				for(Projectile p : allEShots)
				{
					for(Rectangle c : allBoxes)
					{
						if(p.getBounds().intersects(c))
						{
							toRemove.add(p);
						}
					}
				}
				
				// remove all colliding projectile
				allEShots.removeAll(toRemove);
				// clear list
				toRemove.clear();
			}
		}
		
		
	}
	
	public void CheckBulletToEnemy(Map map)
	{	
		
		// All mages in 1st level
		if(map == Map.MAINLAND_ONE)
		{
			//get all shot colliderss
			ArrayList<Projectile> allShots = player.getShotColliders();
			ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
			
			ArrayList<Enemy> enemyToRemove = new ArrayList<Enemy>();
			
			for(Projectile p : allShots)
			{
				Rectangle shotBox = p.getBounds();
		
				for(Enemy e : mages)
				{
					if(shotBox.intersects(e.getHitZone()))
					{
						e.reduceHealth();	
						// This is where layer hit enemy
						if(e.isDead())
						{
							player.enemyKilled++;
							enemyToRemove.add(e);
						}
						toRemove.add(p);
					}
				}
			}
			
			// remove all colliding projectile
			allShots.removeAll(toRemove);
			// clear list
			toRemove.clear();
			// remove dead enemy
			mages.removeAll(enemyToRemove);
			enemyToRemove.clear();
		}		
		else if(map == Map.MAINLAND_TWO)
		{
			//get all shot colliderss
			ArrayList<Projectile> allShots = player.getShotColliders();
			ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
			
			ArrayList<Enemy> enemyToRemove = new ArrayList<Enemy>();
			
			for(Projectile p : allShots)
			{
				Rectangle shotBox = p.getBounds();
		
				for(Enemy e : plants)
				{
					if(shotBox.intersects(e.getHitZone()))
					{
						e.reduceHealth();	
						// This is where layer hit enemy
						if(e.isDead())
						{
							player.enemyKilled++;
							enemyToRemove.add(e);
						}
						toRemove.add(p);
					}
				}
			}
			
			// remove all colliding projectile
			allShots.removeAll(toRemove);
			// clear list
			toRemove.clear();
			// remove dead enemy
			plants.removeAll(enemyToRemove);
			enemyToRemove.clear();
		}
		else if(map == Map.BOSSHOUSE)
		{
			//get all shot colliderss
			ArrayList<Projectile> allShots = player.getShotColliders();
			ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
			
			ArrayList<Enemy> enemyToRemove = new ArrayList<Enemy>();
			
			for(Projectile p : allShots)
			{
				Rectangle shotBox = p.getBounds();
		
				for(Enemy e : boss)
				{
					if(shotBox.intersects(e.getHitZone()))
					{
						e.reduceHealth();	
						// This is where layer hit enemy
						if(e.isDead())
						{
							player.enemyKilled++;
							enemyToRemove.add(e);
						}
						toRemove.add(p);
					}
				}
			}
			
			// remove all colliding projectile
			allShots.removeAll(toRemove);
			// clear list
			toRemove.clear();
			// remove dead enemy
			boss.removeAll(enemyToRemove);
			enemyToRemove.clear();
		}
		
	}
	
	public void CheckBulletToPlayer(Map whichMap)
	{
		if(whichMap == Map.BOSSHOUSE)
		{
			ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
			
			// Get player colliders
			Rectangle playerBox = player.getCollider();			
			
			for(Enemy e : boss)
			{
				ArrayList<Projectile> allEShots = e.getShotColliders();
				
				for(Projectile p : allEShots)
				{					
					if(p.getBounds().intersects(playerBox))
					{
						// player getting hit
						player.takeDamage();
						
						toRemove.add(p);
					}
				}
				
				// remove all colliding projectile
				allEShots.removeAll(toRemove);
				// clear list
				toRemove.clear();
			}
			
		}
		else if(whichMap == Map.MAINLAND_TWO)
		{
			ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
			
			// Get player colliders
			Rectangle playerBox = player.getCollider();			
			
			for(Enemy e : plants)
			{
				ArrayList<Projectile> allEShots = e.getShotColliders();
				
				for(Projectile p : allEShots)
				{					
					if(p.getBounds().intersects(playerBox))
					{
						// player getting hit
						player.takeDamage();
						
						toRemove.add(p);
					}
				}
				
				// remove all colliding projectile
				allEShots.removeAll(toRemove);
				// clear list
				toRemove.clear();
			}
			
		}
		else if(whichMap == Map.MAINLAND_ONE)
		{
			ArrayList<Projectile> toRemove = new ArrayList<Projectile>();
			
			// Get player colliders
			Rectangle playerBox = player.getCollider();			
			
			for(Enemy e : mages)
			{
				ArrayList<Projectile> allEShots = e.getShotColliders();
				
				for(Projectile p : allEShots)
				{					
					if(p.getBounds().intersects(playerBox))
					{
						// player getting hit
						player.takeDamage();
						
						toRemove.add(p);
					}
				}
				
				// remove all colliding projectile
				allEShots.removeAll(toRemove);
				// clear list
				toRemove.clear();
			}
			
		}
	}
	
	public void DebugMode()
	{
		if(debug)
		{
			player.paintCollider();
			map.paintColliders();
			map.paintTriggers();
			for(Enemy e : mages)
			{
				e.paintCollider();
				//e.paintLookZone();
			}
			for(Enemy e : plants)
			{
				//e.paintLookZone();
				e.paintCollider();
			}
			for(Enemy e : boss)
			{
				//e.paintLookZone();
				e.paintCollider();
			}
		} 
	}
	
	
	
	
	
	// Key type event @override
	public void keyTyped(KeyEvent arg0) {	}
	@Override
	public void mouseEntered(MouseEvent arg0) {	}
	@Override
	public void mouseExited(MouseEvent arg0) { }
	@Override
	public void mousePressed(MouseEvent arg0) {	}
	@Override
	public void mouseReleased(MouseEvent arg0) { }
	
}
