package com.example.gameproject20javarunner.map;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TileMap {
    private final Image tileSheet;
    private final int originalTileWidth;
    private final int originalTileHeight;
    private final int displayTileWidth;
    private final int displayTileHeight;
    private List<ImageView> tileList;
    private ImageView[][] tiles;

    public TileMap(String tileSheetFileName, String[][] map, int originalTileWidth, int originalTileHeight, int displayTileWidth, int displayTileHeight) {
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

                ImageView tileView = new ImageView(tileSheet);
                tileView.setViewport(new javafx.geometry.Rectangle2D(tileX, tileY, originalTileWidth, originalTileHeight));
                tileView.setFitWidth(displayTileWidth);
                tileView.setFitHeight(displayTileHeight);

                tileList.add(tileView);
            }
        }
    }

    private void createTiles(String[][] map) {
        int numRows = map.length;
        int numCols = map[0].length;

        tiles = new ImageView[numRows][numCols];

        int numColsInTileList = (int) (tileSheet.getWidth() / originalTileWidth);

        for (int row = 0; row < numRows; row++) {
            for (int col = 0; col < numCols; col++) {
                String tileCode = map[row][col];
                String[] indices = tileCode.split(",");
                int tileIndexX = Integer.parseInt(indices[0]);
                int tileIndexY = Integer.parseInt(indices[1]);
                int tileIndex = tileIndexY * numColsInTileList + tileIndexX;

                ImageView tileView = copyImageView(tileList.get(tileIndex));
                tileView.relocate(col * displayTileWidth, row * displayTileHeight);
                tiles[row][col] = tileView;
            }
        }
    }

    private ImageView copyImageView(ImageView original) {
        ImageView copy = new ImageView(original.getImage());
        copy.setViewport(original.getViewport());
        copy.setFitWidth(original.getFitWidth());
        copy.setFitHeight(original.getFitHeight());
        return copy;
    }

    public void addToPane(Pane pane) {
        for (ImageView[] row : tiles) {
            for (ImageView tile : row) {
                pane.getChildren().add(tile);
            }
        }
    }
}
