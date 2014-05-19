package main.java.com.uom.game;

import java.util.Observable;

/**
 * Handles the functionality and the behaviour of a Team
 */
public class Team extends Observable
{
	private String teamLetter = "";
	
	/**
	 * Initialises a Team with a particular Team Letter
	 * @param i integer to be converted to a letter
	 */
	public Team(int i)
	{
		teamLetter = getTeamLetter(i);
	}
	
	/**
	 * Updating each team's member HTML
	 * @param position the last position that was visited by a team's member
	 */
	public void updateHTMLTeam(Position position)
	{
		setChanged();
		notifyObservers(position);
	}
	
	/**
	 * Returning the team's letter
	 * @return Team's letter
	 */
	public String getTeamLetter()
	{
		return teamLetter;
	}
	
	/**
	 * Converting integer to a corresponding letter
	 * @param i number
	 * @return teamLetter letter corresponding to the ASCII table
	 */
	private static String getTeamLetter(int i)
	{
		String teamLetter = "";
		if(i > 0 && i < 27)
			teamLetter =  String.valueOf((char)(i + 64));
		
		return teamLetter;
	}
}