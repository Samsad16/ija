/*************************************************************************
 * Module representing the core game logic. Manages the board, units and *
 * observers. Handles unit movement and pathfinding using BFS to         *
 * calculate reachable tiles.                                            *
 *                                                                       *
 * Authors: Tereza Doláková, Samuel Smutný (nettle), Natália Václavíková *
**************************************************************************/

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

    // List of playing units
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

    // Create unit and add it to list of units
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

    // Get terrain type based on position
    public String getPositionTerrain(Position position) {
        String row = this.map_definition[position.getRow()];
        String terrain_type = row.split(" ")[position.getCol()];
        return terrain_type;
    }

    // Moves unit to given position
    public boolean moveUnit(Position from, Position to) {

        // Check if position is reachalbe
        List<Position> available_pos = getReachableTiles(from);
        if (available_pos.contains(to)) {

            // Find unit and update its position
            for (int index_unit = 0; index_unit < units_in_game.size(); index_unit++) {
                if ((units_in_game.get(index_unit).getPosition().getRow() == from.getRow()) &&
                    (units_in_game.get(index_unit).getPosition().getCol() == from.getCol())) {
                        
                    units_in_game.get(index_unit).changePosition(to);
                    break;
                }
            }

            updateObservable(new GameEvent(from, to));
            return true;
        }
        return false;
    }

    // Uses BFS to get a list of positions reachable for a unit
    public List<Position> getReachableTiles(Position current_position) {        
        Unit current_unit = null;

        // Find unit in list of units
        for (int index_unit = 0; index_unit < units_in_game.size(); index_unit++) {
            if ((units_in_game.get(index_unit).getPosition().getRow() == current_position.getRow()) &&
                (units_in_game.get(index_unit).getPosition().getCol() == current_position.getCol())) {
                current_unit = units_in_game.get(index_unit);
                break;
            }
        }

        // Unit was not found in game
        if (current_unit == null) {
            return List.of();
        }

        // Get unit's maximum possible movement
        int current_max_move = current_unit.getMaxMove();

        // Create a queue of tiles
        Queue<Tile> queue = new LinkedList<>();
        Tile current_tile = new Tile(current_position, current_max_move);
        queue.add(current_tile);

        // Create list for reachable positions
        List<Position> reachable = new ArrayList<>();

        // Create movement matrix
        int[][] neighbors = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
        };
        int max_rows = this.map_definition.length;
        int max_cols = this.map_definition[0].split(" ").length;

        // Loop while there still are tiles in the queue
        while (!queue.isEmpty()) {

            // Get the current tile from queue
            current_tile = queue.poll();

            Position current = current_tile.getPosition();

            // Check neighbors in all four directions
            for (int direction = 0; direction < neighbors.length; direction++) {
                int new_row = current.getRow() + neighbors[direction][0];
                int new_col = current.getCol() + neighbors[direction][1];

                // Check map bounds
                if (new_row < 0 || new_row >= max_rows || new_col < 0 || new_col >= max_cols) {
                    continue;
                }

                Position next_position = new Position(new_row, new_col);

                // Get the new position's terrain type
                String next_terrain = getPositionTerrain(next_position);

                // Calculate the movement's cost
                int new_tile_cost = current_tile.getTileCost(next_terrain, current_unit);

                // If impassable -> -1
                if (new_tile_cost < 0) {
                    continue;
                }

                // Calculate remaining move
                int remaining_move = current_tile.getRemainingMove() - new_tile_cost;

                // If no remaining move, tile is unreachable
                if (remaining_move < 0) {
                    continue;
                }

                // Add new reachable position and add neighbor tile to queue
                if (!reachable.contains(next_position)) {
                    reachable.add(next_position);
                    Tile new_tile = new Tile(next_position, remaining_move);
                    queue.add(new_tile);
                }
            }
        }

        return reachable;
    }
}
