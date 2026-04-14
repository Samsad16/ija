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
}