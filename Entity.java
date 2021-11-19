
abstract class Entity

{
	protected String name;
	protected int health;
	protected Inventory inventory;
	protected boolean isPlayer;
	protected int xCoor;
	protected int yCoor;
	protected boolean isItem;
		
	//constructor for entity with set name (enemy)
	public Entity()
	{
		name = "Bob";
		health = 50;
		inventory = new Inventory(100);
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
	//returns isItem
	public boolean getIsItem()
	{
		return isItem;
	}
	//adds health to health
	public abstract void setHealth(int addHealth);	
	//modifies xCoor
	public abstract void setXCoor(int xChange);
	
	//modifies yCoor
	public abstract void setYCoor(int yChange);
	
	//sets isItem
	public abstract void setIsItem(boolean itemSet);
	
	//sets name
	public abstract void setName(String word);


}
