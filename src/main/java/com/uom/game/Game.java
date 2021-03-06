package main.java.com.uom.game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import main.java.com.uom.factory.Map;
import main.java.com.uom.factory.MapFactory;

public class Game
{
	private static Scanner keyboard = new Scanner(System.in);
	private static Map map;
	private static ArrayList<Team> teams = new ArrayList<Team>();
	private static ArrayList<Player> players = new ArrayList<Player>(); 
	private static ArrayList<Player> winners = new ArrayList<Player>();
	private static ArrayList<Player> tempPlayers;
	private static int numberOfTeams = 0;
	
	public static final String MSG_WINNING = "We have winners for this Game! Well Done to the Winners";
	public static final String MSG_REPEAT = "Players, would you like to give another try? Y/N  :";
	
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
	 * @return size
	 */
	public static int getPlayerSize()
	{
		return players.size();
	}

	/**
	 * Returns the number of players that are going to play
	 * @return number of players
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
		Random random = new Random();
		
		for(int i = 0; i < numOfPlayers; i++)
		{
			//Initialisation of a new player
			Player player = new Player(i + 1);
			players.add(player);
			
			
			if(getNumberOfTeams() != 0)
			{
				//selecting a random team in which the player will be part of
				int team = random.nextInt(getNumberOfTeams());
				Team selectedTeam = teams.get(team);
				player.setTeam(selectedTeam);
				selectedTeam.addObserver(player);
			}
		}
		
		return players;
	}
	
	public static void setNumberOfTeams(int t)
	{
		numberOfTeams = t;
	}
	
	public static int getNumberOfTeams()
	{
		return numberOfTeams;
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
		Team playerTeam;
		
		for(int i = 0; i < players.size(); i++)
		{
			Player player = players.get(i);
			
			if(generatePlayerPos)
			{
				//Get random position for each particular player
				player.setPosition(map, Player.getInitialPosition(player, map));
			}
			
			generateHTMLFiles(true, player, map, player.getPosition());
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
					playerTeam = player.getTeam();
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
					
					//At this point, html is changed for a particular player. We need to alter the html for every player
					//of that particular team
					
					if(getNumberOfTeams() == 0)
						generateHTMLFiles(false, player, map, player.getPosition());
					else
						playerTeam.updateHTMLTeam(player.getPosition());
				}
				else
				{
					System.out.println("We have " + winners.size() + " winner/s for this game!");
					if(teams.size() != 0)
					{
						for(int j = 0; j < winners.size(); j++)
						{
							System.out.println("Team " + winners.get(j).getTeam().getTeamLetter() + " has won this game!");
						}
					}
					return MSG_WINNING;
				}
			}
		}
		
		System.out.print(MSG_REPEAT);
		
		if(!test)
		{
			if(keyboard.next().charAt(0) == 'Y')
			{
				if(teams.size() != 0)
				{
					//Removing  old observers
					for(int i = 0; i < teams.size(); i++)
					{
						teams.get(i).deleteObservers();
					}
				}
				for(Player player : tempPlayers) 
				{
					players.add(player);
					if(teams.size() != 0)
						player.getTeam().addObserver(player);
				}
				
				handlingPlayerEvents(map, players, false, false);
			}
			else
			{
				System.out.println("Goodbye!");
			}
		}
		
		return MSG_REPEAT;
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
	public static void generateHTMLFiles(boolean init, Player player, Map map, Position pos)
	{
		if(init) 
			Misc.writeToFile(map,"external/maps/map_player_" + player.getNumber() +".html", init, player, pos);
		
		else
			Misc.writeToFile(map,"external/maps/map_player_" + player.getNumber() +".html", false, player, pos);
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
			.append("' style='font-size:20px; text-align:center; font-style:italic'>");
	
		if(player.getTeam() != null)
		{
			buff.append("Team ")
				.append(player.getTeam().getTeamLetter())
				.append(" - ");
		}
		
		buff.append("Player ")
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
			.append("\n\t\t\tfunction updatePosition(x, y, color, changePos)\n")
			.append("\t\t\t{\n")
			.append("\t\t\t\tdocument.getElementById(x + \'-\' + y).style.backgroundColor = color;\n")
			.append("\t\t\t\tif(changePos)\n")
			.append("\t\t\t\t{\n")
			.append("\t\t\t\t\tvar currentPos = document.getElementById('currentPosition');\n")
			.append("\t\t\t\t\tcurrentPos.parentNode.removeChild(currentPos);\n")
			.append("\t\t\t\t\tvar newPos = document.getElementById(x + \'-\' + y);\n")
			.append("\t\t\t\t\tnewPos.appendChild(currentPos);\n")
			.append("\t\t\t\t}\n")
			.append("\t\t\t\telse\n")
			.append("\t\t\t\t{\n")
			.append("\t\t\t\t\tdocument.getElementById(x + '-' + y).style.opacity = '0.65';\n")
			.append("\t\t\t\t}\n")
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
		//Creating a directory to hold HTML files for the players
		Misc.createDirectory("external/maps");
		
		//checks for a collaborative or individual game
		collaborativeMode();
		
		int numOfPlayers = getNumberOfPlayers();
		players = setNumPlayers(numOfPlayers, players);
		
		System.out.print("Enter size of map : ");
		try
		{  
			int size = Integer.parseInt(keyboard.nextLine());
			System.out.println("Enter Safe or Hazardous Map : ");
			String choice = keyboard.next();
			MapFactory create = new MapFactory();
			map = create.createMap(choice, numOfPlayers, size);
			//map.generateMap();
			handlingPlayerEvents(map, players, true, false);	
		}
		catch(NumberFormatException e)
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
	public static String updateHTML(Map map2, Player player, Position p)
	{
		BufferedReader reader = null;
		StringBuilder buff = new StringBuilder();
		boolean changePos = true;
		
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
			
			int x = p.getX();
			int y = p.getY();
			
			if(!(player.getPosition().equals(p)))
			{
				changePos = false;
			}
			
			//Updating the HTML File with the new Position of the player
			buff.append("\t\t\tupdatePosition(")
				.append(x)
				.append(", ")
				.append(y)
				.append(",'")
				.append(map2.getTileType(x, y))
				.append("', ")
				.append(changePos)
				.append(");\n")
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
	
	/**
	 * Handling input data regarding Collaborative Mode
	 */
	public static void collaborativeMode()
	{
		System.out.print("Do you want to play in collabaritve mode? <Y/N> ");
		String choice = " ";
		boolean flag = false;
		
		while(!choice.equals("Y") & !choice.equals("N"))
		{
			choice = keyboard.nextLine();
			
			if(choice.equals("Y"))
			{
				//handling multiple teams;
				System.out.print("How many teams are going to play? ");
				do
				{
					try
					{
						numberOfTeams = keyboard.nextInt();
						keyboard.nextLine();
						Game.setNumberOfTeams(numberOfTeams);
						
						for(int i = 0; i < getNumberOfTeams(); i++)
							teams.add(new Team(i + 1));
						
						flag = false;
					}
					catch(InputMismatchException e)
					{
						System.out.print("Please make sure to input an integer: ");
						keyboard.nextLine();
						flag = true;
					}
				}
				while(flag);
			}
			
			else if(!choice.equals("N"))
			{
				System.out.print("Enter a valid input please <Y/N>: ");
			}
		}
	}
}
