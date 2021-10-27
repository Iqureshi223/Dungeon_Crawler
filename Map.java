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

	private char[][] map = {
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

	public Map(){
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
			for(int i = 0; i < 10; i++){
                        	for(int j = 0; j < 10; j++){
					map[i][j] = DEFAULT_MAP[i][j];                        	
                	        }
			}
			
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

			if(runcount >= 5){
	                        runtime = false;
                        }

			runcount++;
			printMap();
		}		
	}
	
	public void combat(Entity player, Entity enemy, boolean isPlayer){
		//would prefer to see entity class before writing
	}

	public void createItem(Entity item){
		//this method creates an entity that is just an item, needs an entity to take an item from it's inventory
		//creates and adds an item entity to the arraylist
	}

	//this determines movement for entities
	public void movement(Entity entity){
		if(entity.getIsPlayer()){
			//determine based off input class
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
				combat(entities.get(playerIndex), entity, entity.getIsPlayer());
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
