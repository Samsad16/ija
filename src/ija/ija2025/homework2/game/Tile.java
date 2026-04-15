package ija.ija2025.homework2.game;

import ija.ija2025.homework2.common.Position;

public class Tile {
    private Position position;
    private int remaining_move;

    public Tile(Position position, int remaining_move) {
        this.position = position;
        this.remaining_move = remaining_move;
    }

    public int getTileCost(String terrain, Unit current_unit) {
        int cost;
        switch(terrain) {
            case "P": 
                cost = 1;
                break;
            case "F":
                cost = current_unit.getType() == "Tank" ? 2 : 1;
                break;
            case "W":
                // -1 means impassible
                cost = -1;
                break;
            case "M":
                cost = current_unit.getType() == "Tank" ? -1 : 2;
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
