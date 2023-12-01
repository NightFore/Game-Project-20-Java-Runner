// LevelDetails.java

package com.example.gameproject20javarunner.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class LevelDetails {
    @JsonProperty("tileSheetPath")
    private String tileSheetPath;

    @JsonProperty("originalTileWidth")
    private int originalTileWidth;

    @JsonProperty("originalTileHeight")
    private int originalTileHeight;

    @JsonProperty("displayTileWidth")
    private int displayTileWidth;

    @JsonProperty("displayTileHeight")
    private int displayTileHeight;

    @JsonProperty("map")
    private String[][] map;

    /**
     * Loads level details from a JSON file using Jackson ObjectMapper.
     *
     * @param jsonFilePath The path to the JSON file.
     * @return The LevelDetails object representing the details of the loaded level.
     * @throws RuntimeException If there is an error loading level details from JSON.
     */
    public static LevelDetails loadFromJson(String jsonFilePath) {
        try {
            // Attempting to open an InputStream for the specified JSON file.
            InputStream inputStream = LevelDetails.class.getResourceAsStream(jsonFilePath);

            // Checking if the InputStream is null, indicating that the JSON file was not found.
            if (inputStream == null) {
                throw new IllegalArgumentException("JSON file not found: " + jsonFilePath);
            }

            // Creating an ObjectMapper instance to map JSON data to the LevelDetails class.
            ObjectMapper objectMapper = new ObjectMapper();

            // Reading and mapping the JSON data to a LevelDetails object.
            return objectMapper.readValue(inputStream, LevelDetails.class);
        } catch (IOException e) {
            // Throwing a RuntimeException if there is an error loading level details from JSON.
            throw new RuntimeException("Error loading level details from JSON", e);
        }
    }


    /**
     * Gets the file path to the tile sheet used in the level.
     *
     * @return The tile sheet file path.
     */
    public String getTileSheetPath() {
        return tileSheetPath;
    }

    /**
     * Gets the original width of each tile in the level.
     *
     * @return The original tile width.
     */
    public int getOriginalTileWidth() {
        return originalTileWidth;
    }

    /**
     * Gets the original height of each tile in the level.
     *
     * @return The original tile height.
     */
    public int getOriginalTileHeight() {
        return originalTileHeight;
    }

    /**
     * Gets the width at which tiles are displayed in the level.
     *
     * @return The display tile width.
     */
    public int getDisplayTileWidth() {
        return displayTileWidth;
    }

    /**
     * Gets the height at which tiles are displayed in the level.
     *
     * @return The display tile height.
     */
    public int getDisplayTileHeight() {
        return displayTileHeight;
    }

    /**
     * Gets the two-dimensional array representing the map of the level.
     *
     * @return The level map.
     */
    public String[][] getMap() {
        return map;
    }
}