

public class Entity
{
	private String name;
	private int health;
	private Inventory inventory;
	private boolean isPlayer;
	private int xCoor;
	private int yCoor;
	
	//constructor for entity with a name input as a parameter (user player)
	public Entity(String name)
	{
		this.name = name;
		health = 100;
		inventory = new Inventory(10);
		isPlayer = true;
		xCoor = 0;
		yCoor = 0;
	}
	
	//constructor for entity with set name (enemy)
	public Entity()
	{
		name = "Bob";
		health = 200;
		inventory = new Inventory(5);
		isPlayer = false;
		xCoor = 1;
		yCoor = 1;
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
	
	//adds health to health
	public void setHealth(int modHealth)
	{
		health = health + modHealth;
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

	
}
