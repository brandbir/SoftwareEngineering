
public class Player {

Position player_pos;
Map map = new Map();

void move(char direction)
{
	Position player_pos = new Position();
	if(direction =='L')
		if(player_pos.x_coord >= 0) //adhering to boundaries
			setPosition(player_pos.x_coord - 1,player_pos.y_coord);
	if(direction == 'R')
		if(player_pos.x_coord <= map.getsize())
		setPosition(player_pos.x_coord + 1, player_pos.y_coord);
	if(direction == 'U')
		if(player_pos.y_coord >= 0)
			setPosition(player_pos.x_coord, player_pos.y_coord + 1);
	if(direction == 'D')
		if(player_pos.y_coord <= map.getsize())
			setPosition(player_pos.x_coord, player_pos.y_coord - 1);
		
}

boolean setPosition(int x, int y)
{
	Position pos = new Position();
	if(x >= 0 && x < map.getsize())
		pos.x_coord = x;
	else
	{
		System.out.println("Out of bounds");
		return false;
	}
	if(y >= 0 && y < map.getsize())
		pos.y_coord = y;
	else
	{
		System.out.println("Out of bounds");
		return false;
	}

	return true;
}

	
}
