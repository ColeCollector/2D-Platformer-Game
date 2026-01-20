package ui;

import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import model.Category;
import model.Enemy;
import model.EnemyList;
import model.Entity;
import model.Event;
import model.EventLog;
import model.Map;
import model.Player;
import model.Wall;
import model.WallList;
import persistence.JsonReader;
import persistence.JsonWriter;

/*
 * Represents a platformer game.
*/

// Credit to B02-SpaceInvadersBase
public class Game {
    private static final String JSON_STORE = "./data/map.json";
    public static final int WIDTH = 900;
    public static final int HEIGHT = 675;
    private boolean isGameOver;
    private int lastID;

    private Player player;
    private EnemyList enemyList;
    private WallList wallList;
    
    private Map map;
    
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // Constructs a Puzzle Game
    // effects:  creates empty lists of enemies and walls, puts player on top left of the screen
    public Game() {
        setUp();
    }
    
    public boolean isOver() {
        return isGameOver;
    }
    
    public Player getPlayer() {
        return player;
    }

    public int getHealth() {
        return player.getMoves();
    }

    public List<Enemy> getEnemies() {
        return enemyList.getList();
    }

    public List<Wall> getWalls() {
        return wallList.getList();
    }

    // Sets / resets the game
    // modifies: this
    // effects:  clears list of missiles and invaders, initializes player
    private void setUp() {
        map = new Map("empty");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        load();

        isGameOver = false;
    }

    // MODIFIES: this
    // EFFECTS: move the player to the desired coordinate if it wont be colliding with an enemy or a wall.
    //          if the player collides with a enemy it will take damage and if the player dies it will reset.
    private void move(int x, int y) {
        boolean wallCollide = wallList.handleCollisions(x, y);
        enemyList.handleCollisions(x, y);

        if (player.getMoves() == 0) {
            isGameOver = true;

        } else {
            player.move(wallCollide, x, y);
        }
    }

    public void printEvents() {
        Iterator<Event> iterator = EventLog.getInstance().iterator();

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

    // Controls the player
    // modifies: this
    // effects: turns player in response to key code 
    public void playerControl(int keyCode) {
        if (keyCode == KeyEvent.VK_R && isGameOver) {
            load();
            isGameOver = false;

        } else if (isGameOver == false) {
            if (keyCode == KeyEvent.VK_W) {
                move(0, -1);

            } else if (keyCode == KeyEvent.VK_A) {
                move(-1, 0);
            
            } else if (keyCode == KeyEvent.VK_S) {
                move(0, 1);

            } else if (keyCode == KeyEvent.VK_D) {
                move(1, 0);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: loads player, enemy and walls
    public void load() {
        try {
            map = jsonReader.read();
            System.out.println("Loaded " + map.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }

        List<Entity> entities = map.getEntities();
        enemyList = new EnemyList(map);
        wallList = new WallList(map, enemyList);

        for (Entity e : entities) {
            if (e.getCategory() == Category.PLAYER) {
                player = new Player(e.getX(), e.getY(), e.getHealth());
                
            } else if (e.getCategory() == Category.ENEMY) {
                enemyList.addEnemy(new Enemy(e.getX(), e.getY(), e.getid()));
                if (e.getid() > lastID) {
                    lastID = e.getid();
                }

            } else if (e.getCategory() == Category.WALL) {
                wallList.addWall(new Wall(e.getX(), e.getY(), e.getid()));
                if (e.getid() > lastID) {
                    lastID = e.getid();
                }
            }
        }

        enemyList.setPlayer(player);
        wallList.setPlayer(player);
    }

    // MODIFIES: this
    // EFFECTS: adds an enemy to enemyList with random position and a unique id
    public void randomEnemy() {
        Random random = new Random();
        int randomNumber1 = random.nextInt(26);
        int randomNumber2 = random.nextInt(19);
        map.addEntity(new Entity(Category.ENEMY, randomNumber1, randomNumber2, 0, lastID + 1));
        enemyList.addEnemy(new Enemy(randomNumber1, randomNumber2, lastID + 1));
        lastID += 1;
    }

    // EFFECTS: saves the map to file
    public void save() {
        try {
            map.removePlayer();
            map.addEntity(new Entity(Category.PLAYER, player.getX(), player.getY(), player.getMoves(), 0));
            jsonWriter.open();
            jsonWriter.write(map);
            jsonWriter.close();
            System.out.println("Saved " + map.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }
}
