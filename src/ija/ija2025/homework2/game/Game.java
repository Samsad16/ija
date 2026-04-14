package ija.ija2025.homework2.game;

import ija.ija2025.homework2.common.GameEvent;
import ija.ija2025.homework2.tool.GameObserver;
import ija.ija2025.homework2.common.Position;

import java.util.List;
import java.util.ArrayList;

public class Game {
    private String[] map_definition;
    private List<GameObserver> observable = new ArrayList<>();
    //private List<Unit> 
    
    public Game(String[] mapDefinition) {
        this.map_definition = mapDefinition;
    }

    // Observery
    public void addObserver(GameObserver g_o) {
        if(g_o != null) {
            observable.add(g_o);
        }
    }

    public void removeObserver(GameObserver g_o) {
        observable.remove(g_o);
    }

    private void updateObservable(GameEvent event) {
        for (GameObserver g_o : observable) {
            g_o.update(event);
        }
    }

    public Unit createUnit(String type, String player, int i, int j) {
        int max_move;
        switch(type) {
            case "Tank": 
                max_move = 6;
                break;
            case "Infantry":
                max_move = 3;
                break;
            default:
                throw new IllegalArgumentException("Unsupported unit type.");
        }
        var new_unit = new Unit(type, player, i, j, 100, max_move);
        return new_unit;
    }

    public boolean moveUnit(Position from, Position to) {
        List<Position> available_pos = getReachableTiles(from);
        if (available_pos.contains(to)) {
            from = to;
            updateObservable(new GameEvent(from, to));
            return true;
        }
        return false;
    }

    public List<Position> getReachableTiles(Position current_position) {
        //bfs
        //for
        // Tank (Move 6). Start [0,0]. 
            // Cesta do [1,1] (Les):
            // 1. [0,0]->[0,1] (Plain, cost 1).
            // 2. [0,1]->[1,1] (Forest, cost 2) -> Tanky v lese zpomalují!
            // Celkem cost = 3.

        // search unti on curent_position
        // tmp = unit.max_move
        // werjnvwkvw

        //int rows = mapDefinition.length;
        //int cols = mapDefinition[0].split(" ").length;
        
        //for (int row = 0; row < ?, row++) {
        //    for (int col = 0; col < ?; col++) {
                //check distance == 1
                //cost = ?
                //if can move => getReachableTiles(row, col)

                //"P P M",
                //"P F W",
                //"P P P"
            //}
        //}

        return new ArrayList<>();
    }
}
