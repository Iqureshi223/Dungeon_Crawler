public class ItemEntity extends Entity
{
	/**
	 * @author Cooper Fulton
	 * the class creates an itemEntity object that extends the Entity class
	 */
	private boolean isItem;
	
	/**
	 * constructor that creates an itemEntity
	 * sets isItem to true and makes the item consumable if the name equals one of the consumable items
	 */
	public ItemEntity()
	{
		super();
		isItem = true;
		isStairs = false;
		if (name.equals("Food Stuffs") || name.equals("Healing Potions") || name.equals("Mana Potions") || name.equals("Antidotes"))
		{
			isConsumable = true;
		}		
	}
	/** 
	 * @return isItem
	 */ 
	public boolean getIsItem()
	{
		return isItem;
	}
	
	/**
	 * modifies isItem
	 * @param itemSet
	 */
	public void setIsItem(boolean itemSet)
	{
		isItem = itemSet;
	}
	
	/**
	 * modifies isStairs
	 * @param setStairs
	 */
	public void setIsStairs(boolean setStairs){
		
		isStairs = setStairs;

	}
}
