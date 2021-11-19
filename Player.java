
public class Player extends Entity
{
	
	private boolean isStairs;

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
	public boolean setIsPlayer(boolean play)
	{
		isPlayer = play;
=======
	//returns isStairs
	public boolean getIsStairs()
	{
		return isStairs;
	}


}
