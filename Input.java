import ansi_terminal.*;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.io.FileReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
/**
*the input class sets the terminal into raw mode to allow ani_terminal to
*work then allows user key inputs without hitting enter also allows the
*user to manage their inventory, equip equipment, save and load their game
*and finally either exit the menu to get back to the game or exit the game
*entirely.
*@author Imaad Qureshi
*/
public class Input {
//creates variables to use within the class as well as a Entity and key variable
private Entity entity;
private Key key;
private int movement = 0;
private boolean run = true;
private int newHealth = 0;
ArrayList<Item> Items = new ArrayList<Item>();
ArrayList<Entity> entities;
int floorNumber = 0;
FileReader reader;
Scanner s; 
/**
*blank constructor that initializes an Input object to allow usage of the
*input class without going into terminal.rawmode().
*/
public Input(){


}
/**
*a constructor that takes an entity object as a parameter, then saves that
*into a entity variable, then it it calls on Terminal.rawMode() then
*creates a key variable to let user start hitting input keys without having
*to hit enter.
*@param ent the entity object that needs to be passed in order to be stored.
*/
public Input(Entity ent) {
this.entity = ent;
Terminal.rawMode();
key = Terminal.getKey();
}
//returns the value stored into key
/**
*output the value of the key input the user pressed when called upon.
*@return the key the user pressed that is stored in the key variable.
*/
public Key getInput() {
	return key;
}
//returns an integer that the map class uses to move the characters as well as access openInventory and help method
/**
*the method uses switch cases and checks the input the user inputted, then
*it sets movement to an integer it keeps track of what the user is hitting
*to move and if they want to access the menu options or look up the
*instructions again
*@return the movement variable with a value when called upon
*/
public int getMovement() {
switch(key) {
	case LEFT:
		movement = 1;
		break;	
	case RIGHT:
		movement = 2;
		break;
	case UP:
		movement = 3;
		break;
	case DOWN:
		movement = 4;
		break;
	case I:
		movement = 5;
		openInventory();
		break;
	case i:
		movement = 5;
		openInventory();
		break;
	case H:
		help();
		movement = 5;
		break;
	case h:
		help();
		movement = 5;
		break;
	default:
		movement = 5;
}
return movement;
}
//using the entity variable allows user to access the methods within Inventory to move around and equip their items. it also allows them to quit both the menu and the game
/**
*brings up the menu and options for the user to use it will either print out
*the inventory, drop an item from the inventory, equip an armor or sword,
*save and load the game, exit the menu, and quit out the game.
*/
public void openInventory() {
//the while loop the runs while the user is operating the inventory menu. will not stop until the user chooses the option to quit
while(run) {
	System.out.println("------------------");
	System.out.println("W. Print inventory");
	System.out.println("Y. Drop item");
	System.out.println("Z. Equip Weapon");
	System.out.println("Q. Equip Armor");
	System.out.println("C. Consume an Item");
	System.out.println("S. Save Game (EFFECT DOESN'T TAKE PLACE UNTIL EXITING MENU)");
	System.out.println("L. Load Game (EFFECT DOESN'T TAKE PLACE UNTIL EXITING MENU)");
	System.out.println("R. Exit");
	System.out.println("ESCAPE. quit the game");

	System.out.print("\n: ");


	Terminal.rawMode();
	key = Terminal.getKey();
	Terminal.cookedMode();
	switch(key){
		case W: 
			entity.getInventory().print();
			break;
		case w:
			entity.getInventory().print();
			break;

		case Y:
			entity.getInventory().drop();
			break;
		case y:
			entity.getInventory().drop();
			break;
		case Z:
			entity.getInventory().equipWeapon();
			break;
		case z:
			entity.getInventory().equipWeapon();
			break;
		case Q:
			entity.getInventory().equipArmor();
			break;
		case q:
			entity.getInventory().equipArmor();
			break;
		case C:
			System.out.println("your current health is " + entity.getHealth());
			entity.setHealth(entity.getInventory().eatConsumables());
			System.out.println("Your new health is " + entity.getHealth());
		case c:
			System.out.println("Your current health is " + entity.getHealth());
			entity.setHealth(entity.getInventory().eatConsumables());
			System.out.println("Your new health is " + entity.getHealth());
		case S:
			movement = 6;
			break;
		case s:
			movement = 6;
			break;
		case L:
			movement = 7;
			break;
		case l:
			movement = 7;
			break;
		case R:
			run = false;
			break;
		case r:
			run = false;
			break;

		case ESCAPE:
			System.out.println("exiting the game");
			System.exit(0);
			break;
		default:
			System.out.println("invalid input, please enter a valid input");
}
}
}
//displays instructions on how to play the game
/**
*prints out instructions on how to play the game.
*/
public void help() {
	System.out.println("Arrow Keys to move");
	System.out.println("I: access menus to modify inventory and game settings");
	System.out.println("H: see game instructions");
	System.out.println("W: see what is stored in your inventory");
	System.out.println("Y: drop an item in your inventory");
	System.out.println("Z: equip a weapon");
	System.out.println("Q: equip an Armor set");
	System.out.println("R: exit the game menu");
	System.out.println("ESCAPE: exit the game");
	System.out.println(".: an open spot the character can move on the map");
	System.out.println("W: a wall a player can't move past");
	System.out.println("P: is the player's icon");
	System.out.println("A: is an aggressive enemy that hunts the player down");
	System.out.println("D: is a passive enemy that roams the dungeon");
	System.out.println("$: is an item for the player to pick up");
	System.out.println("Z: is a staircase to advance to the next floor");
	System.out.println("Enter: to continue and confirm inventory choices");
	System.out.println("Z: staircase to advance to next floor");
	System.out.println("S: Save your current game");
	System.out.println("L: Load a previously saved game");



}
//returns the terminal back to the default mode 
/**
*returns the terminal back into the default mode.
*/
public void defaultTerminal() {
	Terminal.cookedMode();
}
//Transform text files into rooms for the players to enter to go the next floor
/**
*it takes an integer parameter to specify which map text file to use
*reads in text files that layouts for the rooms, it splits each element into
*the text file by a comma then sets that inside a string array then adds the
*current elements into the room ArrayList before reinitializing the array
*for the next line then returns the room arraylist.
*@param roomNumber takes a roomNumber to specify which of 
*the map files to use.
*@return the String ArrayList containing an ArrayList of new strings.
*@throws FileNotFoundException if it can't find any of the map files specify.
*@throws IOException when the file writer can preform a certain task anymore.
*/
public ArrayList<String> getRoom(int roomNumber) {
	ArrayList<String> room = new ArrayList<String>();
	Scanner s;
	
	try{
		 reader = new FileReader("map" + roomNumber +".txt");
	
		 s = new Scanner(reader);
		while(s.hasNextLine()) {
			String[] lines = s.nextLine().split(",");
			for(String a: lines) {	
				room.add(a);
			}
		}
		s.close();
		reader.close();
	}catch(FileNotFoundException e) {
		System.out.println("File not Found!");
	}catch(IOException ex) {
		ex.printStackTrace();
	}

 	return room;
	}
//saves the current floor the player is on and all the entities that exist on the floor
/**
*takes in a arraylist type entity parameter and a integer as a parameter as well saves what is stored in the ArrayList and the integer variable to a text file.
*@throws FileNotFoundException throws if it cannot find the file to save to.
*/
public void save(ArrayList<Entity> entityList, int floor) {
	this.entities = entityList;
	this.floorNumber = floor;
	try{
		PrintWriter pw = new PrintWriter(new File("Entities.txt"));
		for(Entity ent: entities) {
				pw.println(ent.getIsPlayer());
				pw.println(ent.getIsItem());
				pw.println(ent.getIsStairs());
				pw.println(ent.getAIMovement());
				pw.println(ent.getHealth());
				pw.println(ent.getName());
				pw.println(ent.getXCoor());
				pw.println(ent.getYCoor());
				Items = ent.getInventory().getItems();
				for(int j = 0; j < Items.size(); j++) {
					Item tempItem = Items.get(j);
					pw.println(tempItem.getWeight());
					pw.println(tempItem.getValue());
					pw.println(tempItem.getName());
					pw.println(tempItem.getType());
					pw.println(tempItem.getStrength());
				}
				pw.println(".");
		}
		pw.close();
	}catch (FileNotFoundException e) {
		System.out.println("File not found! failed to save");
	}
	try{
		PrintWriter pw = new PrintWriter(new File("floor.txt"));
		pw.println(floorNumber);
		pw.close();
	}catch (FileNotFoundException e) {
		System.out.println("File not found! failed to save");
	}
}
//loads all the entities on the floor
/**
*creates a series of entity objects and their item objects from the save file and store them into an ArrayList then return that arraylist
*@return the entities Arraylist that contains a bunch of entity objects and their item objects.
*@throws FileNotFoundException throws an exception if it can't find a file to load from.
*@throws IOException throws an exception when the file reader can't perform any actions due to a conflict.
*/
public ArrayList<Entity> loadEntity() {
	entities = new ArrayList<Entity>();
	try{
		reader = new FileReader("Entities.txt");
		s = new Scanner(reader);
		while(s.hasNextLine()) {
			boolean run = true;
			String personType = s.nextLine();
			String itemType = s.nextLine();
			String isStairs = s.nextLine();
			String AIMovement = s.nextLine();
			String Health = s.nextLine();
			String Name = s.nextLine();
			String XCoor = s.nextLine();
			String YCoor = s.nextLine();
			if(Boolean.parseBoolean(personType) == true) {
				Player player = new Player(" ");
				entity = player;
			}else if(Boolean.parseBoolean(itemType) == true) {
				ItemEntity item = new ItemEntity();
				item.setIsStairs(Boolean.parseBoolean(isStairs));
				entity = item;
			}else{
				Enemy enemy  = new Enemy();
				enemy.setAIMovement(Boolean.parseBoolean(AIMovement));
				entity = enemy;
			}
			entity.setHealth(Integer.parseInt(Health));
			entity.setName(Name);
			entity.setXCoor(Integer.parseInt(XCoor));
			entity.setYCoor(Integer.parseInt(YCoor));
			String test = s.nextLine();
			if(test.equals(".")) {
				run = false;
			}
			int i = 0;
			while(run) {
				String lbs = test;
				String networth = s.nextLine();
				String itemName = s.nextLine();
				String enumType = s.nextLine();
				String muscle = s.nextLine();

				int weight = Integer.parseInt(lbs);
				int value = Integer.parseInt(networth);
				ItemType type = ItemType.valueOf(enumType);
				int strength = Integer.parseInt(muscle);
				
				entity.getInventory().add(new Item(type, itemName, weight, value, strength));
				test = s.nextLine();
				if(test.equals(".")) {
					run = false;
				}
			}
			entities.add(entity);	

		}
		s.close();
		reader.close();
	}catch (FileNotFoundException e){
		System.out.println("File not found! failed to load");
	}catch(IOException ex) {
		ex.printStackTrace();
	}
	return entities;	

}
//loads up the floor the player saved
/**
*loads up the floor text file and saves the value to an integer then returns that integer.
*@return the value of the variable.
*@throws FileNotFoundException throws if it can't find floors.txt.
*@throws IOException if file reader can't perform due to a confliction
*/
public int loadFloor() {
	try{
		 reader = new FileReader("floor.txt");
		 s = new Scanner(reader);
		while(s.hasNextLine()) {
			String number = s.nextLine();
			floorNumber = Integer.parseInt(number);
		}
		s.close();
		reader.close();
	}catch (FileNotFoundException e) {
		System.out.println("File not found! failed to load");
	}catch(IOException ex) {
		ex.printStackTrace();
	}
	return floorNumber;
}
}
