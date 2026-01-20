package ui;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import model.Category;
import model.Entity;
import persistence.JsonReader;
import persistence.JsonWriter;

import model.Map;
import model.Player;
import model.Enemy;
import model.EnemyList;
import model.WallList;
import model.Wall;

// Main Application
public class Terminal {
    private static final String JSON_STORE = "./data/map.json";
    private Player player;
    private EnemyList enemyList;
    private WallList wallList;
    private Scanner input;

    private Map map;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    public Terminal() {
        runLevel();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runLevel() {
        boolean keepGoing = true;
        String command = null;
        
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();
            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // EFFECTS: saves the map to file
    private void saveMap() {
        try {
            jsonWriter.open();
            jsonWriter.write(map);
            jsonWriter.close();
            System.out.println("Saved " + map.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: move the player to the desired coordinate if it wont be colliding with an enemy or a wall.
    //          if the player collides with a enemy it will take damage and if the player dies it will reset.
    private void move(int x, int y) {
        boolean wallCollide = wallList.handleCollisions(player, x, y);
        boolean enemyCollide = enemyList.handleCollisions(player, x, y, map);

        if (enemyCollide) {
            if (player.getHealth() == 1) {
                player.reset();
            } else {
                player.takeDamage();
            }
        }
        player.move(wallCollide | enemyCollide, x, y);
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("w")) {
            move(0,1);
        } else if (command.equals("s")) {
            move(0,-1);
        } else if (command.equals("a")) {
            move(-1,0);
        } else if (command.equals("d")) {
            move(1,0);
        } else if (command.equals("f")) {
            map.removePlayer();
            map.addEntity(new Entity(Category.values()[0], player.getX(), player.getY(), player.getHealth(), 0));
            saveMap();
        } else if (command.equals("g")) {
            load();
        } else {
            System.out.println("Invalid character");
        }
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tw -> move up");
        System.out.println("\ta -> move left");
        System.out.println("\ts -> move down");
        System.out.println("\td -> move right");
        System.out.println("\tf -> save the game");
        System.out.println("\tg -> load the game");
        System.out.println("\tq -> quit");
        System.out.printf("\n\tPlayer Position: %d %d \n", player.getX(), player.getY());
        System.out.printf("\tPlayer Health: %d \n", player.getHealth());

        int counter = 0;
        for (Enemy enemy : enemyList.getList()) {
            counter += 1;
            System.out.printf("\tEnemy %d Position: %d %d\n", counter, enemy.getX(), enemy.getY());
        }

        counter = 0;
        for (Wall wall : wallList.getList()) {
            counter += 1;
            System.out.printf("\tWall %d Position: %d %d\n", counter, wall.getX(), wall.getY());
        }
    }

    // MODIFIES: this
    // EFFECTS: loads player, enemy and walls
    private void load() {
        try {
            map = jsonReader.read();
            System.out.println("Loaded " + map.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        List<Entity> entities = map.getEntities();

        for (Entity e : entities) {
            if (e.getCategory() == Category.PLAYER) {
                player = new Player(e.getX(), e.getY(), e.getHealth());
                
            } else if (e.getCategory() == Category.ENEMY) {
                enemyList.addEnemy(new Enemy(e.getX(), e.getY(), e.getid()));

            } else if (e.getCategory() == Category.WALL) {
                wallList.addWall(new Wall(e.getX(), e.getY()));
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes player, enemy and walls
    private void init() {
        enemyList = new EnemyList();
        wallList = new WallList(new EnemyList());
        player = new Player(0, 0, 0);
        input = new Scanner(System.in);
        input.useDelimiter("\r?\n|\r");
    }
}