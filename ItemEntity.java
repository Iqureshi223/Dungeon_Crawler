public class ItemEntity extends Entity
{
	private boolean isItem;
	private boolean isStairs; //added -Bryan

	public ItemEntity()
	{
		super();
		isItem = true;
		isStairs = false; //added -Bryan
		
	}

	public boolean getIsItem()
	{
		return isItem;
	}

	public void setIsItem(boolean itemSet) //fixed -Bryan
	{
		isItem = itemSet;
	}

	//added -Bryan
	public boolean getIsStairs(){
		
		return isStairs;
	}

	//added -Bryan
	public void setIsStairs(boolean setStairs){
		
		isStairs = setStairs;

	}
}
