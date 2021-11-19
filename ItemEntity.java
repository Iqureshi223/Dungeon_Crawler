public class ItemEntity extends Entity
{
	private boolean isItem;
	
	public ItemEntity()
	{
		super();
		isItem = true;
		isStairs = false;		
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
