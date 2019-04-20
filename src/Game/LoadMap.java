package Game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.util.ArrayList;

public class LoadMap extends JPanel {

	private static final long serialVersionUID = 1L; 
	
	Graphics g;
	
	Map currentMap;
	
	ArrayList<Rectangle> colliders = new ArrayList<Rectangle>();
	ArrayList<Rectangle> triggers = new ArrayList<Rectangle>();
	
	ImageIcon black = new ImageIcon(getClass().getResource("/Game/Image/black.jpg"));
	ImageIcon houseTile = new ImageIcon(getClass().getResource("/Game/Image/insideTile.png"));
	
	ImageIcon cabinet = new ImageIcon(getClass().getResource("/Game/Image/cabinet1.png"));
	ImageIcon cabinet2 = new ImageIcon(getClass().getResource("/Game/Image/cabinet2.png"));
	ImageIcon rug = new ImageIcon(getClass().getResource("/Game/Image/rug.png"));
	ImageIcon table = new ImageIcon(getClass().getResource("/Game/Image/Table.png"));
	ImageIcon bed = new ImageIcon(getClass().getResource("/Game/Image/Bed.png"));
	ImageIcon divider = new ImageIcon(getClass().getResource("/Game/Image/Divider.png"));
	ImageIcon divider2 = new ImageIcon(getClass().getResource("/Game/Image/DividerH.png"));
	ImageIcon vase = new ImageIcon(getClass().getResource("/Game/Image/Vase.png"));
	
	ImageIcon Water = new ImageIcon(getClass().getResource("/Game/Img/Water_1.png"));
	ImageIcon Water_1 = new ImageIcon(getClass().getResource("/Game/Img/Water.jpg"));
	ImageIcon Stone = new ImageIcon(getClass().getResource("/Game/Img/Stone.jpg"));
	ImageIcon Grass = new ImageIcon(getClass().getResource("/Game/Img/Grass_1.png"));
	ImageIcon Sand = new ImageIcon(getClass().getResource("/Game/Img/Sand.jpg"));
	ImageIcon Flora_1 = new ImageIcon(getClass().getResource("/Game/Img/Flora_1.png"));
	ImageIcon House = new ImageIcon(getClass().getResource("/Game/Image/house.png"));
	ImageIcon House2 = new ImageIcon(getClass().getResource("/Game/Image/house2.png"));
	ImageIcon Tree = new ImageIcon(getClass().getResource("/Game/Image/Tree.png"));
	ImageIcon Trunk = new ImageIcon(getClass().getResource("/Game/Image/trunk_1.png"));
	ImageIcon Bush = new ImageIcon(getClass().getResource("/Game/Image/Bush.png"));
	ImageIcon Rock = new ImageIcon(getClass().getResource("/Game/Image/rock.png"));
	ImageIcon Tree_1 = new ImageIcon(getClass().getResource("/Game/Image/treeTop.png"));
	
	int[][] playerHouse = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //3
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //4
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //5
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //6
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //7
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //8
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //9
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //10
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //11
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //12
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //13
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //14
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //15
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //16
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//17
		  // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
	
