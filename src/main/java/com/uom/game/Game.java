package main.java.com.uom.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
	private static Scanner keyboard = new Scanner(System.in);
	private static Map map;
	private static ArrayList<Player> players = new ArrayList<Player>(); 
	private static ArrayList<Player> winners = new ArrayList<Player>();
	private static ArrayList<Player> tempPlayers;
	
	/**
	 * Starting Game
	 */
	public static void main(String args[])
	{
		startGame();
	}
	
	/**
	 * Returns the map of the Game
	 * @return Map
	 */
	public static Map getMap()
	{
		return map;
	}
	
	/**
	 * Returns player size
	 * @return int
	 */
	public static int getPlayerSize()
	{
		return players.size();
	}

	/**
	 * Returns the number of players that are going to play
	 * @return int
	 */
	public static int getNumberOfPlayers()
	{
		int numOfPlayers = -1;
		
		System.out.print("How many players are going to play? ");
		
		try
		{
			numOfPlayers = Integer.parseInt(keyboard.nextLine());
		
			//Added for validation purposes
			while(numOfPlayers < 2 || numOfPlayers > 8)
			{
				System.out.print("Please re-enter the number of players (2-8 players) :");
				numOfPlayers = keyboard.nextInt();
			}
			
		}
		catch(Exception e)
		{
			LogFile.logError("Input error : " + e.getMessage());
			System.out.println("You should have entered an integer.");
			System.exit(0);
		}
	
		return numOfPlayers;

	}
	/**
	 * Sets the number of players that will be playing the game
	 */
	public static ArrayList<Player> setNumPlayers(int numOfPlayers, ArrayList<Player> players)
	{

		for(int i = 0; i < numOfPlayers; i++)
		{
			players.add(new Player(i + 1));
		}
		return players;
	}
	
	/**
	 * Handles all the player events
	 * 	1. Generation of HTML Files
	 * 	2. Movement of players
	 * 	3. Updates corresponding HTML Files
	 */
	public static String handlingPlayerEvents(Map map, ArrayList<Player> players, boolean generatePlayerPos, boolean test)
	{
		//Generating random position
		
		Misc.deleteFiles("external/maps");
		char directions[] = {'L', 'R', 'U', 'D'};
		Random ran = new Random();
		
		for(int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			
			if(generatePlayerPos)
			{
				//Get random position for each particular player
				player.setPosition(map, Player.getInitialPosition(player, map));
			}
			
			generateHTMLFiles(true, player, map);
		}
		
		//Copying players temporarily for future reference in case they will need to play the game again
		tempPlayers = Player.copyPlayers(players);

		//looping until at least one player is still playing the game
		while(players.size() > 0)
		{
			int currentPlayers = players.size();
			
			for(int i = 0; i < currentPlayers; i++)
			{
				//if there is a previous winner the current player needs to perform his last movement
				if(winners.isEmpty() || winners.get(winners.size() - 1).getNumber() < players.get(i).getNumber())
				{
					Player player = players.get(i);
					int playerNumber = player.getNumber();
					int nextTile;
					
					do
					{
						//Get input from each user
						System.out.print("Player " + playerNumber + ": ");
						Character direction = null;
						
						if(!test)
						{
							direction = keyboard.next().charAt(0);
							
						}
						else
						{
							direction = directions[ran.nextInt(4)];
							System.out.println("Direction: " + direction + "\n");
							
						}
						nextTile = player.move(map, direction);
						
						if(nextTile == Map.TILE_WATER)
						{
							currentPlayers--;
							i--;
						}
						
						players = movePlayer(nextTile, player, winners, players).get(0);
					}
					while(nextTile == Map.TILE_INVALID);
					
					//Modify the HTML map for this particular Player
					generateHTMLFiles(false, player, map);
				}
				else
				{
					System.out.println("We have " + winners.size() + " winner/s for this game!");
					return "We have winners for this Game! Well Done to the Winners";
				}
			}
		}
		
		System.out.print("Players, would you like to give another try? Y/N  :");
		
		if(!test)
		{
			if(keyboard.next().charAt(0) == 'Y')
			{
				for(Player player : tempPlayers)
					players.add(player);
				
				handlingPlayerEvents(map, players, false, false);
			}
			else
			{
				System.out.println("Goodbye!");
			}
		}
		
		return "Players, would you like to give another try? Y/N";
	}

	/**
	 * Removing Player in case he found a water tile
	 * @param playerNumber the player number
	 */
	public static ArrayList<Player> removePlayer(int playerNumber, ArrayList<Player> players)
	{
		for(int i = 0; i < players.size(); i++)
		{
			Player p = players.get(i);
			
			if(p.getNumber() == playerNumber)
			{
				players.remove(i);
			}
		}
		
		return players;
	}
	
	/**
	 * Handling the movement of the player
	 * @param nextTile the new position of the player
	 * @param player The player that performed the last movement
	 */
	public static ArrayList<ArrayList<Player>> movePlayer(int nextTile, Player player, ArrayList<Player> winners, ArrayList<Player> players)
	{
		ArrayList<ArrayList<Player>> lists = new ArrayList<ArrayList<Player>>();
		ArrayList<Player> updatedPlayers = null;;
		if(nextTile == Map.TILE_WATER)
		{
			System.out.println("Game Over Player " + player.getNumber());
			updatedPlayers = removePlayer(player.getNumber(), players);
		}
		
		else if(nextTile == Map.TILE_TREASURE)
		{
			System.out.println("Congratulations Player " + player.getNumber() + ", you have found the Treasure");
			winners.add(player);
		}
		
		else if(nextTile == Map.TILE_INVALID)
		{
			System.out.println("Invalid Direction, move using (U)p, (D)own, (R)ight and (L)eft within the map's boundaries");
		}
		
		lists.add(players);
		lists.add(updatedPlayers);
		return lists;
	}
	/**
	 * Generates HTML files
	 * @param init Whether HTML files are created for initialisation or for updating
	 * @param player Player to which HTML files are to be delivered
	 */
	public static void generateHTMLFiles(boolean init, Player player, Map map)
	{
		if(init) 
			Misc.writeToFile(map,"external/maps/map_player_" + player.getNumber() +".html", init, player);
		
		else
			Misc.writeToFile(map,"external/maps/map_player_" + player.getNumber() +".html", false, player);
	}
	
	/**
	 * Generates HTML Code for a particular player
	 * @param player The player to which the HTML rendered code is delivered
	 * @return Initialisation HTML code
	 */
	
	public static String generateHTMLCode(Map map, Player player)
	{
		StringBuffer buff = new StringBuffer();
		buff.append("<!DOCTYPE html>\n")
			.append("<html>\n")
			.append("\t<head>\n")
			.append("\t\t<meta charset='UTF-8'>\n")
			.append("\t\t<title>MinGame Map</title>\n")
			.append("\t\t<style type='text/css'>\n")
			.append("\t\t\t td\n")
			.append("\t\t\t {\n")
			.append("\t\t\t\t text-align:center;\n")
			.append("\t\t\t\t width: 45px;\n")
			.append("\t\t\t\t height: 45px;\n")
			.append("\t\t\t\t background-color:")
			.append((map.getTileType(Map.TILE_HIDDEN)))
			.append(";\n")
			.append("\t\t\t }\n")
			.append("\n\t\t\t #header\n")
			.append("\t\t\t {\n")
			.append("\t\t\t\t border-top-left-radius: 200px;\n")
			.append("\t\t\t\t border-top-right-radius: 200px;\n")
			.append("\t\t\t\t color: #FFFFFF;\n")
			.append("\t\t\t}\n")
			.append("\t\t</style>\n")
			.append("\t</head>\n")
			.append("\t<body>\n")
			.append("\t\t<table style='width:40%; margin-left:auto; margin-right:auto'>\n")
			.append("\t\t\t<tr>\n")
			.append("\t\t\t\t<td id='header' colspan='" + map.getSize())
			.append("' style='font-size:20px; text-align:center; font-style:italic'> miniGame - Player ")
			.append(player.getNumber() + " </td>\n")
			.append("\t\t\t</tr>\n");
		
		for(int row = 0; row < map.getSize(); row++ )
		{
			buff.append("\t\t\t<tr>\n");
			for(int col = 0; col < map.getSize(); col++)
			{
				buff.append("\t\t\t\t<td id='")
				.append(row)
				.append("-")
				.append(col);
				
				if(player.getPosition().equals(new Position(row, col)))
				{
					buff.append("' style='text-align:center; width:45px; height:45px; background-color:")
						.append(map.getTileType(Map.TILE_GRASS))
						.append("'>\n")
						.append("\t\t\t\t\t<img id='currentPosition' src='../images/currentPosition.png' alt='CurrentPositon' height='42' width='42'>\n\t\t\t\t");
				}
				
				else
				{
					buff.append("'>");
				}
				
				buff.append("</td>\n");
			}
			
			buff.append("\t\t\t</tr>\n");
		}
		
		buff.append("\t\t</table>\n")
			.append("\t\t<script>\n")
			.append("\t\t\tvar timer;\n")
			.append("\n\t\t\tfunction refreshmypage()\n")
			.append("\t\t\t{\n")
			.append("\t\t\t\tdocument.location=document.location.href;\n")
			.append("\t\t\t}\n")
			.append("\n\t\t\ttimer=setTimeout(refreshmypage,1000);\n")
			.append("\n\t\t\tfunction updatePosition(x, y, color)\n")
			.append("\t\t\t{\n")
			.append("\t\t\t\tdocument.getElementById(x + \'-\' + y).style.backgroundColor = color;\n")
			.append("\t\t\t\tvar currentPos = document.getElementById('currentPosition');\n")
			.append("\t\t\t\tcurrentPos.parentNode.removeChild(currentPos);\n")
			.append("\t\t\t\tvar newPos = document.getElementById(x + \'-\' + y);\n")
			.append("\t\t\t\tnewPos.appendChild(currentPos);\n")
			.append("\t\t\t}\n\n")
			.append("\t\t\t//Player Directions\n")
			.append("\t\t</script>\n")
			.append("\t</body>\n")
			.append("</html>");
		
		return buff.toString();
	}
	
	/**
	 * Starts the Game
	 */
	public static void startGame()
	{
		int numOfPlayers = getNumberOfPlayers();
		players = setNumPlayers(numOfPlayers, players);
		
		System.out.print("Enter size of map : ");
		try
		{
			int size = Integer.parseInt(keyboard.nextLine());
			map = new Map();
			map.setSize(players.size(), size);
			map.generateMap();
			handlingPlayerEvents(map, players, true, false);	
		}
		catch(Exception e)
		{
			LogFile.logError("Input error :: " + e.getMessage());
			System.out.println("You should have entered an integer.");
			System.exit(0);
		}
	}
	
	/**
	 * Updates the map for each player according to each particular
	 * step made by the player
	 * @param player Player that wants his map to be updated
	 * @return updated HTML
	 */
	public static String updateHTML(Map map,Player player)
	{
		BufferedReader reader = null;
		StringBuilder buff = new StringBuilder();
		
		try
		{
			File file = new File("external/maps/map_player_" + player.getNumber() +".html");
			reader = new BufferedReader(new FileReader(file));
		} 
		catch (FileNotFoundException e)
		{
			LogFile.logError("Game.updateHTML::" + e.getMessage());
		}
		
		String line = "";
		
		try
		{
			//Reading the current file until the point to which the file will be modified
			while ((line = reader.readLine()) !=null && !line.contains("//Player Directions"))
				buff.append(line + "\n");
			
			int x = player.getPosition().getX();
			int y = player.getPosition().getY();
			
			//Updating the HTML File with the new Position of the player
			buff.append("\t\t\tupdatePosition(")
				.append(x)
				.append(", ")
				.append(y)
				.append(",'")
				.append(map.getTileType(x, y))
				.append("');\n")
				.append("\t\t\t//Player Directions\n");

			//Continue reading the rest of the file after appending the direction of the 
			//player at the appropriate line number
			while ((line = reader.readLine()) != null)
				buff.append(line + "\n");
			
			reader.close();
		}
		catch (IOException e)
		{
			LogFile.logError("Game.updateHTML:: " + e.getMessage());
		}
		
		return buff.toString();
	}
}
