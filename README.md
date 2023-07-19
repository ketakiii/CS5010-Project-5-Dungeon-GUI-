# Project Description

In this version of the Dungeon game, the part of the program that interfaces with the user, the view, is to be replaced with an interactive graphical user interface (GUI).

#UML

Updated UML
![UML](../res/uml/Project5updated.png)

# Changes Made to the UML


# How to Run the Jar 
## For GUI Game:
java -jar Project_5.jar --gui

## For Text-Based Game:
java -jar Project_5.jar --text

# How to run a program

## Text Interface Way-
Here we just get a text based output of the game and the actions performed in our console. 
## Create a GameState 
`Random random = new Random(12);`

`IgameState gameState = new GameState(9, 10, 20, 30, 4, 4, false, 2, random);`

## Create a controller 
`Appendable output = System.out;`

`Icontroller controller = new Controller(scanner, output, gameState);`

## Use the controller to play game
`controller.playGame();`

##Graphical Interface Way-
Here we get the graphical interface of the game and the respective components are drawn as the player progresses the game. 

## Create a GameState
`Random random = new Random(12);`

`GameState gameState = new GameState(9, 10, 20, 30, 4, 4, false, 2, random);`

## Create a View 
` Iview view = new View(gameState);`

## Create a Controller
We give the model and the view to the controller. 

`IviewController controller = new ViewController(gameState, view);`

## Use the controller to play game
`controller.playGame(gameState);`

## Instructions on How to Play the Game
1. Player will start at its start location
2. The green clouds tell us a monster is nearby, light green one represents it is one distance away while a dark green one tells it is two distance away. 
3. To win reach the end location by shooting the monster and killing it. 
4. Shoot the monster twice to kill it. 
5. `Move: Arrow keys` 
6. `Shoot: Arrow keys + Number`
7. `Pick up Arrow: A`
8. `Pick up Treasure: T`


# Example Runs

## For One player
* Example runs for 1 player starting, arrows, smelling monster and winning have been shown in the screenshots in the folder: 
res/project5runs/oneplayer
* Example runs for 2 players starting, switching turns, picking treasures, picking arrows, smelling monster, and winning are shown in the folder : res/project5runs/twoplayers
* Example runs for 1 and 2 players traversing through the dungeon in the wrapped format has been shown in the screenshots in the folder : res/project5runs/Wrapped



# Assumptions
* The grid length should be more than 5 since the minimum distance between the start and the end location is 5.
* The grid width should be more than 5 since the minimum distance between the start and the end location is 5.
* The interconnectivity can be equal to or greater than 0.
* The percentage of treasure to be added to the caves randomly should be a number greater than 0.
* Dungeon are of two types : wrapped and non-wrapped.
* If the player reaches the end location alive it wins. 

# Limitations
* Need Java version 11 or greater.
* Can not test for a grid smaller than 7x7 since a minimum distance of 5 needs to be maintained between the start and the end locations.
* The interconnectivity can not be less than 3 in a maze less than 8x8 since the distance between the start and the end locations should be minimum 5 hence it does not get that space to be assigned. 

#Citations
1. [Canvas Question](https://northeastern.instructure.com/courses/136753/assignments/1707745)
2. [MST Algorithm](https://www.geeksforgeeks.org/prims-minimum-spanning-tree-mst-greedy-algo-5/)
3. [GeeksForGeeks](https://www.geeksforgeeks.org/)
4. [Google](https://www.google.com/)
5. [StackOverflow](https://stackoverflow.com/)
6. [Java Documentation](https://docs.oracle.com/en/java/)