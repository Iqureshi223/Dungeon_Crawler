
public class Player extends Entity
{
	public Player(String name)
	{
		this.name = name;
		health = 50;
		inventory = new Inventory(100);
		isPlayer = true;
		xCoor = 0;
		yCoor = 0;
	}
	//modifies isPlayer
	public void setIsPlayer(boolean play)
	{
		isPlayer = play;
	}

}
