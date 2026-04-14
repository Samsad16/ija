package ija.ija2025.homework2.game;

import ija.ija2025.homework2.common.Position;

public class Game {
    public void addObserver(GameObserver go) {
        if(go != null) {
            //pridat do nejake dat struktury vsech observeru
        }
    }

    public Unit createUnit(String type, String player, int i, int j) {
        var new_unit = new Unit(String type, String player, int i, int j, 100);
        return new_unit;
    }

    static void moveUnit(Position from, Position to) {
        from = to;
    }

    static List<Position> getReachableTiles(Position current_position) {
        //
    }
}

public class Unit {
    private Position position;
    private String type;
    private String player;
    private int hp;

    public Unit(String type, String player, int row, int col, int hp) {
        this.position = new Position(row, col);
        this.type = type;
        this.player = player;
        this.hp = hp;
    }

    public Position getPosition() {
        return this.position;
    }

    @Override
    public String toString() {
        return "{" + this.type + "[" + position.toString() + "]" + "[" + this.hp + "]}";
    }
}