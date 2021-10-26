//This is the Item class, this class takes in parameters pass through an object and uses it to create object to store in the heap
public class Item {
	ItemType type;
	String name;
	int weight;
	int value;
	int strength;
//this is the constructor for the class, creates the object.
public Item(ItemType type, String name, int weight, int value, int strength) {
	this.type = type;
	this.name = name;
	this.weight = weight;
	this.value = value;
	this.strength = strength;	

 }
//this method returns the value of the weight variable
public int getWeight() {
	return this.weight;
}
//this method returns the value of the value variable, which means the price of the object
public int getValue() {
	return this.value;
}
//this method returns the name variable
public String getName() {
	return this.name;
}
//this method returns the enum type of the object
public ItemType getType() {
	return this.type;
}
//this prints out the item object when system.out.println is called on the object. it makes sure to print out the variables in the given format below
public String toString() {
return this.type + " " +  this.name + " " + this.weight + " " +  this.value + " " + this.strength;


}
}
