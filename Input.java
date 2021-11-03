import ansi_terminal.*;
public class Input {
private  Key key;
private Entity entity;
private int movement = 0;
private boolean run = true;
public Input(Entity ent) {
this.entity = ent; 
Terminal.rawMode();
 key = Terminal.getKey();
}
public Key getInput() {
	return key;
}

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
		break;
	default:
		movement = 5;
		break;
}
return movement;
}

public void openInventory() {

while(run) {
	
	System.out.println("------------------");
	System.out.println("W. Print inventory");
	System.out.println("X. Add random item");
	System.out.println("Y. Drop item");
	System.out.println("Z. Equip Weapon");
	System.out.println("Q. Equip Armor");
	System.out.println("R. Exit");
	System.out.println("ESCAPE. quit the game");

	System.out.println("\n: ");

	switch(key){
		case W: 
			entity.getInventory().print();
			break;
		case Y:
			entity.getInventory().drop();
			break;
		case Z:
			entity.getInventory().equipWeapon();
			break;
		case Q:
			entity.getInventory().equipArmor();
			break;
		case R:
			run = false;
			break;
		case ESCAPE:
			System.out.println("exiting the game");
			System.exit(0);
			break;
		default:
			System.out.println("\nThe is not a valid selection, please try again.");
			break;
}
}
}
public void help() {
	System.out.println("Your objective in this game is to explore the dungeon and kill all remaining foes, find and use items you find to your advantage. you can equip swords and armors to increase your attributes. Use on screen commands to move your character and preform certain actions");
}

public void defaultTerminal() {
	Terminal.cookedMode();
}
}


