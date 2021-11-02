/*
 * 9/26/2021
 * Inventory Assignment
 */

// this class holds an inventory of items and allows users options of what to do with them

import java.util.Scanner;
import java.util.ArrayList;
public class Inventory{
	
	//variables
	private ArrayList<Item> items;
	private int maxWeight;
	private Item equippedWeapon;
	private Item equippedArmor;
	
	//constructor
	public Inventory(int maxWeight){
		items = new ArrayList<Item>();
		this.maxWeight = maxWeight;
		equippedWeapon = null;
		equippedArmor = null;
	}

	//adds an item if weight is under or equal to max, returns true or false to if item has been added.
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
	public int totalWeight(){
		int currentWeight = 0;

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);
			currentWeight += currentItem.getWeight();
		}

		return currentWeight;
	}
	
	//prints all items in the inventory
	public void print(){
		System.out.println("\nItem Name\t\tWeight\tValue\tStrength");

		for(int i = 0; i < items.size(); i++){
			Item currentItem = items.get(i);
			System.out.println(currentItem.toString());
		}
	}

	//drops an item from the list
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
	public void drop(Item item)
	{
		try{
			if(equippedArmor == item){
				equippedArmor = null;
			}
			else if(equippedWeapon == item){
				equippedWeapon = null;
			}
		
			items.remove(item);

			System.out.println("\n" + item.getName() + " has been dropped.");
		}
		catch(Exception e){
			System.out.println("\nBad input. Aborting drop");
		}
	}

	//equips a weapon, weapon will stay in inventory as well
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
	public Item getEquippedWeapon()
	{
		return equippedWeapon;
	}

	//returns equippedArmor
	public Item getEquippedArmor()
	{
		return equippedArmor;
	}

	//sets equippedWeapon without user input
	public void setEquippedWeapon(Item weapon)
	{
		equippedWeapon = weapon;
	}

	//sets equippedArmor without user input
	public void setEquippedArmor(Item armor)
	{
		equippedArmor = armor;
	}
}