	int[][] M = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
			{0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0}, //2
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //3
			{0, 8, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //4
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 2, 8, 0}, //5
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //6
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //7
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 2, 1}, //8
			{0, 8, 2, 2, 2, 7, 2, 7, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 1}, //9
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 1}, //10
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 8, 0}, //11
			{0, 8, 2, 7, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //12
			{0, 8, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 8, 0}, //13
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 8, 0}, //14
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //15
			{0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0}, //16
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//17
		  // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
	
	int[][] M2 = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
			{0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0}, //2
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //3
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //4
			{0, 8, 2, 2, 2, 7, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //5
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //6
			{0, 8, 2, 2, 2, 2, 2, 7, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //7
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 8, 2, 2, 7, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //8
			{1, 2, 2, 2, 2, 7, 2, 7, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 1, 2, 8, 0}, //9
			{1, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 8, 8, 7, 2, 2, 2, 2, 2, 2, 2, 1, 2, 8, 0}, //10
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 7, 1, 1, 1, 1, 2, 8, 0}, //11
			{0, 8, 2, 7, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 7, 2, 8, 0}, //12
			{0, 8, 2, 2, 2, 2, 7, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 7, 2, 2, 2, 2, 2, 2, 8, 0}, //13
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 7, 2, 2, 2, 2, 8, 0}, //14
			{0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0, 8, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 8, 0}, //15
			{0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 0}, //16
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//17
		  // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
	
	int[][] bossHouse = {
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //1
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //2
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //3
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //4
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //5
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //6
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //7
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //8
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //9
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //10
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //11
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //12
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //13
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //14
			{0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0}, //15
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //16
			{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}};//17
		  // 1  2  3  4  5  6  7  8  9  10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26
	
	public void Set(Graphics graph,Map map)
	{
		g = graph;
		currentMap = map;
		
		colliders.clear();
		triggers.clear();
	}
	
	public void paintPlayerHouseTile()
	{
		for (int X = 0; X < 17; X++) 
		{ 

			for (int Y = 0; Y < 26; Y++) 
			{ 
				if (playerHouse[X][Y] == 1)
				{

					houseTile.paintIcon(this, g, Y * 50, X * 50);
				}
			}
		}
	}
	
	public void paintPlayerBlockTile()
	{
		for (int X = 0; X < 17; X++) 
		{ 

			for (int Y = 0; Y < 26; Y++) 
			{ 

				if (playerHouse[X][Y] == 0) 
				{ 

					black.paintIcon (this, g, Y * 50, X * 50);
					createCollider(Y*50, X*50, 50, 50);

				}
			}
		}
	}
	
	public void paintOutsideMapOne() 
	{
		for (int X = 0; X < 17; X++) { 

			for (int Y = 0; Y < 26; Y++) { 

				if (M[X][Y] == 0) { 

					Water.paintIcon (this, g, Y*50, X*50);
					createCollider(Y*50,X*50,50,50);

				}else if (M[X][Y] == 1) { 

					Stone.paintIcon (this, g, Y*50, X*50);

				}else if (M[X][Y] == 2) { 

					Grass.paintIcon (this, g, Y*50, X*50);

				}else if (M[X][Y] == 7) { 

					Flora_1.paintIcon (this, g, Y * 50, X * 50);

				}else if (M[X][Y] == 8) { 

					Sand.paintIcon (this, g, Y * 50, X * 50);

				}else if (M[X][Y] == 9) { 

					Water_1.paintIcon (this, g, Y * 50, X * 50);

				}
			}
		}
	}
	
	public void paintOutsideMapTwo()
	{
		for (int X = 0; X < 17; X++) { 

			for (int Y = 0; Y < 26; Y++) { 

				if (M2[X][Y] == 0) { 

					Water.paintIcon (this, g, Y*50, X*50);
					createCollider(Y*50,X*50,50,50);

				}else if (M2[X][Y] == 1) { 

					Stone.paintIcon (this, g, Y*50, X*50);

				}else if (M2[X][Y] == 2) { 

					Grass.paintIcon (this, g, Y*50, X*50);

				}else if (M2[X][Y] == 7) { 

					Flora_1.paintIcon (this, g, Y * 50, X * 50);

				}else if (M2[X][Y] == 8) { 

					Sand.paintIcon (this, g, Y * 50, X * 50);

				}else if (M2[X][Y] == 9) { 

					Water_1.paintIcon (this, g, Y * 50, X * 50);

				}
			}
		}
	}
	
	public void paintBossHouseTile()
	{
		for (int X = 0; X < 17; X++) 
		{ 

			for (int Y = 0; Y < 26; Y++) 
			{ 

				if (bossHouse[X][Y] == 1) 
				{

					houseTile.paintIcon(this, g, Y * 50, X * 50);
				}
			}
		}
	}
	
	public void paintBossHouseBlock()
	{
		for (int X = 0; X < 17; X++) 
		{ 

			for (int Y = 0; Y < 26; Y++) 
			{ 

				if (bossHouse[X][Y] == 0) 
				{ 

					black.paintIcon (this, g, Y * 50, X * 50);
					createCollider(Y*50, X*50, 50, 50);

				}
			}
		}
	}
	
	public ArrayList<Rectangle> getColliders()
	{
		return colliders;
	}
	
	public ArrayList<Rectangle> getTriggers()
	{
		return triggers;
	}
	
	public void paintColliders()
	{
		for(Rectangle c : colliders)
		{
			g.setColor(Color.darkGray);
			g.fillRect(c.x, c.y, c.width, c.height);
		}
	}
	
	public void paintTriggers()
	{
		for(Rectangle t : triggers)
		{
			g.setColor(Color.red);
			g.fillRect(t.x, t.y, t.width, t.height);
		}
	}
	
	public void createCollider(int x, int y, int width, int height)
	{
		colliders.add(new Rectangle(x,y,width,height));
	}
	
	public void createTrigger(int x, int y, int width, int height)
	{
		triggers.add(new Rectangle(x,y,width,height));
	}
	
	public void createCabinet(int x, int y)	
	{ 
		cabinet.paintIcon(this, g, x, y);
		createCollider(x, y+50, 150, 30);
	}	
	
	public void createCabinetTwo(int x, int y) 
	{ 
		cabinet2.paintIcon(this, g, x, y);
		createCollider(x, y + 50, 150, 30);
	}
	
	public void createDivider(int x, int y) 
	{ 
		divider.paintIcon(this, g, x, y);
		createCollider(x,y,25,200);
	}	
	
	public void createDividerTwo(int x, int y) 
	{ 
		divider2.paintIcon(this, g, x, y);
		createCollider(x,y,175,25);
	}
	
	public void createRug(int x, int y) { rug.paintIcon(this, g, x, y); }
	
	public void createTable(int x, int y) 
	{ 
		table.paintIcon(this, g, x, y);
		createCollider(x,y,100,170);
	}
	
	public void createBed(int x, int y) 
	{ 
		bed.paintIcon(this, g, x, y);
		createCollider(x,y,100,120);
	}
	
	public void createVase(int x, int y) 
	{ 
		vase.paintIcon(this, g, x, y);
		createCollider(x,y+30,35,20);
	}
	
	public void createHouse(int x, int y)
	{
		House.paintIcon(this, g, x, y);
		createCollider(x+5,y+100,127,120);
	}
	
	public void createTree(int x, int y)
	{
		Tree.paintIcon(this, g, x, y);
		createCollider(x+160,y + 230,150,10);
		createCollider(x+200,y + 220,100,10);
	}
	
	public void createTreeTwo(int x, int y)
	{
		Trunk.paintIcon(this, g, x, y);
		Tree_1.paintIcon(this, g, x-7, y-60);
		
		createCollider(x,y+50,77,15);
		createCollider(x+10,y+45,57,5);
		createCollider(x+10,y+65,57,5);
	}
	
	public void createBush(int x, int y)
	{
		Bush.paintIcon(this, g, x, y);
		createCollider(x+25, y+40, 47, 40);
	}
	
	public void createRock(int x, int y)
	{
		Rock.paintIcon(this, g, x, y);
		createCollider(x,y,64,12);
	}
	
	public void createBossHouse(int x, int y)
	{
		House2.paintIcon(this, g, x, y);
		createCollider(x+10,y+150,190, 140);
		createCollider(x+20,y+290,170, 5);
		createCollider(x+30,y+295,150, 5);
		createCollider(x+40,y+300,130, 5);
		createCollider(x+50,y+308,130, 5);
	}
}
