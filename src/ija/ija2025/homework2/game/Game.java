package ija.ija2025.homework2.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import ija.ija2025.homework2.common.GameEvent;
import ija.ija2025.homework2.common.Position;
import ija.ija2025.homework2.tool.GameObserver;

public class Game {
    private final String[] map_definition;
    private final List<GameObserver> observable = new ArrayList<>();
    private final List<Unit> units_in_game = new ArrayList<>();
    
    public Game(String[] mapDefinition) {
        this.map_definition = mapDefinition;
    }

    // Observers
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

        units_in_game.add(new_unit);

        return new_unit;
    }

    public String getPositionTerrain(Position position) {
        String row = this.map_definition[position.getRow()];
        String terrain_type = row.split(" ")[position.getCol()];
        return terrain_type;
    }

    public boolean moveUnit(Position from, Position to) {
        List<Position> available_pos = getReachableTiles(from);
        if (available_pos.contains(to)) {

            for (int index_unit = 0; index_unit < units_in_game.size(); index_unit++) {
                if ((units_in_game.get(index_unit).getPosition().getRow() == from.getRow()) &&
                    (units_in_game.get(index_unit).getPosition().getCol() == from.getCol())) {
                    to = units_in_game.get(index_unit).getPosition();
                    break;
                }
            }

            updateObservable(new GameEvent(from, to));
            return true;
        }
        return false;
    }

    public List<Position> getReachableTiles(Position current_position) {        
        Unit current_unit = null;

        // find unit in units in game
        for (int index_unit = 0; index_unit < units_in_game.size(); index_unit++) {
            if ((units_in_game.get(index_unit).getPosition().getRow() == current_position.getRow()) &&
                (units_in_game.get(index_unit).getPosition().getCol() == current_position.getCol())) {
                current_unit = units_in_game.get(index_unit);
                break;
            }
        }

        Queue<Position> queue = new LinkedList<>();
        queue.add(current_position);

        //visited
        List<Position> visited = new ArrayList<>();

        int[][] neighbors = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };
        int max_rows = this.map_definition.length;
        int max_cols = this.map_definition[0].split(" ").length;

        int current_max_move = current_unit.getMaxMove();

        while (!queue.isEmpty()) {
            Position current = queue.poll();

            //
            Tile current_tile = new Tile(current, current_max_move);

            for (int direction = 0; direction < neighbors.length; direction++) {
                int new_row = current.getRow() + neighbors[direction][0];
                int new_col = current.getCol() + neighbors[direction][1];
                if (new_row < 0 || new_row >= max_rows || new_col < 0 || new_col >= max_cols) {
                    continue;
                }

                Position next_position = new Position(new_row, new_col);

                //
                String next_terrain = getPositionTerrain(next_position);
                int new_tile_cost = current_tile.getTileCost(next_terrain, current_unit);

                //
                if (new_tile_cost < 0) {
                    continue;
                }

                //
                int remaining_move = current_tile.getRemainingMove() - new_tile_cost;

                if (!visited.contains(next_position)) {
                    visited.add(next_position);
                    queue.add(next_position);
                }
            }
        }

        return visited;
    }
}
