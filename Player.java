
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
	
	
	//returns isStairs
	public boolean getIsStairs()
	{
		return isStairs;
	}

	
	//adds health to health
	public void setHealth(int addHealth)
	{
		health += addHealth;
		super.setHealth(int addHealth);
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
	
	//sets isStairs
	public void setIsStairs(boolean stairs)
	{
		isStairs = stairs;
	}
}
