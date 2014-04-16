import java.util.Random;


public class Map {
	
static int size;
char[][] map;

	void setMapSize(int size)
	{
	    map = new char[size][size];
	    this.size = size;
		
	}
	
	int getsize()
	{
		return size;
	}
	
	void generateMap()
	{
		Random ran = new Random();
		
		for(int i =0; i < size; i++)
		{
			for(int j =0; j < size; j++)
			{
				int x = ran.nextInt(5);
				if(x<3)
					map[i][j] = 'G';
				else 
					map[i][j] = 'B';
			}	
		}
		
		//assigning yellow block
		int row = ran.nextInt(size);
		int col = ran.nextInt(size);
		
		map[row][col] = 'Y';	
		
	}
	
	void printMap()
	{
		for(int i =0; i < size; i++)
		{
			System.out.print('|');
			for(int j =0; j < size; j++)
			{
				System.out.print(map[i][j]);
				System.out.print('|');
			}
			System.out.println();
		}
	}

	
	char getTileType(int x, int y)
	{
		return map[x][y];
	}


}
