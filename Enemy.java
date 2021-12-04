public class Enemy extends Entity
{
	/**
	 * @author Cooper Fulton
	 * the class creates an enemy object that extends the Entity class
	 */
	private String name;
	private int health;
	private Inventory inventory;
	private boolean isPlayer;
	private int xCoor;
	private int yCoor;
	/**
	 * constructor that creates an enemy object	
	 */
	public Enemy()
	{
		super();
		AIMovement = false;
		isStairs = false;
	}
 	/**
	 * modifies setAIMovement
	 * @param AIMove
	 */
	public void setAIMovement(boolean AIMove)
	{
		AIMovement = AIMove;
	}
}
