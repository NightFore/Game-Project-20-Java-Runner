package com.example.gameproject20javarunner.map;

import com.example.gameproject20javarunner.model.Thing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileMap {
    // Game Attributes
    private final Camera camera;
    private final Pane root;
    private final Image tileSheet;
    private final int originalTileWidth;
    private final int originalTileHeight;
    private final int displayTileWidth;
    private final int displayTileHeight;
    private List<Thing> tileList;
    private Thing[][] tiles;

    public TileMap(Camera camera, Pane root, String tileSheetFileName, String[][] map, int originalTileWidth, int originalTileHeight, int displayTileWidth, int displayTileHeight) {
        // Set Game attributes
        this.camera = camera;
        this.root = root;

        this.tileSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tileSheetFileName)));
        this.originalTileWidth = originalTileWidth;
        this.originalTileHeight = originalTileHeight;
        this.displayTileWidth = displayTileWidth;
        this.displayTileHeight = displayTileHeight;

        createTileList();
        createTiles(map);
    }

    private void createTileList() {
        tileList = new ArrayList<>();

        int numCols = (int) (tileSheet.getWidth() / originalTileWidth);
        int numRows = (int) (tileSheet.getHeight() / originalTileHeight);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                double tileX = col * originalTileWidth;
                double tileY = row * originalTileHeight;

                Thing tileThing = new Thing(camera, root, 0, 0, displayTileWidth, displayTileHeight, tileSheet);
                tileThing.setViewport(tileX, tileY, originalTileWidth, originalTileHeight);
                tileList.add(tileThing);
            }
        }
    }

    private void createTiles(String[][] map) {
        int numRows = map.length;
        int numCols = map[0].length;

        tiles = new Thing[numRows][numCols];

        int numColsInTileList = (int) (tileSheet.getWidth() / originalTileWidth);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String tileCode = map[row][col];
                String[] indices = tileCode.split(",");
                int tileIndexX = Integer.parseInt(indices[0]);
                int tileIndexY = Integer.parseInt(indices[1]);
                int tileIndex = tileIndexY * numColsInTileList + tileIndexX;

                Thing tileThing = tileList.get(tileIndex).copy();
                tileThing.setInitialPosition(col * displayTileWidth, row * displayTileHeight);
                tiles[row][col] = tileThing;
            }
        }
    }

    public void addToPane() {
        for (Thing[] row : tiles) {
            for (Thing tile : row) {
                root.getChildren().add(tile.getImageView());
            }
        }
    }

    public void draw() {
        for (Thing[] row : tiles) {
            for (Thing tile : row) {
                tile.draw();
            }
        }
    }
}
