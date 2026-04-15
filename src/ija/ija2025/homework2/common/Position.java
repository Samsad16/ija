package ija.ija2025.homework2.common;

import java.util.Objects;

public class Position {
    private final int row;
    private final int col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() { 
        return row; 
    }
    
    public int getCol() { 
        return col; 
    }
    
    @Override
    public String toString() { 
        return "[" + row + "," + col + "]"; 
    }

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

    @Override
    public int hashCode() {
        int hash = 1;
        hash = hash * 31 + this.row;
        hash = hash * 31 + this.col;
        return hash;
    }
}