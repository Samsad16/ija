/*************************************************************************
 * Helper class representing a tile on the game map. Stores the tile     *
 * position and the unit's remaining movement.                           *
 * Used during pathfinding (BFS).                                        *
 *                                                                       *
 * Authors: Tereza Doláková, Samuel Smutný (nettle), Natália Václavíková *
**************************************************************************/

package ija.ija2025.homework2.game;

import ija.ija2025.homework2.common.Position;

// Class for representing the tile in map with the unit's remaining movement
public class Tile {
    private Position position;
    private int remaining_move;

    public Tile(Position position, int remaining_move) {
        this.position = position;
        this.remaining_move = remaining_move;
    }

    // Returns cost of a tile based on the unit's type
    public int getTileCost(String terrain, Unit current_unit) {
        int cost;
        switch(terrain) {
            case "P": 
                cost = 1;
                break;
            case "F":
                cost = "Tank".equals(current_unit.getType()) ? 2 : 1;
                break;
            case "W":
                // -1 means impassable
                cost = -1;
                break;
            case "M":
                cost = "Tank".equals(current_unit.getType()) ? -1 : 2;
                break;
            default:
                throw new IllegalArgumentException("Unsupported terrain type.");
        }
        return cost;
    }

    public Position getPosition() {
        return this.position;
    }

    public int getRemainingMove() {
        return this.remaining_move;
    }
}
