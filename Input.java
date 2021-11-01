import ansi_terminal.*;
public class Input {
private  Key key = Terminal.getKey();
private int movement = 0;
private boolean run = true;
public Input() {

}
public Key getInput() {
	return key;
}

public int getMovement() {
switch(key) {
	case LEFT:
		movement = 1;
	case RIGHT:
		movement = 2;
	case UP:
		movement = 3;
	case DOWN:
		movement = 4;
}
return movement;
}

public void openInventory(Entity entity) {
while(run) {
	
	System.out.println("------------------");
	System.out.println("W. Print inventory");
	System.out.println("X. Add random item");
	System.out.println("Y. Drop item");
	System.out.println("Z. Equip Weapon");
	System.out.println("Q. Equip Armor");
	System.out.println("R. Exit");

	System.out.println("\n: ");
	
	switch(key){
		case W:
			entity.getInventory().print();
			break;

}	
}
}
public void help() {
	System.out.println("Your objective in this game is to explore the dungeon and kill all remaining foes, find and use items you find to your advantage. you can equip swords and armors to increase your attributes. Use on screen commands to move your character and preform certain actions");
}

}


