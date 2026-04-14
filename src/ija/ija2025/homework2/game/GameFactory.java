package ija.ija2025.homework2.game;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class GameFactory {
    // map provided as lines
    public static Game createGame(String[] mapDefinition) {
        if (mapDefinition == null || mapDefinition.length == 0) {
            throw new IllegalArgumentException("Map definition must not be null or empty.");
        }
        return new Game(mapDefinition);
    }

    // load map from file
    public static Game createGame(Path mapFile) {
        if (mapFile == null) {
            throw new IllegalArgumentException("Map file path must not be null.");
        }
        // read file and convert to lines
        try {
            // input in CSV 
            List<String> lines = Files.readAllLines(mapFile).stream()
                .map(String::trim)
                .filter(line -> !line.isEmpty())
                .map(line -> line.replace(",", " ").trim())
                .toList();
            return createGame(lines.toArray(String[]::new));
        } catch (IOException e) {
            throw new IllegalArgumentException("Failed to read map file.");
        }
    }
}
