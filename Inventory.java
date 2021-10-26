//the inventory class involves adding things to the users inventory as well as letting them manipulate their inventory
import java.util.*;
public class Inventory{
	Scanner input = new Scanner(System.in); 
	ArrayList<Item> items = new ArrayList<Item>();
	private int maxWeight;
	private int totalWeight;
	private Item equippedWeapon;
	private Item equippedArmor;
	private int userInput;
//inventory object constructor
public Inventory(int maxWeight) {
	this.maxWeight = maxWeight;
	this.totalWeight = 0;
	this.userInput = 0;
}
//adds an item to the items list if only they are not making the totalWeight go over the restricted max weight
public boolean add(Item item) {
	if (((totalWeight()) + item.getWeight()) <= maxWeight) {
		items.add(item);
		this.totalWeight += item.getWeight();
		return true;
	}else {
		return false;
	}
}
//returns the totalWeight when called on
public int totalWeight() {
	return this.totalWeight;

}
//when called upon prints out the ArrayList
public void print() {
	System.out.println("Type:   Name:   Weight:   Value:   Strength: ");
	for(int i=0;i<items.size();i++) {
		System.out.println(items.get(i));
	}
}
//lets the user drop an item in case their inventory gets to full but they still want to add stuff
public void drop() {
	System.out.println("Drop an Item");
	for(Item i : items) {
		System.out.println(items.indexOf(i) + ": " + i);
	}
	System.out.println((items.size()) + " cancel");
	System.out.print(": ");
	userInput = input.nextInt();
	if(userInput<items.size()) {
		items.remove((userInput));
	}

}
//equip weapon method takes elements from the items arraylist and if they have the enum type Weapon adds them to the weapon arraylist and then asks the user if they want to equip a weapon or not
//and then stores it into a variable
public void equipWeapon() {
	ArrayList<Item> equipableWeap = new ArrayList<Item>();
	System.out.println("Equip a Weapon");
	for(Item i : items) {
		if(i.getType()==ItemType.WEAPON) {
			equipableWeap.add(i);
		}
	}
	for(Item ew : equipableWeap) {
		System.out.println(equipableWeap.indexOf(ew) + ": " + ew);
	}

	System.out.println((equipableWeap.size()) + " cancel");
	System.out.print(": ");
	userInput = input.nextInt();
	if(userInput < equipableWeap.size()) {
		equippedWeapon = equipableWeap.get((userInput));
	}
}
//similar thing as the equip weapon method just works with armor sets instead
public void equipArmor() {
	ArrayList<Item> equipableArm = new ArrayList<Item>();
	System.out.println("Equip an Armor Set");	
	for(Item a: items) {
		if(a.getType()==ItemType.ARMOR) {
			equipableArm.add(a);
		}	
	}
	for(Item ea: equipableArm) {
		System.out.println(equipableArm.indexOf(ea) + ": " + ea);
	}
	System.out.println((equipableArm.size()) + " cancel");
	System.out.print(": ");
	userInput = input.nextInt();
	if(userInput < equipableArm.size()) {
		equippedArmor = equipableArm.get((userInput));
	}
}


}
