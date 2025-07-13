package fighters;

import framework.BattleField;
import framework.Random131;

public class BasicSoldier {

	// Static constants
	public final static int INITIAL_HEALTH = 10; 
	public final static int ARMOR = 20;   
	public final static int STRENGTH = 30;  
	public final static int SKILL = 40;  

	public final static int UP = 0;
	public final static int RIGHT = 1;
	public final static int DOWN = 2;
	public final static int LEFT = 3;
	public final static int UP_AND_RIGHT = 4;
	public final static int DOWN_AND_RIGHT = 5;
	public final static int DOWN_AND_LEFT = 6;
	public final static int UP_AND_LEFT = 7;
	public final static int NEUTRAL = -1;

	public final BattleField grid;  
	public int row, col; 
	public int health; 
	public final int team; 

	public BasicSoldier(BattleField gridIn, int teamIn, int rowIn, int colIn) {

		grid = gridIn;
		row = rowIn;
		col = colIn;
		health = INITIAL_HEALTH;
		team = teamIn;	
	}

	public boolean canMove() {
		return grid.get(row - 1, col) == BattleField.EMPTY || 
				grid.get(row + 1, col) == BattleField.EMPTY || 
				grid.get(row, col - 1) == BattleField.EMPTY || 
				grid.get(row, col + 1) == BattleField.EMPTY;

	}

	public int numberOfEnemiesRemaining() {
		int numEnemies = 0;
		int enemy;

		if (team == BattleField.BLUE_TEAM) {
			enemy = BattleField.RED_TEAM;
		} else {
			enemy = BattleField.BLUE_TEAM;
		}

		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getCols(); c++) {
				if (grid.get(r,c) == enemy) {
					numEnemies++;
				}
			}	 
		}

		return numEnemies;
	}

	public int getDistance(int destinationRow, int destinationCol) {

		return Math.abs(destinationRow - row) + Math.abs(destinationCol - col);
	}

	public int getDirection(int destinationRow, int destinationCol) {

		int direction = 0;

		if (destinationRow > row && destinationCol == col) {
			direction = DOWN;
		} else if (destinationRow < row && destinationCol == col) {
			direction = UP;
		} else if (destinationRow == row && destinationCol < col) {
			direction = LEFT;
		} else if (destinationRow == row && destinationCol > col) {
			direction = RIGHT;
		} else if (destinationRow < row && destinationCol > col) {
			direction = UP_AND_RIGHT;
		} else if (destinationRow > row && destinationCol > col) {
			direction = DOWN_AND_RIGHT ;
		} else if (destinationRow > row && destinationCol < col) {
			direction = DOWN_AND_LEFT ;
		} else if (destinationRow < row && destinationCol < col) {
			direction = UP_AND_LEFT ;	
		} else {
			direction = NEUTRAL;
		}

		return direction;
	}

	public int getDirectionOfNearestFriend() {

		int nearestDistance = grid.getRows() + grid.getCols();
		int nearestRow = row;
		int nearestCol = col;

		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getCols(); c++) {
				if (!(r == row && c == col) && grid.get(r,c) == team &&
						getDistance(r,c) < nearestDistance) {
					nearestDistance = getDistance(r,c);
					nearestRow = r;
					nearestCol = c;
				}	
			}	 
		}

		return getDirection(nearestRow, nearestCol);
	}

	public int countNearbyFriends(int radius) {

		int numFriends = 0;

		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getCols(); c++) {
				if (grid.get(r,c) == team && getDistance(r,c) <= radius) {
					numFriends++;
				}
			}	 
		}
		// reduce by one for current soldier
		numFriends--;

		return numFriends;
	}

	public int getDirectionOfNearestEnemy(int radius) {

		int nearestDistance = grid.getRows() + grid.getCols();
		int nearestRow = row;
		int nearestCol = col;
		int enemy;

		if (team == BattleField.BLUE_TEAM) {
			enemy = BattleField.RED_TEAM;
		} else {
			enemy = BattleField.BLUE_TEAM;
		}


		for (int r = 0; r < grid.getRows(); r++) {
			for (int c = 0; c < grid.getCols(); c++) {
				if (grid.get(r,c) == enemy && getDistance(r,c) <= radius
						&& getDistance(r,c) < nearestDistance) {
					nearestDistance = getDistance(r,c);
					nearestRow = r;
					nearestCol = c;
				}	
			}	 
		}

		return getDirection(nearestRow, nearestCol);
	}

	public void performMyTurn()  {

		int directionEnemy = getDirectionOfNearestEnemy(1);
		if (directionEnemy != NEUTRAL) {
			if (directionEnemy == UP) {
				grid.attack(row - 1, col);
			} else if (directionEnemy == DOWN) {
				grid.attack(row + 1, col);
			} else if (directionEnemy == LEFT) {
				grid.attack(row, col - 1);
			} else  {
				grid.attack(row, col + 1);
			}
		} else if (canMove()) {
			if (grid.get(row - 1, col) == BattleField.EMPTY) {
				row = row - 1;
			} else if (grid.get(row + 1, col) == BattleField.EMPTY) {
				row = row + 1;
			} else if (grid.get(row, col - 1) == BattleField.EMPTY) {
				col = col - 1;
			} else { 
				col = col + 1;
			}
		} 
	}
}