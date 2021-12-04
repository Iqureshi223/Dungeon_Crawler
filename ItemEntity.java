public class ItemEntity extends Entity
{
	private boolean isItem;
	
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
