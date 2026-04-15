/******************************************************************
 * Module representing a game event triggered when a unit         *
 * successfully moves.                                            *
 *                                                                *
 * Authors: Tereza Doláková, Samuel Smutný, Natália Václavíková   *
*******************************************************************/

package ija.ija2025.homework2.common;

// Event describing succesful unit movement in the game
public class GameEvent {
    // Original unit position before moveUnit was aplied
    private Position from;
    // Final unit position
    private Position to;

    public GameEvent(Position from, Position to) {
        this.from = from;
        this.to = to;
    }

    // Returns source tile
    public Position getFrom() {
        return from;
    }

    // Returns destination tile
    public Position getTo() {
        return to;
    }
}
