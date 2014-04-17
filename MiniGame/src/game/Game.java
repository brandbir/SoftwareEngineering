package game;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
	private static Scanner keyboard = new Scanner(System.in);
	private static Map map;
	private static ArrayList<Player> players = new ArrayList<Player>();
	
	/**
	 * Default Constructor which initialises the class variables
	 */
	public Game()
	{
		map = null;
		players = null;
	}
	
	public static void main(String args[])
	{
		startGame();
	}
	
	public static Map getMap()
	{
		return map;
	}

	/**
	 * Sets the number of players that will be playing the game
	 */
	private static void setNumPlayers()
	{
		System.out.print("How many players are going to play? : ");
		int numOfPlayers = keyboard.nextInt();
		for(int i = 0; i < numOfPlayers; i++)
		{
			players.add(new Player(i + 1));
		}
		
		handlingPlayerEvents();
	}
	
	private static int getPlayers()
	{
		return players.size();
	}
	
	private static void handlingPlayerEvents()
	{
		// generating random position
		Random ran = new Random();
		
		for(int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			
			// Get random position for each particular player
			while(player.setPosition(new Position(ran.nextInt(map.getSize()), ran.nextInt(map.getSize()))) != Map.TILE_GRASS)
			{
				// Setting a valid position
			}
		}
		
		generateHTMLFiles();
		
		while(getPlayers() > 0)
		{
			for(int i = 0; i < players.size(); i++)
			{
				// Get input from each user
				System.out.print("Player " + players.get(i).getNumber() + ": ");
				char direction = keyboard.next().charAt(0);
				int nextTile = players.get(i).move(direction);
				
				if(nextTile == Map.TILE_WATER)
				{
					System.out.println("Oops you fell in the water... You Lost :(");
					players.remove(i);
				}
				
				else if(nextTile == Map.TILE_TRES)
				{
					System.out.println("Congratulations, you have found the treasure");
					players.removeAll(players);
				}
				
				else if(nextTile == Map.TILE_INVALID)
				{
					System.out.println("Invalid Direction");
				}
			}
		}
	}

	private static void generateHTMLFiles()
	{
		Misc.deleteFiles("external\\maps");
		
		for(int i = 1; i < getPlayers() + 1; i++)
		{
			try
			{
				BufferedWriter w = new BufferedWriter(new FileWriter("external\\maps\\map_player_" + i +".html"));
				w.write("<!DOCTYPE html>\n");
				w.write("<html>\n");
				w.write("\t<head>\n");
				w.write("\t\t<title>MinGame Map</title>\n");
				w.write("\t\t<script>\n");
				w.write("\t\t\tvar timer;\n");
				w.write("\t\t\tfunction refreshmypage(){\n");
				w.write("\t\t\tdocument.location=document.location.href;}\n");
				w.write("\t\t\ttimer=setTimeout(refreshmypage,1000)\n"); 
				w.write("\t\t</script>\n");
				w.write("\t</head>\n");
				w.write("\t<body>\n");
				w.write("\t\t<table style='width:40%; margin-left:auto; margin-right:auto'>\n");
				w.write("\t\t\t<tr>\n");
				w.write("\t\t\t\t<td colspan='" + map.getSize());
				w.write("' style='font-size:20px; text-align:center; font-style:italic'> miniGame - Player ");
				w.write(i + " </td>\n");
				w.write("\t\t\t</tr>\n");
				
				for(int row = 0; row < map.getSize(); row++ )
				{
					w.write("\t\t\t<tr>\n");
					for(int col = 0; col < map.getSize(); col++)
					{
						w.write("\t\t\t\t<td style='text-align:center; width:45px; height:45px; background-color:");
						w.write(map.getTileType(row, col));
						
						if(players.get(i-1).getPosition().equals(new Position(row, col)))
						{
							w.write("'>\n");
							w.write("\t\t\t\t\t<img src='../images/currentPosition.png' alt='CurrentPositon' height='42' width='42'>\n\t\t\t\t");
						}
						else
						{
							w.write("'>");
						}
						w.write("</td>\n");
					}
					
					w.write("\t\t\t</tr>\n");
				}
				w.write("\t\t</table>\n");
				w.write("\t</body>\n");
				w.write("</html>");
				w.close();
				
			} 
			catch (IOException e)
			{
				LogFile.logError("[Game.generateHTMLFiles()]::" + e.getMessage());
			}
		}
	}

	static void startGame()
	{
		System.out.print("Enter size of map : \n");
		int size = keyboard.nextInt();
		map = new Map();
		map.setSize(size);
		map.generateMap();
		map.printMap();
		setNumPlayers();
	}
	
	
}
