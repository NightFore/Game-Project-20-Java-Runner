// LevelLoader.java

package com.example.gameproject20javarunner.level;

import com.example.gameproject20javarunner.map.TileMap;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class LevelLoader {
    /**
     * Loads a level from a JSON file and constructs the corresponding TileMap.
     *
     * @param filePath The path to the JSON file.
     * @param root     The root pane where elements are added.
     * @param camera   The camera used for positioning.
     */
    public void loadLevel(String filePath, Pane root, Camera camera) {
        // Attempt to load level details from the provided JSON file path.
        LevelDetails levelDetails = LevelDetails.loadFromJson(filePath);

        // Check if level details were successfully loaded.
        if (levelDetails != null) {
            // If successful, construct the TileMap using the loaded details.
            constructTileMap(levelDetails, root, camera);
        } else {
            // If unsuccessful, print an error message.
            System.out.println("Failed to load level. Please check the JSON file: " + filePath);
        }
    }

    /**
     * Constructs a TileMap based on the provided level details and adds it to the specified root pane.
     *
     * @param levelDetails       The details of the loaded level, including tile sheet, dimensions, and map.
     * @param root               The root pane where TileMap elements will be added.
     * @param camera             The camera used for positioning within the level.
     */
    private void constructTileMap(LevelDetails levelDetails, Pane root, Camera camera) {
        // Extracting necessary details from the loaded level.
        String tileSheetPath = levelDetails.getTileSheetPath();
        int originalTileWidth = levelDetails.getOriginalTileWidth();
        int originalTileHeight = levelDetails.getOriginalTileHeight();
        int displayTileWidth = levelDetails.getDisplayTileWidth();
        int displayTileHeight = levelDetails.getDisplayTileHeight();
        String[][] map = levelDetails.getMap();

        // Creating a new TileMap instance using the extracted details.
        TileMap tileMap = new TileMap(camera, root, tileSheetPath, map, originalTileWidth, originalTileHeight, displayTileWidth, displayTileHeight);

        // Adding the constructed TileMap to the specified root pane.
        tileMap.addToRoot();
    }
}