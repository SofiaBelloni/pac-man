# PACMAN

**Object-Oriented Programming Project (OOP)**

*May 15, 2020*

The goal is to create a video game inspired by Pac-Man from 1980. The player must guide Pac-Man through a maze, making him eat as many pellets as possible while avoiding waves of ghosts.

### Functional Requirements

- Creation of an initial menu that allows starting the game and accessing screens for settings, player rankings, and game instructions with a control scheme.
- Implementation of a settings screen that allows enabling/disabling sound, resetting the leaderboard, and changing the game map.
- Creation of a "Game Over" screen with the option to enter a name that will be saved in the leaderboard.
- Presence of five types of ghosts, distinguished by their "character", meaning the way they move.
- There are tunnels at the edges of the game map that lead to the other side.
- During the game, it's possible to see the number of remaining lives, the total score, the remaining time for the current level, and the current record.

### Non-Functional Requirements

- Presence of sound effects related to certain actions.
- Smooth animations for moving elements.
- Implementation of a user-friendly graphical interface.

### Domain Analysis and Model

The entities in the game are Pac-Man and the ghosts. The player must guide Pac-Man through a maze, making him eat as many pellets as possible while avoiding waves of ghosts, which increase as the level progresses. Eating pellets scattered throughout the map earns points, and upon reaching a certain score, the situation reverses, and Pac-Man can eat the ghosts for a limited time. At the beginning of each level, a timer starts, and when it ends, the player advances to the next level, a new wave of ghosts begins, and the pellets regenerate.

### Architecture

For the development of this software, the Model-View-Controller (MVC) architectural pattern was chosen.
