//this is the itemgenerator which, when called upon creates a random object listed below using the Random function
import java.util.*;
public class ItemGenerator{

private static Random rng = new Random();
//this method when called upon creates items and then adds them to an arraylist. then randNum will call a number between 0 and 16, then it will find the element with same index number and return that object to inventory.
public static Item generate() {
	ArrayList<Item> list = new ArrayList<Item>();
	Item i1 = new Item(ItemType.Weapon, "Wooden Sword", 39, 50, 49);
	Item i2 = new Item(ItemType.Weapon, "Bronze Sword", 50, 79, 56);
	Item i3 = new Item(ItemType.Weapon, "Iron Sword", 90, 110, 78);
	Item i4 = new Item(ItemType.Weapon, "Silver sword", 125, 220, 130);
	Item i5 = new Item(ItemType.Weapon, "Golden Sword", 170, 550, 240);
	Item i6 = new Item(ItemType.Weapon, "Skeletal Arm", 25, 0, 5);
	Item i7 = new Item(ItemType.Armor, "Leather Armor", 25, 35, 15);
	Item i8 = new Item(ItemType.Armor, "Wooden Armor", 59, 50, 35); 
	Item i9 = new Item(ItemType.Armor, "Iron Armor", 110, 135, 45);
	Item i10 = new Item(ItemType.Armor, "Silver Armor", 150, 255, 160);
	Item i11 = new Item(ItemType.Armor, "Golden Armor", 220, 990, 360);
	Item i12 = new Item(ItemType.Armor, "Bone Armor", 35, 0, 10);
	Item i13 = new Item(ItemType.Other, "Flask of Liquified Oranges", 15, 35, 0);
	Item i14 = new Item(ItemType.Other, "Giant cheese Wheel made from Mammoth Milk", 100, 450, 0);
	Item i15 = new Item(ItemType.Other, "Potion of Poisoning", 5, 110, 0);
	Item i16 = new Item(ItemType.Other, "Potion of Healing", 5, 105, 0);

	list.add(i1);	
	list.add(i2);
	list.add(i3);
	list.add(i4);
	list.add(i5);
	list.add(i6);
	list.add(i7);
	list.add(i8);
	list.add(i9);
	list.add(i10);
	list.add(i11);
	list.add(i12);
	list.add(i13);
	list.add(i14);
	list.add(i15);
	list.add(i16);
	int randNum =rng.nextInt(16);
	return list.get(randNum);
}

}
