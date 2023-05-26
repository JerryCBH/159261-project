# Hookshot Heroes

Hookshot Heroes is a classic maze puzzle solver game with RPG elements and a fun story.

In a realm shrouded in mystery, Lidia, a brave and determined heroine, embarks on a perilous journey. 
Drawn by the allure of hidden treasures and ancient secrets, she fearlessly enters the dungeons. 
Empowered by the legendary "hookshot," a grappling hook that fused to her arm, Lidia defies danger and navigates treacherous terrain. 
With each triumph over enemies and the acquisition of precious loot, she inches closer to the ultimate prize. Driven by unwavering bravery, 
Lidia's quest for glory unfolds as she unravels the depths of the dungeons, leaving an indelible mark upon the annals of Eldoria's history.

The game can be launched from IntelliJ IDE by electing the "Run" configuration and pressing the green Run button.
Alternatively, the game JAR file artifact can be built from the IDE and then run the command:

```shell
java -jar HookshotHeroes.jar
```

Source repository: https://github.com/JerryCBH/159261-project

---
## Game Features
- Use Grapple to hook onto walls and jump across obstacles.
- 10 unique dungeon mazes to solve.
- 2 boss fights.
- Treasures to collect to increase score and eat fruit barrels to replenish health.
- 2 player mode, where players race to see who can complete the rooms the fastest with the best score.
- Quest mode, where player(s) protect and guide the NPC character Avalon through the dungeon.
- Read your character’s thoughts as they collect items and battle enemies.
- Suspenseful music and immersive sound effects.
- Character / boss and item animations.
- Two end-game levels after defeating all the boss.

## About Game Design
- Non-linear game progression. Example: In level 4 we have two doors that lead to different levels. This makes the game more non-linear. This can be made more complex by adding multiple doors in each level, for instance hidden Easter egg levels.
- NPC AI has patrol, seek, wait states. More states in state machines can be added and more variety of state machines can be added. We have 3 state machines: NPC enemy, NPC follower, BGC (background character) follower / wonderer. There are parameters in state machine classes that tunes the behaviour via reaction time: how long to make a decision during each state. Increasing the reaction time makes the character slower and less agile. Possible improvements outside scope of this project: Introduce cruising state for flying terror that goes around in path of large circle, mimic flying behaviours. Add routing algorithms, currently the NPC calculates the shortest Euclidean distance and not considering path blocked by obstacle, so npc can get stuck from time to time.
- Friendly NPC can make random comments and reactions. Comments are from chat GPT. Each ChatGPT request runs on a different thread, no penalty on performance. The comments are made when the NPC state machine state changes or if the NPC health / score increase or decrease. There is also an idle timer, so NPC will say something every couple of seconds. This is made more random by adding random number generator to decide to speak or not when comment timer is up. We can make it more realistic by introducing more comment types. We have about 5 comment types to make remarks on different situations. If ChatGPT fails, there is a backup comment dictionary in the code.
- Comment speech bubble and score notifications are displayed beside characters on the screen in descending order. They follow characters.
- The use of Linked List / Array List / Queues as a means of communication for in-game events. There are Audio Queue, Animation Queue, Elimination Queue, Spawn queue. In-game object raises requests to these queues to be processed by game engine to play audio / animation at certain location and time, due to in-game events. These queues decouples the classes.
- World builder class: we can easily control addition of different types of objects in each level.
- Level class: Embodies a level. Returns a list of exits / entry doors. Contains a link to the next level object. Paints screen with details.
- Collision detection logic is a bit messy as it happens in two places rather than one. One in the in-game objects themselves, another at the world level. This can be improved.
- The classes are designed to support dependency injection, we can adopt a DI framework, but it's out of scope. Eg: State Machine all inherit IStateMachine interface and can be injected from DI or factory classes depending on the NPC class type.
- Grid design makes the movement not smooth enough. We can add smoothing to character / NPC movements.
- Bouncing ball projectile, explodes when runs out of energy.
- Smoke animation.
- We have three theme music, played at different level.
- 3 types of game: Single / double / quest (Hardest).
- NPC character generation - the NPC characters in the end game levels are randomly generated. We can randomly generate quest character as well if we have enough time.
- Quest characters follows closet players in quest, can jump with hook-shot, tracks a score.
- Consumable items like coins / vegetable cabbages / bombs are generated randomly. Improvements can be made such that they spawn on walkable area only. They could spawn inside walls and become inaccessible.

---
## How to play
Player One: Use 'W', 'S', 'D' and 'A' to move Lidia around the dungeon.
Use 'X' to fire the 'hookshot'

Player Two: Use the arrow keys to move Shura around the dungeon
Use '.' to fire the 'hookshot'

The hookshot can be used to:
- jump over the lava
- to collect items
- destroy bombs
- attack enemies

Be careful though, as the hookshot only has a limited range.

Collect coins to increase your score.
Open treasurers to boost your score and health.
Collect plant baskets for extra lives.
Collision with bombs, results in a loss of life.
Landing in the lava, results in a loss of life.

### Single Player Mode:

Navigate the dungeons solo, collecting as many coins and treasure, as quickly as
you can to complete the game with the best score.

### Double Player Mode:

You and a friend will compete against each other to see who can get the highest
score. Pick between collecting coins or opening chests or getting to the exit
the fastest. The first player to the exit will end the level for both players.
Level knowledge, speed and tactics, will help you secure the win.

### Quest Mode:

Help Avalon escape the Dungeon. This can be played either in Single or Double player.
Avalon will follow you and your friend. Watch over and guide her through the dungeons.

### Game Options:

- Change from Single Player Mode to Double Player Mode
- Enable / Disable music

---
## Group Member's Contributions
In alphabetical order.

Bryce:
-	level layout design.
-	Level layout coding.
-	Menu design.
-	Boss fights design.
-	Documentations.
-	General bug fixes.
-	Play testing.

Helen:
-	Game design and ideas.
-	Story ideas
-	Play testing.

Jerry:
-	Game software architecture design, core classes and base game functionalities.
-	Animations, audio and sound.
-	Game object interactions / collisions / scores.
-	NPC dialogues / Chat GPT / notifications.
-	Game AI designs.
-	GitHub repository setup and integration of code from team members.
-	Bug fixes.
-	Play testing.

Josh:
-	Game design and ideas.
-	Grapple mechanism and design.
-	Play testing.
