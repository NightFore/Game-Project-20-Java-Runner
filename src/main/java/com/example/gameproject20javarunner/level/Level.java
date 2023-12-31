// Level.java

package com.example.gameproject20javarunner.level;

import com.example.gameproject20javarunner.map.TileMap;
import com.example.gameproject20javarunner.view.Camera;
import com.example.gameproject20javarunner.manager.BackgroundManager;
import javafx.scene.layout.Pane;

public class Level {
    // Level Attributes
    private TileMap tileMap;

    /**
     * Loads a level from a JSON file and constructs the corresponding TileMap.
     *
     * @param camera             The camera used for positioning.
     * @param root               The root pane where elements are added.
     * @param backgroundManager  The background manager.
     * @param filePath           The path to the JSON file.
     */
    public void loadLevel(Camera camera, Pane root, BackgroundManager backgroundManager, String filePath) {
        // Attempt to load level details from the provided JSON file path.
        LevelDetails levelDetails = LevelDetails.loadFromJson(filePath);

        // Check if level details were successfully loaded.
        if (levelDetails != null) {
            // Construct the TileMap and store the reference
            tileMap = constructTileMap(camera, root, levelDetails);

            // Load and set the background image
            String backgroundImagePath = levelDetails.getBackgroundImagePath();
            if (backgroundImagePath != null) {
                backgroundManager.setBackgroundImage(backgroundImagePath);
            }
        } else {
            // If unsuccessful, print an error message.
            System.out.println("Failed to load level. Please check the JSON file: " + filePath);
        }
    }

    /**
     * Constructs a TileMap based on the provided level details and adds it to the specified root pane.
     *
     * @param camera       The camera used for positioning within the level.
     * @param root         The root pane where TileMap elements will be added.
     * @param levelDetails The details of the loaded level, including tile sheet, dimensions, and map.
     * @return The constructed TileMap instance.
     */
    private TileMap constructTileMap(Camera camera, Pane root, LevelDetails levelDetails) {
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

        // Return the TileMap instance
        return tileMap;
    }

    public TileMap getTileMap() {
        return tileMap;
    }

    /**
     * Draws the level elements, including the TileMap.
     */
    public void draw() {
        tileMap.draw();
    }
}
