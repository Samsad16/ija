/*************************************************************************
 * Module representing a unit on the game board. Stores the unit's type, *
 * owner, position, HP and maximum move range.                           *
 *                                                                       *
 * Authors: Tereza Doláková, Samuel Smutný (nettle), Natália Václavíková *
**************************************************************************/

package ija.ija2025.homework2.game;

import ija.ija2025.homework2.common.Position;

// Class for a playing unit
public class Unit {
    private Position position;
    private String type;
    private String player;
    private int hp;
    private int max_move; // Maximum movement of unit

    public Unit(String type, String player, int row, int col, int hp, int max_move) {
        this.position = new Position(row, col);
        this.type = type;
        this.player = player;
        this.hp = hp;
        this.max_move = max_move;
    }

    public Position getPosition() {
        return this.position;
    }

    public String getType() {
        return this.type;
    }

    public int getMaxMove() {
        return this.max_move;
    }

    // Updates unit's position
    public void changePosition(Position new_position) {
        this.position = new_position;
    }

    @Override
    public String toString() {

        // position.toString already puts [] around position
        return "{" + this.type + position.toString() + "[" + this.hp + "]}";
    }
}