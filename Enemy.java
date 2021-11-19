
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
		name = "Bob";
		health = 50;
		inventory = new Inventory(200);
		isPlayer = false;
		xCoor = 1;
		yCoor = 1;
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
