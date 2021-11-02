/* Bryan Potts
 *
 * CPSC 240
 * 10/25/2021
 * Dugneon Crawler Project
 * Map Class
 *
 * This class creates the initial map and holds any updates to the map,
 * it also stores the positions of entities on the map and runs a few
 * map related methods like combat, movement updates, and printing the
 * new map.
 */
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
public class Map{
	
	//create map
	private final char[][] DEFAULT_MAP = {
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '}
	};

	private char[][] map = new char[10][10] ;
	
	//map icon meanings:
	// "." is nothing, entities should reside on top of these spots and move over them
	// "W" is a wall, entities should NOT be able to pass over these spots
	// "P" is the player
	// "E" is an enemy
	// "$" is an item
	
	//MAKE SURE PLAYER SPAWNS INSIDE WALLED AREAS
	
	//create arraylist to hold entities
	private ArrayList<Entity> entities = new ArrayList<Entity>(); 
	
	//runs moveall method
	boolean runtime = true;
	int runcount = 0;

	//enemy control variable
	int numEnemy = 0;

	public Map(){
		//fill in map
		for(int i = 0; i < 10; i++){
                        for(int j = 0; j < 10; j++){
                        	map[i][j] = DEFAULT_MAP[i][j];
                	}
                }
		
		//create BAD entity
		createEntity(); //this entity is for debug purposes, it should NOT appear on the map at any time
		
		//setup player
		System.out.print("Please enter your name: ");
		Scanner input = new Scanner(System.in);
		String playerName = input.next();
		createPlayer(playerName);	
	}

	//print the map
	public void printMap(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	}

	//finds the player in the arraylist
	public Entity getPlayer(){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).getIsPlayer()){
				return entities.get(i);
			}
		}
	
		return entities.get(0); //this returns the BAD entity
	}
	
	//creates a player
	public void createPlayer(String name){
		Entity player = new Entity(name);
		player.setXCoor(5);
		player.setYCoor(5);
		map[player.getXCoor()][player.getYCoor()] = 'P';
		entities.add(player);
	}

	//creates a non-player entitiy
	public void createEntity(){
		Entity entity = new Entity();
		boolean createCheck = false;
		Random rand = new Random();
		while(!createCheck){
			int x = rand.nextInt(10);
			int y = rand.nextInt(10);
			if(map[x][y] == '.'){
				entity.setXCoor(x);
				entity.setYCoor(y);
				map[entity.getXCoor()][entity.getYCoor()] = 'E';
				createCheck = true;
			}
		}
		entities.add(entity);
	}
	
	//moves all entities
	public void moveAll(){
		while(runtime){		
			//clears the map
			for(int i = 0; i < 10; i++){
                        	for(int j = 0; j < 10; j++){
					map[i][j] = DEFAULT_MAP[i][j];                        	
                	        }
			}
			
			//moves all entities
			for(int i = 1; i < entities.size(); i++){
				movement(entities.get(i));
				int x = entities.get(i).getXCoor();
				int y = entities.get(i).getYCoor();
				if(entities.get(i).getIsPlayer()){
					map[x][y] = 'P';
				}
				else{
					map[x][y] = 'E';
				}

				
			}

			//test variable, remove in final
			if(runcount >= 5){
	                        runtime = false;
                        }

			runcount++;
			printMap();
		}		
	}
	
	public void combat(Entity attacker, Entity defender){
		Inventory AInv = attacker.getInventory();
		Inventory DInv = defender.getInventory();
		if(false){
			//change this to a check for item entities
		}
		else{
			//this runs normal combat, attacker should attack only.
		}
	}

	public void createItem(Entity item){
		//this method creates an entity that is just an item, needs an entity to take an item from it's inventory
		//creates and adds an item entity to the arraylist
	}

	//this determines movement for entities
	public void movement(Entity entity){
		if(entity.getIsPlayer()){
			//determine based off input class
			Input input = new Input();
			System.out.println(input.getMovement());

			//variables
			int direction = input.getMovement();
			int attemptX = entity.getXCoor();
			int attemptY = entity.getYCoor();
			boolean moveAllowed = false;
			boolean didCombat = false;
			boolean isOpen = false;


			//move based on action
			switch(direction){
				//left
				case 1:
					attemptX = attemptX - 1;
					break;
				//right
				case 2:
					attemptX = attemptX + 1;
					break;
				//up
				case 3:
					attemptY = attemptY - 1;
					break;
				//down
				case 4:
					attemptY = attemptY + 1;
					break;
				//not movement
				case 5:
					System.out.println("returned 5");
					break;
				//other/bug handling
				default:
					System.out.println("Error in player movement switch case.");
			}

			//check if spot is open
			if(map[attemptX][attemptY] == '.'){
				isOpen = true;
			}

			//perform combat
			if(map[attemptX][attemptY] == 'E' || map[attemptX][attemptY] == '$'){
				Entity defense = null;
				for(int i = 2; i < entities.size(); i++){
					if(attemptX == entities.get(i).getXCoor()){
						if(attemptY == entities.get(i).getYCoor()){
							defense = entities.get(i);
						}
					}
				}

				if(defense == null){
					defense = entities.get(0); //BAD entity, prevents error, don't remove
				}

				combat(entity, defense);
				didCombat = true;
			}

			//check movement
			if(!didCombat && isOpen){
				moveAllowed = true;
			}

			//commit movement
			if(moveAllowed){
				entity.setXCoor(attemptX);
				entity.setYCoor(attemptY);
			}

		}
		/*
		else if(entity.getIsItem()){
			// item should not move
		}
		*/
		else{
			//general ai movement
			boolean moveAllowed = false;
			int playerX = 0;
			int playerY = 0;
			int playerIndex = 1;

                        for(int i = 0; i < entities.size(); i++){
                                if(entities.get(i).getIsPlayer()){
					playerX = entities.get(i).getXCoor();
                                	playerY = entities.get(i).getYCoor();
					playerIndex = i;
				}
			}
			
			boolean hasNotMoved = true;
			int attemptX = entity.getXCoor();
			int attemptY = entity.getYCoor();

			//caclulate new x coor
			if(entity.getXCoor() < playerX){
			 	attemptX = entity.getXCoor() + 1;
				hasNotMoved = false;
			}
			else if(entity.getXCoor() > playerX){
                                attemptX = entity.getXCoor() - 1;
				hasNotMoved = false;
                        }
			
			//calculate new y coor
			if((entity.getYCoor() > playerY) && hasNotMoved){
                                attemptY = entity.getYCoor() - 1;
                        }
			else if((entity.getYCoor() < playerY) && hasNotMoved){
                                attemptY = entity.getYCoor() + 1;
                        }

			//calculate if this causes combat
			boolean didCombat = false;

			if((attemptX == playerX) && (attemptY == playerY)){
				combat(entity, entities.get(playerIndex));
				didCombat = true;
			}
			
			//check for walls
			boolean hasWall = false;

			if(map[attemptX][attemptY] == 'W'){
				hasWall = true;
			}
			
			//checks if the spot is open
			boolean isOpen = false;

			if(map[attemptX][attemptY] == '.'){
				isOpen = true;
			}

			//check for all other conditions
			if(!didCombat && !hasWall && isOpen){
				moveAllowed = true;
			}

			//commit movement changes
			if(moveAllowed){
				entity.setXCoor(attemptX);
				entity.setYCoor(attemptY);
			}
		}
	}
}
