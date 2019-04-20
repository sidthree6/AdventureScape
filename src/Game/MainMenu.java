package Game;

import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

class ButtonList{
	int startX;
	int startY;
	int endX;
	int endY;
	Map whatToLoad;
	
	public ButtonList(int x, int y, int width, int height, Map load) 
	{
		startX = x;
		startY = y;
		endX = x+width;
		endY = y+height;
		whatToLoad = load;
	}
}

public class MainMenu extends JPanel {
	
	private static final long serialVersionUID = 1L;

	ImageIcon MainTitle = new ImageIcon(getClass().getResource("/Game/Image/TitleScreen.jpg"));
	ImageIcon HelpTitle = new ImageIcon(getClass().getResource("/Game/Image/Help_Title.png"));
	ImageIcon InstructionTitle = new ImageIcon(getClass().getResource("/Game/Image/Instruction_TitleScreen.jpg"));
	
	ImageIcon Button = new ImageIcon(getClass().getResource("/Game/Image/Button_1.png"));
	ImageIcon BackButton = new ImageIcon(getClass().getResource("/Game/Image/Back_Button.png"));
	ImageIcon H_Button = new ImageIcon(getClass().getResource("/Game/Image/Help_Button.png"));
	ImageIcon I_Button = new ImageIcon(getClass().getResource("/Game/Image/I_Button.png"));
	
	ImageIcon WinTitle = new ImageIcon(getClass().getResource("/Game/Image/Win_Title.png"));
	ImageIcon LoseTitle = new ImageIcon(getClass().getResource("/Game/Image/Game_Over_Title.png"));
	
	Graphics g; // Graphics instance of game
	
	ArrayList<ButtonList> buttons = new ArrayList<ButtonList>();
	
	Map currentMap;
	
	public MainMenu()
	{
		//..
	}
	
	// Set graphics
	public void Set(Graphics graph, Map mapToLoad)
	{
		g = graph;
		
		g.setFont(new Font("Algerian",Font.BOLD,100));
		
		currentMap = mapToLoad;
		buttons.clear();
	}
	
	// Paint title screen based on instrction
	public void paintTitle(String whichTitle)
	{
		switch(whichTitle)
		{			
		case "main": case "Main": case "title": 
			MainTitle.paintIcon(this, g, 0, 0);
			break;
		case "help":
			HelpTitle.paintIcon(this, g, 0, 0);
			break;
		case "instruction":
			InstructionTitle.paintIcon(this, g, 0, 0);
			break;
		case "win":
			WinTitle.paintIcon(this, g, 0, 0);
			break;
		case "lose":
			LoseTitle.paintIcon(this, g, 0, 0);
			break;
		}
	}
	
	// Paint buttons based on instrction given
	public void paintButton(String whichButton, int x, int y, int width, int height, Map whichToLoad)
	{
		if(whichButton == "play")
		{
			Button.paintIcon(this, g, x, y);			
		}
		else if(whichButton == "help")
		{
			H_Button.paintIcon(this, g, x, y);
		}
		else if(whichButton == "instruction")
		{
			I_Button.paintIcon(this, g, x, y);
		}
		else if(whichButton == "back")
		{
			BackButton.paintIcon(this, g, x, y);
		}
		
		// Add button to lists
		buttons.add(new ButtonList(x,y,width,height,whichToLoad));
	}
	
	public void paintString(String str, int x, int y)
	{
		g.drawString(str,x,y);
	}
	
	public Map whichMapToLoad(int x, int y)
	{
		Map mapToLoad = currentMap;
		
		for(ButtonList b : buttons) 
		{
			if(x > b.startX && x < b.endX && y > b.startY && y < b.endY)
			{
				return b.whatToLoad;
			}
		}
		
		return mapToLoad;
	}
	
}
