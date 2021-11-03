import java.util.Scanner;

public class MainCrawler
{
	public static void main (String [] args)
	{
		Scanner in = new Scanner(System.in);
		Map map = new Map();

		System.out.println("Welcome to Dungeon Crawlers");
		System.out.println("You have entered the dungeon and must slay all the enemies to escape");
		System.out.println("You may run into some weapons and armor along the way to help you on your journey");
		System.out.println("Here are some starting materials to help you survive");
		System.out.println("Have fun and remember: slay and don't get slayed!");

		map.moveAll();
		System.out.println("Congratualtions! You have won the game!");
	}
}
