/* Bryan Potts
 *
 * CPSC 240
 * 10/25/2021
 * Dugneon Crawler Project
 * Map Class
 */
/**
 * This class creates the initial map and holds any updates to the map,
 * it also stores the positions of entities on the map and runs a few
 * map related methods like combat, movement updates, and printing the
 * new map.
 * @author Bryan Potts
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

	/**
	*Default constructor for the Map Class.
	*This constructor sets up the map for the game as well as populating it.
	*This can call methods for loading the state of the game.
	*This constructor gets user input related to setting up the map.
	*This constructor will call on Entity classes and the Input class.
	*/
	public Map(){
		
		//start of contructor
		boolean load = askLoad();
		if(load){
			loadMap();
			loadEntities();
			firstMap();
		}
		//false condition, creates a new game
		else{
			//import a map from files
			//System.out.println(currentFloor); //test line
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

			//bonus question that never gets used for anything
			System.out.print("Are you a Wizard, a Knight, or a Thief?: ");
			String characterClass = input.next();

			//create non player items and entities
			populate();

			//setup map
			firstMap();
		}
	}
	
	//check if player wants to load the save file
	/**
	*This method asks the user if they want to load the save file.
	*This is called from the constructor.
	*This method accepts yes/no input and returns a boolean that is is true only
	*in the case the response is a yes. If the response is a no or otherwise not handled then it returns
	*false. User input that is neither yes or no is returns false and prints a message indicating that the input
	*is not a valid input.
	*@return a boolean
	*/
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
	/**
	*This method puts items, enemies, and the stairs on the map.
	*All of these things are Entity class objects stored in a list in the class.
	*This adds them to the list to be used by other methods.
	*/
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
	/**
	*This method removes items, enemies, and the stairs from the map.
	*All of the things are Entity class objects stroed in a list in the class.
	*This removes them from the list so that they can no longer be used.
	*/
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
	/**
	*This method moves the player to an open spot on the map.
	*This method will move the player to any spot on the map not currently in use, 
	*as long as the spot is a spot that the user could normally move on during gameplay.
	*/
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
	/**
	*This method outputs the map to the user.
	*The method takes the currently map in use as well as the position of all things on the map 
	*and outputs it to the user's terminal window.
	*/
	public void printMap(){
		for(int i = 0; i < MAP_SIZE_X; i++){
			for(int j = 0; j < MAP_SIZE_Y; j++){
				System.out.print(map[i][j]);
			}
			System.out.print("\n");
		}
	}

	//finds the player in the arraylist
	/**
	*This method finds the player on the map.
	*This method will find the player's Entity object in the list it is stored in and return it when called.
	*The Entity class object this returns should be the player but if the player cannot be found it will return a special 
	*Entity object, this object is not the player but is used to prevent compiling errors.
	*@return an Entity class object
	*/
	public Entity getPlayer(){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i).getIsPlayer()){
				return entities.get(i);
			}
		}
	
		return entities.get(0); //this returns the BAD entity
	}
	
	//creates a player 
	/**
	*This method creates a Player and puts them on the map.
	*This creates an Entity Player object and adds it to the list that is used for updating the map
	*@param name a String
	*/
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
	/**
	*This method generates a new weapon and armor for an Entity.
	*This method takes an Entity object, accesses it's inventory, and gives it a weapon and armor to start with.
	*This was originally intended only to be used on the player which is why the parameter is named player, but it can be 
	*used with any Entity object.
	*@param player an Entity object
	*/
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
	/**
	*This method creates Enemies.
	*This method creates an Entity object that acts as an enemy, and adds it to the list of Entities to referrenced later.
	*It was orignally intended to create more than just enemies but that proved inefficient, but the name still reflects that intent.
	*/
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
	/**
	*This method moves the BAD Entity.
	*The BAD entity is an Entity object that does not act as a normal entity. It exists for debug purposes.
	*This method moves this Entity off the accessible parts of the map so that the player never interacts with it.
	*/
	public void moveBAD(){
		Entity bad = entities.get(0);
		bad.setXCoor(0);
		bad.setYCoor(0);
		bad.setName("I AM ERROR");
	}

	//first time map setup
	/**
	*This method sets up the map one time.
	*This method gets all Entity data from the list and puts them on the map in the right spot.
	*This method also outputs the map to the user's termial afterwords.
	*/
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
	
	/**
	*This method moves all Entity objects.
	*This method goes updates the map with the new positions of all Entities stored in the list.
	*This method must be called from the main class to to start the game.
	*/
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
	
	/**
	*This method performs interactions between Entity objects.
	*This method handles all forms of interactions between Entity objects. 
	*This can be used with any Entity.
	*Originally only intended only for combat interactions but has been explanded to cover all interactions.
	*@param attacker an Entity object, the one performing the action
	*@param defender an Entity object, the one the action is happening to
	*/
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

	/**
	*This method creates an ItemEntity object.
	*This method creates and Entity object of the ItemEntity class that acts as an item for the user to interact with.
	*This Entity is added to the Entity list.
	*/
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
		
		//changes name ad consumable property
		ArrayList<Item> eItems = entityInv.getItems();
		Item tempItem = eItems.get(0);
		entity.setName(tempItem.getName());
		String name = entity.getName();
		if (name.equals("Food Stuffs") || name.equals("Healing Potions") || name.equals("Mana Potions") || name.equals("Antidotes"))
		{
			entity.setisConsumable(true);
		}

		//add to arraylist
                entities.add(entity);
	}

	
	//this method creates the stairs
	/**
	*This method creates stairs to advance to the next floor.
	*This method creates an Entity object that when interacted with will allow the user to advance to the next floor.
	*This Entity is added to the Entity list.
	*/
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
	/**
	*This method determines how Entities will move.
	*This method calculates a new position for an Entity.
	*This method takes into consideration the type of Entity as well.
	*This can be used for ALL Entity types.
	*This can also call on the combat method which handles interactions.
	*@param entity an Entity object
	*/
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
					firstMap();
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
	/**
	*This method loads in a new map from a file.
	*This method opens a file and checks its contents and creates a new map based off the contents of the file.
	*The map is then used to update the current map.
	*/
	public void ImportMap(){
		try{
			//System.out.println(1); //test line
			Input input = new Input();
			//System.out.println(2); //test line
			//System.out.println(currentFloor); //test line
			ArrayList<String> unassignedMap = input.getRoom(currentFloor);
			//System.out.println(3); //test line
			importMap = new char[MAP_SIZE_X][MAP_SIZE_Y];
			//System.out.println(4); //test line
			int sequence = 0;
			for(int i = 0; i < MAP_SIZE_X; i++){
				for(int j = 0; j < MAP_SIZE_Y; j++){
					//System.out.println(sequence); //test line
					//System.out.println(unassignedMap.get(sequence)); //test line
					char conversion = unassignedMap.get(sequence).charAt(1);
					importMap[i][j] = conversion;
					sequence++;
					//System.out.print(importMap[i][j]); //test line
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
	/**
	*This method changes and readies a new map.
	*This method removes all Entities from the list, loads in the new map, moves the player to an open spot, and adds in new Entities to the list.
	*/
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
	/**
	*This method saves all Entities and the map.
	*All Entities in the list and the current map are passed to the Input classes to be saved to a file.
	*/
	public void save(){
		//this should pass the arraylist of entities and save them to files, also passes in the floor number
		Input input = new Input();
		input.save(entities, currentFloor);
	}

	//this loads all entities from
	/**
	*This method loads in Entities.
	*This method loads in Entities from a file and adds them to  the list.
	*/
	public void loadEntities(){
		//this should recieve an arraylist of entities and update the map arraylist
		Input input = new Input();
		entities = input.loadEntity();
	}
		
	//load in saved map info
	/**
	*This method loads in the saved map.
	*This method changes the floor number which is used to determine which map file to load.
	*Then the corresponding map file is loaded.
	*/
	public void loadMap(){
		//this should read in a number from a file and change currentFloor and call ImportMap()
		Input input = new Input();
		currentFloor = input.loadFloor();
		ImportMap();
	}

}
