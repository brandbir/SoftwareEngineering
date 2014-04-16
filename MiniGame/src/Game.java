import java.util.Random;
import java.util.Scanner;


public class Game {
	


	public static void main(String args[])
	{
		startGame();
		
		Scanner keyboard = new Scanner(System.in);
		System.out.println("How many players are going to play? : ");
		int players = keyboard.nextInt();
		setNumPlayers(players);

	}
	
	
	static boolean setNumPlayers(int n)  
	{
		Scanner keyboard = new Scanner(System.in);
		Random ran = new Random();
		//generating random position
		Player p = new Player();
		Map m = new Map();
		int x = ran.nextInt(m.getsize());
		int y = ran.nextInt(m.getsize());
		
		p.setPosition(x, y);
		//System.out.println("x-coord : " + p.player_pos.x_coord + "y-coord : " + p.player_pos.y_coord);
		System.out.println("Please make a move : ");  //should be placed in a separate method....
		char direction = keyboard.next().charAt(0);
		p.move(direction);
		
		//System.out.println("x-coord : " + p.player_pos.x_coord + "y-coord : " + p.player_pos.y_coord);
		
		return true;
	}
	
	void generateHTMLFiles()
	{
		
	}
	
	static void startGame()
	{
		Scanner keyboard = new Scanner(System.in);
		System.out.println("Enter size of map : \n");
		int size = keyboard.nextInt();
		Map map = new Map();
		map.setMapSize(size);
		map.generateMap();
		map.printMap();
	}
	
}
