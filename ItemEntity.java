public class ItemEntity extends Entity
{
	private boolean isItem;
	
	public ItemEntity()
	{
		super();
		isItem = true;
		isStairs = false;
		if (name == "Food Stuffs" || name == "Healing Potions" || name == "Mana Potions" || name == "Antidotes")
		{
			isConsumable = true;
		}		
	}

	public boolean getIsItem()
	{
		return isItem;
	}

	public void setIsItem(boolean itemSet)
	{
		isItem = itemSet;
	}

	public void setIsStairs(boolean setStairs){
		
		isStairs = setStairs;

	}
}
