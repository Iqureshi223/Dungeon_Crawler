
public class Enemy extends Entity
{
	private String name;
	private int health;
	private Inventory inventory;
	private boolean isPlayer;
	private int xCoor;
	private int yCoor;
	private boolean isStairs;
	private boolean AIMovement;

	public Enemy()
	{
		super();
		AIMovement = false;
		isStairs = false;
	}

	public boolean getAIMovement()
	{
		return AIMovement;
	}

	public void setAIMovement(boolean AIMove)
	{
		AIMovement = AIMove;
	}
}
