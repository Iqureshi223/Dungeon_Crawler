/*Bryan Potts
 * CPSC 240
 * 9/26/2021
 * Inventory Assignment
 */

//This class creates an item to be stored in the inventory of the user

public class Item{
	
	//variables
	private ItemType type;
	private String name;
	private int weight;
	private int value;
	private int strength;

	//constructor
	public Item(ItemType type, String name, int weight, int value, int strength){
		this.type = type;
		this.name = name;
		this.weight = weight;
		this.value = value;
		this.strength = strength;
	}

	//get methods
	public int getWeight(){
		return weight;
	}

	public int getValue(){
		return value;
	}

	public String getName(){
		return name;
	}

	public ItemType getType(){
		return type;
	}

	//it doesn't ask for this in the design but it fits with the others
	public int getStrength(){
		return strength;
	}

	//returns a string of the item's contents
	public String toString(){
		return name + "\t\t" + weight + "\t" + value + "\t" + strength;
	}
}
