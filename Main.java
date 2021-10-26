//the Main program, executes the program and has the user enter inputs to select what they want to do
import java.util.*;
public class Main {

public static void main (String args[]) { 
	Scanner input = new Scanner(System.in);
	Inventory invi = new Inventory(475);
	int userInput = 0;
	System.out.println("welcome to teting!");
	do{
		System.out.println("1.Print inventory\n2.add an item\n3.remove an item\n4.equip a weapon\n5.equip an armor set\n6.exit");
		System.out.println(": ");
		userInput = input.nextInt();
		if(userInput == 1) {
			invi.print();
		}else if(userInput == 2) {
			if(invi.add(ItemGenerator.generate())) {
				System.out.println("Added");
			}else{
			System.out.println("You feel like your carrying a mammoth, remove something. this is your total weight: " + invi.totalWeight());
			}
		}else if(userInput == 3) {
			invi.drop();
		}else if(userInput == 4) {
			invi.equipWeapon();
		}else if(userInput == 5) {
			invi.equipArmor();
		}
	}while(userInput != 6);
}
}
