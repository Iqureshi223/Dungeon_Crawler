/*Bryan Potts
 * CPSC 240
 * 9/26/2021
 * Inventory Assignment
 */

//This class is used to create randomised items for the main and inventory classes
/**
*This class generates randomized Item objects.
*This class uses random number generators and an array of prexisting string variables to create a randomized Item object.
*@author Bryan Potts
*/
import java.util.Random;
public class ItemGenerator{
	
	//create a random for number generation
	private static Random random = new Random();

	//create arrays to store possible item names
	private static String[] namesWeapon = {"Iron Sword", "Wooden Bow", "Wooden Staff", "Steel Sword", "Steel Bow", "Steel Staff", "Enchanted Sword", "Enchanted Bow", "Enchanted Staff", "Battle Axe"};
	private static String[] namesArmor = {"Platemail", "Dark Cloak", "Breastplate", "Bronze Shield", "Iron Shield", "Mithril Shield", "Chainmail", "Leather Boots", "Iron Gauntlets", "Steel Helmet"};
	private static String[] namesOther = {"Food Stuffs", "Magic Lantern", "Thick Rope", "Magic Tomes", "Healing Potions", "Mana Potions", "Gemstones", "Antidotes", "Necklaces", "Magic Rings"};

	//item generator method
	/**
	*This method generates a random Item.
	*This method creates a randomized Item object and returns that item.
	*The ItemType is selected using a random number generator (RNG).
	*The name is selected from an array of prexisting names, the array used is dependant on ItemType.
	*The weight is randomly generated. using an RNG.
	*The strength uses the weight and an RNG to calculate a value.
	*The value uses weight, strength, and RNG to generate an int.
	*@return an Item object
	*/
	public static Item generate(){
		
		//variables
		ItemType type;
		String name;
		int weight;
		int strength;
		int value;

		//determine item type
		switch(random.nextInt(3)){
			case 0:
				type = ItemType.WEAPON;
				break;
			case 1:
				type = ItemType.ARMOR;
				break;
			case 2:
				type = ItemType.OTHER;
				break;
			default:
				type = ItemType.OTHER;
		}

		//create a random number for the name
		int nameInt = random.nextInt(10);

		//determine item name and other variables
		switch(type){
			case WEAPON:
				name = namesWeapon[nameInt];
				weight = random.nextInt(6) + 4;
				strength = (weight * 2) - random.nextInt(4) + random.nextInt(5);
				break;
			case ARMOR:
				name = namesArmor[nameInt];
				weight = random.nextInt(13) + 8;
				strength = (weight * 2) - 10 - random.nextInt(4) + random.nextInt(6);
				break;
			case OTHER:
				name = namesOther[nameInt];
				weight = random.nextInt(4) + 1;
				strength = random.nextInt(7) + 1;
				break;
			default:
				name = "NULL";
				weight = 0;
				strength = 0;		
		}

		//generate a value
		value = strength - weight + random.nextInt(6)+ 5;

		//in case value is negative (only possible for OTHER type items)
		if(value < 0){
			value = 3;
		}

		//in case the default cases run
		if(name.equals("NULL")){
			value = 0;
		}
		
		return new Item(type, name, weight, value, strength);
	}
}
