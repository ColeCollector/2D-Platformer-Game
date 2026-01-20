# My Platformer Game

The application will be a **2d platformer game** where there is a player who can move around and has to avoid enemies. If the player collides with the enemy it will take damage. If the players health reaches 0 the game will reset. Anyone can play this game though I think teenagers would get the most fun out of it. This project is of interest to me because it's something I haven't made before and sounds like something that fits well within the requirements of this project.

## User Stories:
- As a user, I want to be able to move around the map
- As a user, I want to be reset the level if i die
- As a user, I want to be able to kill the enemy
- As a user, I want to be take damage if i collide with an enemy
- As a user, I want to die if i take too much damage
- As a user, I want to be able to save my data
- As a user, I want to be able to load my data
- As a user, I don't want to walk through walls

## Instructions for End User:

- You can add multiple Enemy to EnemyList by clicking the button "Random" at the top
- You can add remove Enemy from EnemyList by colliding with an enemy from above
- You can locate my visual component by giving "q" as an input
- You can save the state of my application by pressing "Save" at the top
- You can reload the state of my application by pressing "Load" at the top

## Phase 4: Task 2
Initial Launch:
- EnemyList Reset
  
Player clicks the "Load" button:
- EnemyList Reset
- Enemy added to EnemyList
- Enemy added to EnemyList

Player Kills an enemy:
- Enemy removed from EnemyList

Player clicks the "Random" button twice:
- Enemy added to EnemyList
- Enemy added to EnemyList

Player clicks "Load" button:
- EnemyList Reset
- Enemy added to EnemyList
- Enemy added to EnemyList
  
## Phase 4: Task 3
If I had more time I would make a new class for the terminal UI to help seperate classes and improve readability. I could also add observer and observeable classes which would improve updating and receiving updates across classes. I could potentially add an interface to reduce repitition across Player, Enemy and Walls since those classes have a lot of the same functions. Those are the main changes I would make, I can't really see any other changes.

## Credits:
Enemy and Wall PNGs from Vecteezy.com