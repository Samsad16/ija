package ija.ija2025.homework2.game;

import ija.ija2025.homework2.common.Position;

public class Unit {
    private Position position;
    private String type;
    private String player;
    private int hp;
    private int max_move;

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

    public void changePosition(Position new_position) {
        this.position = new_position;
    }

    @Override
    public String toString() {
        //position.toString uz dava [] kolem position
        return "{" + this.type + position.toString() + "[" + this.hp + "]}";
    }
}