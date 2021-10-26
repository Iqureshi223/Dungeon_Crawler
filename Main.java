/*Bryan Potts
 * CPSC 240
 * 9/26/2021
 * Inventory Assignment
 */

// this is the main class that runs the program.

import java.util.Scanner;
public class Main{

	// this is the main method
	public static void main(String[] args){
		Inventory inventory = new Inventory(100);
		boolean run = true;

		//this loop keeps the program running until the user selects "Exit"
		while(run){
			
			//the options
			System.out.println("------------------");
			System.out.println("1. Print inventory");
			System.out.println("2. Add random item");
			System.out.println("3. Drop item");
			System.out.println("4. Equip Weapon");
			System.out.println("5. Equip Armor");
			System.out.println("6. Exit");
			
			//the input
			System.out.print("\n: ");
			Scanner in = new Scanner(System.in);
			int choice = 0;
			boolean errorState = false;

			try{
				choice = in.nextInt();
			}
			catch(Exception e){
				System.out.println("\nBad input, please try again");
				errorState = true;
			}

			//perform the appropriate action for the choice made
			switch(choice){
				case 1:
					inventory.print();
					break;
				case 2:
					boolean didAdd = inventory.add(ItemGenerator.generate());
					
					if(!didAdd){
						System.out.println("\nYou can't carry anymore!");
					}

					break;
				case 3:
					inventory.drop();
					break;
				case 4:
					inventory.equipWeapon();
					break;
				case 5:
					inventory.equipArmor();
					break;
				case 6:
					run = false;
					break;
				default:
					if(!errorState){
						System.out.println("\nThat is not a valid selection, please try again.");
					}
			}

			//this helps to make text more readable
			System.out.println();
			
		}			
			
	}

}
