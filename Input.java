import ansi_terminal.*;
public class Input {
private Entity entity;
private Key key;
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

public void openInventory() {
	
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
public void help() {
	System.out.println("Your objective in this game is to explore the dungeon and kill all remaining foes, find and use items you find to your advantage. you can equip swords and armors to increase your attributes. Use on screen commands to move your character and preform certain actions");
}

public void defaultTerminal() {
	Terminal.cookedMode();
}
}


