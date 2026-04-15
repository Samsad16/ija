/*************************************************************************
 * Module for representing a position on the game board defined          *
 * by rows and columns. Used for identifying locations of units          *
 * and terrain tiles.                                                    *
 *                                                                       *
 * Authors: Tereza Doláková, Samuel Smutný (nettle), Natália Václavíková *
**************************************************************************/

package ija.ija2025.homework2.common;

import java.util.Objects;

public class Position {
    private final int row;
    private final int col;

    // Creates new position
    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    // Returns the row index 
    public int getRow() { 
        return row; 
    }
    
    // Returns the column index
    public int getCol() { 
        return col; 
    }
    
    // Returns a string representation of position 
    @Override
    public String toString() { 
        return "[" + row + "," + col + "]"; 
    }

    // Two positions are equal if they have the same row and column
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Position)) {
            return false;
        }

        Position pos = (Position) obj;
        return ((this.row == pos.row) && (this.col == pos.col));
    }

    // Hashcode override because of equals override to work correctly
    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + this.row;
        hash = hash * 31 + this.col;
        return hash;
    }
}