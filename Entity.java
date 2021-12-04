public class Entity

{	
	/**
	 * @author Cooper Fulton
	 * the class creates an entity object with a name, health amount, inventory, and coordinates
	 * the class also sets multiple boolean variables to determine what type of entity is created
	 */

	protected String name;
	protected int health;
	protected Inventory inventory;
	protected boolean isPlayer;
	protected int xCoor;
	protected int yCoor;
	protected boolean isItem;
	protected boolean isStairs;
	protected boolean AIMovement;
	protected boolean isConsumable;
		
	/**constructor for entity with set name (enemy)
	 */
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
		isConsumable = false;
	}
	
	/**@return name
	 */
	public String getName()
	{
		return name;
	}
	/**@returns health
	 */
	public int getHealth()
	{
		return health;
	}
	/**@returns inventory
	 */
	public Inventory getInventory()
	{
		return inventory;
	}
	/**@returns isPlayer
	 */
	public boolean getIsPlayer()
	{
		return isPlayer;
	}
	/**@returns xCoor
	 */
	public int getXCoor()
	{
		return xCoor;
	}
	/**@returns yCoor
	 */
	public int getYCoor()
	{
		return yCoor;
	}
	/**@returns isItem
	 */
	public boolean getIsItem()
	{
		return isItem;
	}
	
	/**@returns isStairs
	 */
	public boolean getIsStairs()
	{
		return isStairs;
	}

	/**@returns AIMovement
	 */
	public boolean getAIMovement()
	{
		return AIMovement;
	}

	/**@returns isConsumbale
	 */
	public boolean getIsConsumable()
	{
		return isConsumable;
	}
	/**adds health to health
	 * @param addHealth
	 */
	public void setHealth(int addHealth)
	{
		health += addHealth;
	}	
	/**modifies xCoor
	 * @param xChange
	 */
	public void setXCoor(int xChange)
	{
		xCoor = xChange;
	}
	
	/**modifies yCoor
	 * @param yChange
	 */
	public void setYCoor(int yChange)
	{
		yCoor = yChange;
	}
	
	/**sets isItem
	 * @param itemSet
	 */
	public void setIsItem(boolean itemSet)
	{
		isItem = itemSet;
	}
	
	/**sets name
	 * @param word
	 */
	public void setName(String word)
	{
		name = word;
	}
	
	/**sets isConsumbale
	 * @param consume
	 */
	public void setisConsumable(boolean consume)
	{
		isConsumable = consume;
	}
	
}
