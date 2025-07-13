# 🔫 Basic Soldier AI – Java Battlefield Simulation

An autonomous agent built in Java to participate in a real-time, grid-based battlefield simulation. The `BasicSoldier` class implements movement and attack strategies against enemy units while cooperating with teammates, navigating obstacles, and making tactical decisions every turn.

## 🛠️ Technologies Used

- Java (OOP, logic-based control flow)
- 2D grid simulation

## 📌 Features

- **Combat & Movement Logic:** 
  - Makes decisions based on nearby enemies, allies, and available movement.
  - Uses direction-based constants to interact with a simulated battlefield.
  - Attacks adjacent enemies or moves into nearby empty grid cells.

- **AI Perception:**
  - Calculates the Manhattan distance to determine the shortest path to targets.
  - Detects and navigates toward the nearest friendly or enemy soldier.
  - Counts nearby allies within a configurable radius to support decision-making.

- **Strategic Flexibility:**
  - Responds dynamically each turn based on battlefield conditions.
  - Easily expandable into more advanced strategies (e.g., flanking, retreating, clustering).

## 🧠 Core Class: `BasicSoldier.java`

Implements methods such as:
- `canMove()`: Checks if movement is possible to adjacent cells.
- `getDirection(...)`: Determines direction toward a given location.
- `getDirectionOfNearestEnemy(...)`: Finds and tracks enemies within a radius.
- `performMyTurn()`: The central logic driving each unit’s behavior on its turn.

## 🖼️ Simulation Environment

- A visual GUI (`Driver.java`) initiates battles between soldier classes.
- Supports custom and built-in soldiers (e.g., Easy, Medium, Smart).
- Allows replaying the same battle using a deterministic RNG.
