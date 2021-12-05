/*Bryan Potts
 * CPSC 240
 * 9/26/2021
 * Inventory Assignment
 */

//This class creates an item to be stored in the inventory of the user
/**
*This class is an item.
*This class holds values for an item that can be either a weapon, armor, or other.
*@author Bryan Potts
*/
public class Item{
	
	//variables
	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;
	private boolean isConsumable;

	//constructor
	/**
	*Constructor for Item class.
	*This constructor stores values for later use.
	*@param type an ItemType
	*@param name a String
	*@param weight an int
	*@param value an int
	*@param strength an int
	*/
	public Item(ItemType type, String name, int weight, int value, int strength){
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
		if (name.equals("Food Stuffs") || name.equals("Healing Potions") || name.equals("Mana Potions") || name.equals("Antidotes")){
			isConsumable = true;
		}
		else{
			isConsumable = false;
		}
	}

	//get methods
	/**
	*Gets weight.
	*Get method for the weight variable.
	*@return an int
	*/
	public int getWeight(){
		return weight;
	}
	
	/**
	*Gets value.
	*Get method for the value variable.
	*@return an int
	*/
	public int getValue(){
		return value;
	}

	/**
	*Gets name.
	*Get method for the name variable.
	*@return a String
	*/
	public String getName(){
		return name;
	}
	
	/**
	*Gets ItemType.
	*Get method for the type variable.
	*@return an ItemType
	*/
	public ItemType getType(){
		return type;
	}

	//it doesn't ask for this in the design but it fits with the others
	/**
	*Gets strength.
	*Get method for strength.
	*@return an int
	*/
	public int getStrength(){
		return strength;
	}

	/**
	*Gets isConsumable.
	*Get method for if the item can be used.
	*@return a boolean
	*/
	public boolean getIsConsumable(){
		return isConsumable;
	}

	//returns a string of the item's contents
	/**
	*Outputs class variables to the user.
	*Outputs name, weight, value, and strength to the terminal.
	*@return a String
	*/
	public String toString(){
		return name + "\t\t" + weight + "\t" + value + "\t" + strength;
	}
}
