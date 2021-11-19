public class Entity

{
	protected String name;
	protected int health;
	protected Inventory inventory;
	protected boolean isPlayer;
	protected int xCoor;
	protected int yCoor;
	protected boolean isItem;
	protected boolean isStairs;
	protected boolean AIMovement;
		
	//constructor for entity with set name (enemy)
	public Entity()
	{
		name = "Bob";
		health = 50;
		inventory = new Inventory(100);
		isPlayer = false;
		xCoor = 1;
		yCoor = 1;
		isStairs = false;
		AIMovement = false;
	}
	
	//returns name
	public String getName()
	{
		return name;
	}
	//returns health
	public int getHealth()
	{
		return health;
	}
	//returns inventory
	public Inventory getInventory()
	{
		return inventory;
	}
	//returns isPlayer
	public boolean getIsPlayer()
	{
		return isPlayer;
	}
	//returns xCoor
	public int getXCoor()
	{
		return xCoor;
	}
	//returns yCoor
	public int getYCoor()
	{
		return yCoor;
	}
	//returns isItem
	public boolean getIsItem()
	{
		return isItem;
	}
	
	//returns isStairs
	public boolean getIsStairs()
	{
		return isStairs;
	}

	//returns AIMovement
	public boolean getAIMovement()
	{
		return AIMovement;
	}

	//adds health to health
	public void setHealth(int addHealth)
	{
		health += addHealth;
	}	
	//modifies xCoor
	public void setXCoor(int xChange)
	{
		xCoor = xChange;
	}
	
	//modifies yCoor
	public void setYCoor(int yChange)
	{
		yCoor = yChange;
	}
	
	//sets isItem
	public void setIsItem(boolean itemSet)
	{
		isItem = itemSet;
	}
	
	//sets name
	public void setName(String word)
	{
		name = word;
	}

	
}
