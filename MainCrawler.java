public class MainCrawler
{
	/** 
	 * @author Cooper Fulton
	 * This is the main class that runs the game
	 * A map object is created and then the instructions to the game are displayed
	 */
	public static void main (String [] args)
	{
		//creates a map which runs the game and creates a setting and plot for the game
		Map map = new Map();

		System.out.println("Welcome to Dungeon Crawlers");
		System.out.println("You have entered the dungeon and must slay all the enemies to escape");
		System.out.println("You may run into some weapons and armor along the way to help you on your journey");
		System.out.println("Here are some starting materials to help you survive");
		System.out.println("Have fun and remember: slay and don't get slayed!");
		//commands for the player to follow
		System.out.println("Here are the instructions for the game");
		System.out.println("Arrow Keys to move");
		System.out.println("I: access menus to modify inventory and game settings");
		System.out.println("H: see game instructions");
		System.out.println("W: see what is stored in your inventory");
		System.out.println("Y: drop an item in your inventory");
		System.out.println("Z: equip a weapon");
		System.out.println("Q: equip an Armor set");
		System.out.println("C: eat a Consumable");
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



		map.moveAll();
		System.out.println("Congratualtions! You have won the game!");
	}
}
