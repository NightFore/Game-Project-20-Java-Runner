// LevelDetails.java
package com.example.gameproject20javarunner.level;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class LevelDetails {
    private String tileSheetPath;
    private int originalTileWidth;
    private int originalTileHeight;
    private int displayTileWidth;
    private int displayTileHeight;
    private String[][] map;

    // Public no-argument constructor
    public LevelDetails() {
        // Default constructor
    }

    /**
     * Loads level details from a JSON file.
     *
     * @param jsonFilePath The path to the JSON file.
     * @return The LevelDetails object representing the level details.
     */
    public static LevelDetails loadFromJson(String jsonFilePath) {
        try {
            InputStream inputStream = LevelDetails.class.getResourceAsStream(jsonFilePath);
            if (inputStream == null) {
                throw new IllegalArgumentException("JSON file not found: " + jsonFilePath);
            }

            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, LevelDetails.class);
        } catch (IOException e) {
            throw new RuntimeException("Error loading level details from JSON", e);
        }
    }

    // Example getters
    public String getTileSheetPath() {
        return tileSheetPath;
    }

    public int getOriginalTileWidth() {
        return originalTileWidth;
    }

    public int getOriginalTileHeight() {
        return originalTileHeight;
    }

    public int getDisplayTileWidth() {
        return displayTileWidth;
    }

    public int getDisplayTileHeight() {
        return displayTileHeight;
    }

    public String[][] getMap() {
        return map;
    }
}