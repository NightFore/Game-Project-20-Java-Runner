// TileMap.java

package com.example.gameproject20javarunner.map;

import com.example.gameproject20javarunner.model.Thing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileMap {
    // Game Attributes
    private final Camera camera;
    private final Pane root;

    // Tile Sheet Attributes
    private final Image tileSheet;
    private final int originalTileWidth;
    private final int originalTileHeight;
    private final int displayTileWidth;
    private final int displayTileHeight;
    private List<Thing> tileList;

    // Map Attributes
    private int numRows;
    private int numCols;
    private Thing[][] tiles;

    /**
     * Constructs a TileMap with the specified camera, root, tile sheet file name, map, original tile dimensions, and display tile dimensions.
     *
     * @param camera              The camera used for positioning.
     * @param root                The root pane where the elements are added.
     * @param tileSheetFileName   The file name of the tile sheet image.
     * @param map                 The map represented as a 2D array of tile codes.
     * @param originalTileWidth   The original width of a tile in the tile sheet.
     * @param originalTileHeight  The original height of a tile in the tile sheet.
     * @param displayTileWidth    The display width of a tile in the game.
     * @param displayTileHeight   The display height of a tile in the game.
     */
    public TileMap(Camera camera, Pane root, String tileSheetFileName, String[][] map, int originalTileWidth, int originalTileHeight, int displayTileWidth, int displayTileHeight) {
        // Set Game attributes
        this.camera = camera;
        this.root = root;

        // Set Tile Sheet Attributes
        this.tileSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(tileSheetFileName)));
        this.originalTileWidth = originalTileWidth;
        this.originalTileHeight = originalTileHeight;
        this.displayTileWidth = displayTileWidth;
        this.displayTileHeight = displayTileHeight;

        // Initialize the tile map
        createTileList();
        createTiles(map);
    }

    /**
     * Create the list of Thing objects representing individual tiles.
     */
    private void createTileList() {
        tileList = new ArrayList<>();

        int numCols = (int) (tileSheet.getWidth() / originalTileWidth);
        int numRows = (int) (tileSheet.getHeight() / originalTileHeight);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                double tileX = col * originalTileWidth;
                double tileY = row * originalTileHeight;

                // Create a Thing for each tile and add it to the tileList
                Thing tileThing = new Thing(camera, root, 0, 0, displayTileWidth, displayTileHeight, tileSheet);
                tileThing.setViewport(tileX, tileY, originalTileWidth, originalTileHeight);
                tileList.add(tileThing);
            }
        }
    }

    /**
     * Create the 2D array of Thing objects representing the map using the given tile codes.
     *
     * @param map The 2D array of tile codes.
     */
    private void createTiles(String[][] map) {
        numRows = map.length;
        numCols = map[0].length;
        tiles = new Thing[numRows][numCols];

        int numColsInTileList = (int) (tileSheet.getWidth() / originalTileWidth);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String tileCode = map[row][col];

                // Check if the tile is not empty
                if (!tileCode.equals("000")) {
                    String[] indices = tileCode.split(",");
                    int tileIndexX = Integer.parseInt(indices[0]);
                    int tileIndexY = Integer.parseInt(indices[1]);
                    int tileIndex = tileIndexY * numColsInTileList + tileIndexX;

                    // Create a copy of the Thing from tileList and set its position
                    Thing tileThing = tileList.get(tileIndex).copy();
                    tileThing.setInitialPosition(col * displayTileWidth, row * displayTileHeight);
                    tiles[row][col] = tileThing;
                } else {
                    // Empty tile logic, set the tile as null
                    tiles[row][col] = null;
                }
            }
        }
    }


    /**
     * Add all Thing objects to the root.
     */
    public void addToRoot() {
        for (Thing[] row : tiles) {
            for (Thing tile : row) {
                // Check if the tile is not null before adding it to the root
                if (tile != null) {
                    root.getChildren().add(tile.getImageView());
                }
            }
        }
    }

    /**
     * Remove all Thing objects from the root.
     */
    public void removeFromRoot() {
        for (Thing[] row : tiles) {
            for (Thing tile : row) {
                // Check if the tile is not null before removing it from the root
                if (tile != null) {
                    tile.removeFromRoot();
                }
            }
        }
    }

    /**
     * Get the number of rows in the TileMap.
     *
     * @return The number of rows.
     */
    public int getNumRows() {
        return numRows;
    }

    /**
     * Get the number of columns in the TileMap.
     *
     * @return The number of columns.
     */
    public int getNumCols() {
        return numCols;
    }

    /**
     * Get the display width of a tile in the TileMap.
     *
     * @return The display width of a tile.
     */
    public double getDisplayTileWidth() {
        return displayTileWidth;
    }

    /**
     * Get the display height of a tile in the TileMap.
     *
     * @return The display height of a tile.
     */
    public double getDisplayTileHeight() {
        return displayTileHeight;
    }

    /**
     * Get the tile at the specified row and column in the TileMap.
     *
     * @param row    The row index.
     * @param column The column index.
     * @return The Thing representing the tile at the specified position.
     */
    public Thing getTile(int row, int column) {
        if (row >= 0 && row < numRows && column >= 0 && column < numCols) {
            return tiles[row][column];
        } else {
            return null;
        }
    }

    /**
     * Update the positions of all Thing objects based on the camera.
     */
    public void draw() {
        for (Thing[] row : tiles) {
            for (Thing tile : row) {
                // Check if the tile is not null before updating its position
                if (tile != null) {
                    tile.draw();
                }
            }
        }
    }
}
