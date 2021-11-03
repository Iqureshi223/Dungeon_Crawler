/* This class only exists to test the other classes.
 * Not intended for final version.
 */
public class MainTest{
	
	public static void main(String[] args){
		
		//create and run a map object
		Map map = new Map();

		//map.printMap();
		//map.createEntity();
		//map.createEntity();
		System.out.println("use the arrow keys to move, and hit I to access a game menu to see your inventory as well as quit the game");
		map.moveAll();
	}
}

