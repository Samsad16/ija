package ija.ija2025.homework2;

public interface GameObserver {
    boolean notified;
    public void update(GameEvent e);
}
