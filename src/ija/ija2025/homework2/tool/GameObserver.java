/******************************************************************
 * Module defining the GameObserver interface. Observers          *
 * receive notifications when a game event occurs.                *
 *                                                                *
 * Authors: Tereza Doláková, Samuel Smutný, Natália Václavíková   *
*******************************************************************/

package ija.ija2025.homework2.tool;

import ija.ija2025.homework2.common.GameEvent;

public interface GameObserver {
    public void update(GameEvent e);
}
