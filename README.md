# AdventureScape
An Action/RPG 2D game demo developed on Java (No game engine used) as a fun project. I created this project to learn Java, and figured best way to learn is just create a simple game which envovles many programming concepts such as functions, OOP, Enums etc.

# Gameplay
There are 4 stages in game. First stage is a room where attack option is disabled and you can only explore. In second stage player can shoot fireball and there are 4 low level enemy whom players have to kill to advance.  In third stage player encounter medium level enemies with more health and different shooting mechanic. And final stage is boss round where he moves and shoots in four direction with much more health.

# What does game include
- GameState class - Where it stores just a simple variable regarding which map player is in, and load appropriate map.
- Player class - This class stores players health, location, its current map and movement and shooting mechanic.
- Enemy class - This class is used to create different types of enemy and initiate them. It holds enemy type, location, movement and logic.
- Projectile class - Any time player/enemy shoots it initiate this class with some default property. This class holds data of projective direction, speed and destruction on obstacle.

- Game also include basic AI, where enemy starts shooting if player is in their vicinity.

# Screenshot

# Main Menu

![Main menu](/screenshot/one.PNG?raw=true "Main Menu")

# Room (Stage 1)

![Room (Stage 1)](/screenshot/two.PNG?raw=true "Stage 1")

# Garden (Stage 2)

![Garden (Stage 2)](/screenshot/three.PNG?raw=true "Stage 2")

# Garden (Stage 3)

![Garden (Stage 3)](/screenshot/four.PNG?raw=true "Stage 3")

# Boss Room (Stage 4)

![Boss Room (Stage 4)](/screenshot/five.PNG?raw=true "Stage 4")
