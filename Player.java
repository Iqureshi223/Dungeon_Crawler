
public class Player extends Entity
{
	/**
	 * @author Cooper Fulton
	 * the class creates a player object that extends the entity class 
	 */

	/**
	 * constructor creates a player with isPlayer set to true
	 * @param name
	 */
	public Player(String name)
	{
		this.name = name;
		health = 50;
		inventory = new Inventory(100);
		isPlayer = true;
		xCoor = 0;
		yCoor = 0;
	}
	/**
	 * modifies isPlayer
	 * @param play
	 */
	public void setIsPlayer(boolean play)
	{
		isPlayer = play;
	}

}
