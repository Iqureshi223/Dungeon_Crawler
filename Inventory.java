/*
 * 9/26/2021
 * Inventory Assignment
 */

// this class holds an inventory of items and allows users options of what to do with them
/**
*This class acts as an inventory of Item class objects.
*This class also supports options for interacting with that inventory.
*@author Bryan Potts
*/
import java.util.Scanner;
import java.util.ArrayList;
public class Inventory{
	
	//variables
	private ArrayList<Item> items;
	private int maxWeight;
	private Item equippedWeapon;
	private Item equippedArmor;
	
	//constructor
	/**
	*This is the Constructor for the Inventory class.
	*This constructor is given an int value that represents the maximum weight value that all combined Item objects can have in this inventory.
	*This initializes the ArrayList of Item objects.
	*@param maxWeight an int
	*/
	public Inventory(int maxWeight){
		items = new ArrayList<Item>();
		this.maxWeight = maxWeight;
		equippedWeapon = null;
		equippedArmor = null;
	}

	//adds an item if weight is under or equal to max, returns true or false to if item has been added.
	/**
	*This adds an Item to the inventory.
	*This method checks if an item's weight does not exceed the maximum limit, then if it does not exceed the limit the Item object is added to the inventory.
	*Whether or not the item is added to the inventory is returned as a boolean value, true if added.
	*@param item an Item object
	*@return a boolean
	*/
	public boolean add(Item item){
		int checkWeight = 0;

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);
			checkWeight += currentItem.getWeight();
		}
		
		if(checkWeight <= maxWeight){
			items.add(item);
			return true;
		}
		else{
			return false;
		}
	}
	
	//gets the total weight in the arraylist
	/**
	*This method returns the total weight in the inventory.
	*This method calculates and returns the total weight of all Item objects in the inventory at this point in time.
	*@return an int
	*/
	public int totalWeight(){
		int currentWeight = 0;

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);
			currentWeight += currentItem.getWeight();
		}

		return currentWeight;
	}
	
	//prints all items in the inventory
	/**
	*Displays all Items in the inventory.
	*This method gets the name, weight, value, and strength of all the Items in the inventory and outputs them to the user's terminal.
	*/
	public void print(){
		System.out.println("\nItem Name\t\tWeight\tValue\tStrength");

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);
			System.out.println(currentItem.toString());
		}
	}

	//drops an item from the list
	/**
	*Removes an Item from the inventory.
	*This removes an Item object stored in the inventory. The item object removed is selected by the user.
	*/
	public void drop(){
		int counter = 1;
		
		System.out.println("\nDrop an item: ");
		
		if(items.size() == 0){
			System.out.println("\nOops, you have no items to drop.");
			return;
		}

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);
			System.out.println(counter + ".\t" + currentItem.toString());
			counter++;
		}

		Scanner in = new Scanner(System.in);

		System.out.print("\n: ");
		
		try{
			int input = in.nextInt();
			Item droppingItem = items.get(input - 1);

			if(equippedArmor == droppingItem){
				equippedArmor = null;
			}
			else if(equippedWeapon == droppingItem){
				equippedWeapon = null;
			}
			
			items.remove(input - 1);

			System.out.println("\n" + droppingItem.getName() + " has been dropped.");
		}
		catch(Exception e){
			System.out.println("\nBad input. Aborting drop");
		}		
	}
	
	//drops item without user input
	/**
	*Removes an Item from the inventory without user input.
	*This method removes an Item object from the inventory without asking the user which item to drop.
	*The int passed in to the method is the index of the Item in the ArrayList to be removed.
	*@param num an int
	*/
	public void drop(int num)
	{
		try{
			if(equippedArmor == items.get(num)){
				equippedArmor = null;
			}
			else if(equippedWeapon == items.get(num)){
				equippedWeapon = null;
			}
		
			items.remove(num);

		}
		catch(Exception e){
			System.out.println("\nBad input. Aborting drop");
		}
	}

	//equips a weapon, weapon will stay in inventory as well
	/**
	*This method sets the current weapon.
	*This method assigns a weapon to the equipped weapon variable. The Item selected for this is an ItemType.WEAPON Item that is chosen by the user.
	*/
	public void equipWeapon(){
		int counter = 1;
		ArrayList<Integer> itemTracker = new ArrayList<Integer>();

		System.out.println("\nEquip a weapon: ");

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);

			if(currentItem.getType() ==ItemType.WEAPON){
				System.out.println(counter + ".\t" + currentItem.toString());
				counter++;
				itemTracker.add(i);
			}
		}
			
		//condition where there are no weapons in inventory
		if(counter == 1){
			System.out.println("\nOops, you have no weapons.");
			return;
		}

		System.out.print("\n: ");

		Scanner in = new Scanner(System.in);

		try{
			int input = in.nextInt();
			equippedWeapon = items.get(itemTracker.get(input - 1));

			System.out.println("\n" + equippedWeapon.getName() + " has been equipped");
		}
		catch(Exception e){
			System.out.println("\nBad input. Aborting equip");
		}
	}
	
	//same as equip weapon above, but for ARMOR types instead
	/**
	*This method sets the current armor.
	*This method assigns a weapon to the equipped armor variable. The Item selected for this is an ItemType.ARMOR Item that is chosen by the user.
	*/
	public void equipArmor(){
                int counter = 1;
                ArrayList<Integer> itemTracker = new ArrayList<Integer>();

                System.out.println("\nEquip an armor: ");

                for(int i = 0; i < items.size(); i++){
                        Item currentItem = items.get(i);

                        if(currentItem.getType() == ItemType.ARMOR){
                                System.out.println(counter + ".\t" + currentItem.toString());
                                counter++;
                                itemTracker.add(i);
                        }
                }

                //condition where there are no armors in inventory
                if(counter == 1){
                        System.out.println("\nOops, you have no armor.");
                        return;
                }

                System.out.print("\n: ");

                Scanner in = new Scanner(System.in);

                try{
                        int input = in.nextInt();
                        equippedArmor = items.get(itemTracker.get(input - 1));

                        System.out.println("\n" + equippedArmor.getName() + " has been equipped");
                }
                catch(Exception e){
                        System.out.println("\nBad input. Aborting equip");
                }
        }

	//returns equippedWeapon
	/**
	*Gets the equipped weapon.
	*A get method for the equippedWeapon variable.
	*@return an Item object
	*/
	public Item getEquippedWeapon()
	{
		return equippedWeapon;
	}

	//returns equippedArmor
	/**
	*Gets the equipped armor.
	*A get method for the equippedArmor variable.
	*@return an Item object
	*/
	public Item getEquippedArmor()
	{
		return equippedArmor;
	}

	//sets equippedWeapon without user input
	/**
	*Sets the equipped weapon.
	*Set method for the equippedWeapon variable.
	*@param weapon an Item
	*/
	public void setEquippedWeapon(Item weapon)
	{
		equippedWeapon = weapon;
	}

	//sets equippedArmor without user input
	/**
	*Sets the equipped armor.
	*Set method for the equippedArmor variable.
	*@param armor an Item
	*/
	public void setEquippedArmor(Item armor)
	{
		equippedArmor = armor;
	}

	//returns ArrayList of items
	/**
	*Gets the ArrayList of Items.
	*Get method for the ArrayList of Item objects in the inventory.
	*@return an ArrayList of type Item
	*/
	public ArrayList getItems()
	{
		return items;
	}
}

