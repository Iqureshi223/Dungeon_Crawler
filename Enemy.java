public class Enemy extends Entity
{
	private String name;
	private int health;
	private Inventory inventory;
	private boolean isPlayer;
	private int xCoor;
	private int yCoor;
		
	public Enemy()
	{
		super();
		AIMovement = false;
		isStairs = false;
	}

	public void setAIMovement(boolean AIMove)
	{
		AIMovement = AIMove;
	}
}
