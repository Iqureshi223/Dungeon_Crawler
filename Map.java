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
                        {' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', 'W', 'W', 'W', 'W', 'W', 'W', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', 'W', ' ', ' ', ' ', ' ', 'W', '.', '.', '.', '.', '.', 'W', ' '},
                        {' ', 'W', '.', '.', '.', '.', '.', 'W', ' ', ' ', ' ', ' ', 'W', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', 'W', ' ', ' ', ' ', ' ', 'W', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', 'W', ' ', ' ', ' ', ' ', 'W', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', 'W', 'W', 'W', 'W', 'W', 'W', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', '.', 'W', ' '},
			{' ', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', 'W', ' '},
			{' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' '},
	};

	final int MAP_SIZE_X= 20;
	final int MAP_SIZE_Y = 20;
	private char[][] map = new char[MAP_SIZE_X][MAP_SIZE_Y];
	private char[][] importMap;	
	
	int currentFloor = 1;

	//map icon meanings:
	// "." is nothing, entities should reside on top of these spots and move over them
	// "W" is a wall, entities should NOT be able to pass over these spots
	// "P" is the player
	// "A" is a smart enemy (agressive)
	// "D" is a dumb enemy (dumb)
	// "$" is an item
	// "Z" is a staircase to advance to next floor
	
	//MAKE SURE PLAYER SPAWNS INSIDE WALLED AREAS
	
	//create arraylist to hold entities
	private ArrayList<Entity> entities = new ArrayList<Entity>(); 
	
	//runs moveall method
	boolean runtime = true;
	int runcount = 0;

	//enemy control variable
	int numEnemy = 0;

	public Map(){
		boolean load = askLoad();
		if(load){
			loadMap();
			loadEntities();
		}
		//false condition, creates a new game
		else{
			//import a map from files
			ImportMap();

			//fill in map
			for(int i = 0; i < MAP_SIZE_X; i++){
                        	for(int j = 0; j < MAP_SIZE_Y; j++){
                        		map[i][j] = importMap[i][j];
                		}
                	}
		
			//create BAD entity
			createEntity(); //this entity is for debug purposes, it should NOT appear on the map at any time
			moveBAD();
		
			//setup player
			System.out.print("Please enter your name: ");
			Scanner input = new Scanner(System.in);
			String playerName = input.next();
			createPlayer(playerName);

			//create non player items and entities
			populate();

			//setup map
			firstMap();
		}
	}

	//check if player wants to load the save file
	public boolean askLoad(){
		System.out.print("Would you like to load the save file? (Y/N): ");
		Scanner input = new Scanner(System.in);
		String inString = input.next();
		boolean result = false;

                //determine what kind of input
                switch(inString){
                	case "Y":
                        	result = true;
                                break;
                        case "YES":
                              	result = true;
                                break;
                        case "y":
                                result = true;
                                break;
                        case "yes":
                                result = true;
                                break;
                        case "N":
                                break;
                        case "NO":
                                break;
                        case "n":
                                break;
                        case "no":
                                break;
                        default:
                                System.out.println("Bad input. Save file not loaded.");
		}

		return result;		
	}
	
	//populate the floor
	public void populate(){
		createItem();
                createItem();
                createEntity();
                createEntity();
                createEntity();
                createEntity();
		createStairs();
	}

	//depopulates the floor
	public void depopulate(){
		while(entities.size() > 2){
			for(int i = 1; i < entities.size(); i++){
				if(!entities.get(i).getIsPlayer()){
					entities.remove(i);
				}
			}
		}
	}

	//moves the player between floors
	public void movePlayer(){
		Entity entity = getPlayer();
		boolean createCheck = false;
		Random rand = new Random();
		while(!createCheck){
                        int x = rand.nextInt(MAP_SIZE_X);
                        int y = rand.nextInt(MAP_SIZE_Y);
                        if(map[x][y] == '.'){
                                entity.setXCoor(x);
                                entity.setYCoor(y);
                                map[entity.getXCoor()][entity.getYCoor()] = 'P';
                                createCheck = true;
                       }
                }

	}
	
	//print the map
	public void printMap(){
		for(int i = 0; i < MAP_SIZE_X; i++){
			for(int j = 0; j < MAP_SIZE_Y; j++){
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
		Player player = new Player(name);
		player.setXCoor(5);
		player.setYCoor(5);
		map[player.getXCoor()][player.getYCoor()] = 'P';
		player.setHealth(50);
		entities.add(player);
		startItems(player);
	}

	//give entity starting items
	public void startItems(Entity player){
		boolean setup = false;
		int itemNum = 0;
		ItemGenerator itemGen = new ItemGenerator();
		while(!setup){
			Item item = itemGen.generate();
			if(itemNum == 0){
				if(item.getType() == ItemType.WEAPON){
					player.getInventory().add(item);
					player.getInventory().setEquippedWeapon(item); //debug line REMOVE
					itemNum++;
				}
			}
			else if(itemNum == 1){
				if(item.getType() == ItemType.ARMOR){
					player.getInventory().add(item);
					player.getInventory().setEquippedArmor(item); //debug line REMOVE
					itemNum++;
				}
			}
			else if(itemNum == 2){
				setup = true;
			}
		}
	}

	//creates a non-player entitiy
	public void createEntity(){
		Enemy entity = new Enemy();
		boolean createCheck = false;
		Random rand = new Random();
		int AiNum = rand.nextInt(2);
		if(AiNum == 1){
			entity.setAIMovement(true);
			entity.setName("Living Armor");
			entity.setHealth(25);
		}
		else{
			entity.setName("Dire Wolf");
		}
		while(!createCheck){
			int x = rand.nextInt(MAP_SIZE_X);
			int y = rand.nextInt(MAP_SIZE_Y);
			if(map[x][y] == '.'){
				entity.setXCoor(x);
				entity.setYCoor(y);
				if(entity.getAIMovement()){
					//map[entity.getXCoor()][entity.getYCoor()] = 'A';
				}
				else{
					//map[entity.getXCoor()][entity.getYCoor()] = 'D';
				}
				createCheck = true;
			}
		}
		entity.setIsItem(false);
		startItems(entity);
		entities.add(entity);
	}

	//place BAD entity outside map
	public void moveBAD(){
		Entity bad = entities.get(0);
		bad.setXCoor(0);
		bad.setYCoor(0);
		bad.setName("I AM ERROR");
	}

	//first time map setup
	public void firstMap(){
		//clears the map
                for(int i = 0; i < MAP_SIZE_X; i++){
                	for(int j = 0; j < MAP_SIZE_Y; j++){
                        	map[i][j] = importMap[i][j];
                        }
                }

                //moves all entities
                for(int i = 1; i < entities.size(); i++){
                	int x = entities.get(i).getXCoor();
                        int y = entities.get(i).getYCoor();
                        if(entities.get(i).getIsPlayer()){
                        	map[x][y] = 'P';
                        }
                        else if(entities.get(i).getIsItem()){
                        	if(entities.get(i).getIsStairs()){
					map[x][y] = 'Z';
				}
				else{
					map[x][y] = '$';
				}
                       	}
                        else{
                        	if(entities.get(i).getAIMovement()){
                                        map[x][y] = 'A';
                                }
                                else{
                                        map[x][y] = 'D';
                                }
                       	}
               	}

		//print the map
		printMap();
	}
	

	//moves all entities
	public void moveAll(){
		while(runtime){		
			//clears the map
			for(int i = 0; i < MAP_SIZE_X; i++){
                        	for(int j = 0; j < MAP_SIZE_Y; j++){
					map[i][j] = importMap[i][j];                        	
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
				else if(entities.get(i).getIsItem()){
					if(entities.get(i).getIsStairs()){
						map[x][y] = 'Z';
					}
					else{
						map[x][y] = '$';
					}
				}
				else{
					if(entities.get(i).getAIMovement()){
						map[x][y] = 'A';
					}
					else{
						map[x][y] = 'D';
					}
				}				
			}

			//restore 2 health to the player every turn
			if(getPlayer().getHealth() < 100){
				getPlayer().setHealth(2);
				if(getPlayer().getHealth() == 101){
					getPlayer().setHealth(-1);
				}
			}

			//win condition, no enemies remaining
			int enemyCount = entities.size();
			for(int i = 0; i < entities.size(); i++){
				if(i == 0){
					enemyCount--;
				}
				else if(entities.get(i).getIsPlayer()){
					enemyCount--;
				}
				else if(entities.get(i).getIsItem()){
					enemyCount--;
				}
			}

			if(enemyCount <= 0){
				runtime = false;
			}

			/*
			//test variable, remove in final
			if(runcount >= 100){
	                        runtime = false;
                        }

			runcount++;
			*/
			printMap();
		}		
	}
	
	public void combat(Entity attacker, Entity defender){
		Inventory AInv = attacker.getInventory();
		Inventory DInv = defender.getInventory();
		if(attacker.getIsPlayer() && defender.getIsItem() && defender.getIsStairs()){
			//this should allow the player to move up to the next floor
			System.out.print("Would you like to advance to the next floor? (Y/N): ");
			Scanner input = new Scanner(System.in);
                        String inString = input.next();

                        //determine what kind of input
                        switch(inString){
                                case "Y":
                                        currentFloor++;
                        		if(currentFloor > 3){
                                		currentFloor = 1;
                        		}
                        		changeFloors();
					break;
                                case "YES":
                                        currentFloor++;
                        		if(currentFloor > 3){
                                		currentFloor = 1;
                        		}
                        		changeFloors();
                                        break;
                                case "y":
                                        currentFloor++;
                        		if(currentFloor > 3){
                                		currentFloor = 1;
                        		}
                        		changeFloors();
                                        break;
                                case "yes":
                                        currentFloor++;
                        		if(currentFloor > 3){
                                		currentFloor = 1;
                        		}
                        		changeFloors();
                                        break;
                                case "N":
                                        break;
                                case "NO":
                                        break;
                                case "n":
                                        break;
                                case "no":
                                        break;
                                default:
                                        System.out.println("Bad input. Floor not changed.");
                        }
		}
		else if(attacker.getIsPlayer() && defender.getIsItem() && !defender.getIsStairs()){
			//this should activate the prompt for picking up items
			ArrayList<Item> items = DInv.getItems();
			Item item = items.get(0);
			System.out.print("Would you like to pick up a(n) " + item.getName() + "? (Y/N): ");
			Scanner input = new Scanner(System.in);
			String inString = input.next();
			
			//determine what kind of input
			switch(inString){
				case "Y":
					AInv.add(item);
                                        for(int i = 0; i < entities.size(); i++){
                                                if(entities.get(i) == defender){
                                                        entities.remove(i);
                                                }
                                        }
                                        break;
				case "YES":
					AInv.add(item);
                                        for(int i = 0; i < entities.size(); i++){
                                                if(entities.get(i) == defender){
                                                        entities.remove(i);
                                                }
                                        }
                                        break;
				case "y":
					AInv.add(item);
                                        for(int i = 0; i < entities.size(); i++){
                                                if(entities.get(i) == defender){
                                                        entities.remove(i);
                                                }
                                        }
                                        break;
				case "yes":
					AInv.add(item);
					for(int i = 0; i < entities.size(); i++){
						if(entities.get(i) == defender){
							entities.remove(i);
						}
					}
					break;
				case "N":
					break;
				case "NO":
					break;
				case "n":
					break;
				case "no":
					break;
				default:
					System.out.println("Bad input. Item was not picked up.");	
			}			
		}
		else if(attacker.getIsPlayer() && defender.getIsPlayer()){
			//nothing should happen here since this is the case where the player would attack themselves
		}
		else{
			//this runs normal combat, attacker should attack only.
			int attack;
			if(AInv.getEquippedWeapon() != null){
				attack = AInv.getEquippedWeapon().getStrength();
			}
			else {
				attack = 1;
			}
			int defense;
			if(DInv.getEquippedArmor() != null){
				defense = DInv.getEquippedArmor().getStrength();
			}
			else{
				defense = 0;
			}
			
			int damage = attack - defense + 15;
			if(damage < 1){
				damage = 1;
			}

			defender.setHealth(0 - damage);

			//print combat results
			System.out.println(attacker.getName() + " attacks " + defender.getName() + " for " + damage + " damage.");
			if(defender.getIsPlayer()){
				System.out.println("Player health: " + defender.getHealth());
			}

			//check if defender is dead
			int health = defender.getHealth();
			if(health <= 0){
				System.out.println(defender.getName() + " has been defeated.");

				//lose condition
				if(defender.getIsPlayer()){
					System.out.println("\nGAME OVER\n");
					System.exit(0);
				}

				//defender is dead and is not player
				for(int i = 0; i < entities.size(); i++){
					if(entities.get(i) == defender){
						entities.remove(i);
					}
				}

				createItem();
			}
		}
	}

	public void createItem(){
		//this method creates an entity that is just an item, needs an entity to take an item from it's inventory
		//creates and adds an item entity
		ItemEntity entity = new ItemEntity();
                boolean createCheck = false;
                Random rand = new Random();
                while(!createCheck){
                        int x = rand.nextInt(MAP_SIZE_X);
                        int y = rand.nextInt(MAP_SIZE_Y);
                        if(map[x][y] == '.'){
                                entity.setXCoor(x);
                                entity.setYCoor(y);
                                //map[entity.getXCoor()][entity.getYCoor()] = '$';
                                createCheck = true;
                        }
                }
                entity.setIsItem(true);
		
		//add one item to it's inventory
		Inventory entityInv = entity.getInventory();
		ItemGenerator genItem = new ItemGenerator();
		entityInv.add(genItem.generate());

		//add to arraylist
                entities.add(entity);
	}

	//this method creates the stairs
	public void createStairs(){
                ItemEntity entity = new ItemEntity();
                boolean createCheck = false;
                Random rand = new Random();
                while(!createCheck){
                        int x = rand.nextInt(MAP_SIZE_X);
                        int y = rand.nextInt(MAP_SIZE_Y);
                        if(map[x][y] == '.'){
                                entity.setXCoor(x);
                                entity.setYCoor(y);
                                //map[entity.getXCoor()][entity.getYCoor()] = 'Z';
                                createCheck = true;
                        }
                }
                entity.setIsItem(true);
		entity.setIsStairs(true);

                //add one item to it's inventory
                Inventory entityInv = entity.getInventory();
                ItemGenerator genItem = new ItemGenerator();
                entityInv.add(genItem.generate());

                //add to arraylist
                entities.add(entity);
	}

	//this determines movement for entities
	public void movement(Entity entity){
		if(entity.getIsPlayer()){
			//determine based off input class
			Input input = new Input(entity);
			input.defaultTerminal();

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
					attemptY = attemptY - 1;
					break;
				//right
				case 2:
					attemptY = attemptY + 1;
					break;
				//up
				case 3:
					attemptX = attemptX - 1;
					break;
				//down
				case 4:
					attemptX = attemptX + 1;
					break;
				//not movement
				case 5:
					//System.out.println("returned 5");
					break;
				//saving the game
				case 6:
					save();
					break;
				//player loading the save file
				case 7:
					loadMap();
					loadEntities();
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
			for(int i = 0; i < entities.size(); i++){
				if(attemptX == entities.get(i).getXCoor() && attemptY == entities.get(i).getYCoor()){
					Entity defense = entities.get(i);
					combat(entity, defense);
					didCombat = true;
				}
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
		else if(entity.getIsItem()){
			// item should not move
			// this can be left blank
		}
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

			//check for AI type
			if(entity.getAIMovement()){

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

			}
			//dumb AI movement
			else{
				Random rand = new Random();
				int direction = rand.nextInt(4);

                        	switch(direction){
                                	//left
                                	case 0:
                                        	attemptX = attemptX - 1;
                                        	break;
                                	//right
                               		case 1:
                                        	attemptX = attemptX + 1;
                                        	break;
                                	//up
                                	case 2:
                                        	attemptY = attemptY - 1;
                                        	break;
                                	//down
                                	case 3:
                                        	attemptY = attemptY + 1;
                                        	break;
					default:
						System.out.println("Error in Entity AI movement (dumb)");
				}
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
	
	//loads in a map from a text file
	public void ImportMap(){
		try{
			Input input = new Input();
			ArrayList<String> unassignedMap = input.getRoom(currentFloor);
			importMap = new char[MAP_SIZE_X][MAP_SIZE_Y];
			int sequence = 0;
			for(int i = 0; i < MAP_SIZE_X; i++){
				for(int j = 0; j < MAP_SIZE_Y; j++){
					char conversion = unassignedMap.get(sequence).charAt(1);
					importMap[i][j] = conversion;
					sequence++;
					//System.out.print(importMap[i][j]);
				}
				//System.out.print("\n");
			}
		}
		catch(Exception e){
			importMap = DEFAULT_MAP;
			System.out.println("Error in map loading, default map selected.");
		}

		//importMap = DEFAULT_MAP; //remove in final version
	}

	//change floors
	public void changeFloors(){
		depopulate();
		ImportMap();
		for(int i = 0; i < MAP_SIZE_X; i++){
                	for(int j = 0; j < MAP_SIZE_Y; j++){
                        	map[i][j] = importMap[i][j];
                        }
                }
		movePlayer();
		populate();
	}

	//this saves all the entities to be loaded in later
	public void save(){
		//this should pass the arraylist of entities and save them to files, also passes in the floor number
		Input input = new Input();
		input.save(entities, currentFloor);
	}

	//this loads all entities from
	public void loadEntities(){
		//this should recieve an arraylist of entities and update the map arraylist
		Input input = new Input();
		entitites = input.loadEntity();
		firstMap();
		//currently uninished
	}
		
	//load in saved map info
	public void loadMap(){
		//this should read in a number from a file and change currentFloor and call ImportMap()
		Input input = new Input();
		currentFloor = input.loadFloor();
		ImportMap();
		//currently unfinished
	}



}
