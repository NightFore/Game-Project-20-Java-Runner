// LevelLoader.java
package com.example.gameproject20javarunner.level;

import com.example.gameproject20javarunner.map.TileMap;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class LevelLoader {
    public static void loadLevel(String filePath, Pane root, Camera camera) {
        LevelDetails levelDetails = LevelDetails.loadFromJson(filePath);

        if (levelDetails != null) {
            String tileSheetPath = levelDetails.getTileSheetPath();
            int originalTileWidth = levelDetails.getOriginalTileWidth();
            int originalTileHeight = levelDetails.getOriginalTileHeight();
            int displayTileWidth = levelDetails.getDisplayTileWidth();
            int displayTileHeight = levelDetails.getDisplayTileHeight();
            String[][] map = levelDetails.getMap();

            TileMap tileMap = new TileMap(camera, root, tileSheetPath, map, originalTileWidth, originalTileHeight, displayTileWidth, displayTileHeight);
            tileMap.addToRoot();
        } else {
            System.out.println("Failed to load level.");
        }
    }
}