//library created by the professor to allow inputs without hitting enter
import ansi_terminal.*;
public class Input {
//creates variables to use within the class as well as a Entity and key variable
private Entity entity;
private Key key;
private int movement = 0;
private boolean run = true;
//constructor that takes in an entity object as a parameter and initializes the entity and key variable and puts the terminal in a special view
public Input(Entity ent) {
this.entity = ent; 
Terminal.rawMode();
key = Terminal.getKey();
}
//returns the value stored into key
public Key getInput() {
	return key;
}
//returns an integer that the map class uses to move the characters as well as access openInventory and help method
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
		openInventory();
		movement = 5;
		break;
	case i:
		openInventory();
		movement = 5;
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
public void openInventory() {
//the while loop the runs while the user is operating the inventory menu. will not stop until the user chooses the option to quit
while(run) {
	System.out.println("------------------");
	System.out.println("W. Print inventory");
	System.out.println("Y. Drop item");
	System.out.println("Z. Equip Weapon");
	System.out.println("Q. Equip Armor");
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



}
//returns the terminal back to the default mode 
public void defaultTerminal() {
	Terminal.cookedMode();
}
}


