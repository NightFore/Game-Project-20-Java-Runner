package com.example.gameproject20javarunner.entity;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Heart {
    // Instance Attributes
    private final ImageView fullHeart;
    private final ImageView halfHeart;
    private final ImageView emptyHeart;

    // Constants
    private static final double DISPLAY_WIDTH = 32.0;
    private static final double DISPLAY_HEIGHT = 32.0;
    private static final String HEART_SPRITE_SHEET_PATH = "/img/NicoleMarieT_Heart_Sprite_Sheet_32x32.png";

    // Constructor taking the position and the initial heart state
    public Heart(double x, double y, int initialState) {
        // Load heart sprite sheet image from resources
        Image heartSpriteSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(HEART_SPRITE_SHEET_PATH)));

        // Create ImageView for each heart state
        fullHeart = createHeartImageView(heartSpriteSheet, 0, x, y);
        halfHeart = createHeartImageView(heartSpriteSheet, 1, x, y);
        emptyHeart = createHeartImageView(heartSpriteSheet, 2, x, y);

        // Set the initial heart state
        setHeartState(initialState);
    }

    // Method to create an ImageView for a specific heart state
    private ImageView createHeartImageView(Image spriteSheet, int state, double x, double y) {
        double frameX = state * DISPLAY_WIDTH;
        double frameY = 0;

        // Create ImageView with the specified state
        ImageView imageView = new ImageView(spriteSheet);
        imageView.setFitWidth(DISPLAY_WIDTH);
        imageView.setFitHeight(DISPLAY_HEIGHT);
        Rectangle2D frameRectangle = new Rectangle2D(frameX, frameY, DISPLAY_WIDTH, DISPLAY_HEIGHT);
        imageView.setViewport(frameRectangle);

        // Set the position of the heart
        imageView.setX(x);
        imageView.setY(y);

        // Return the created ImageView
        return imageView;
    }

    // Method to set the current heart state
    public void setHeartState(int state) {
        // Set visibility based on the current state
        fullHeart.setVisible(state == 0);
        halfHeart.setVisible(state == 1);
        emptyHeart.setVisible(state == 2);
    }

    // Getter for the width
    public static double getDisplayWidth() {
        return DISPLAY_WIDTH;
    }

    // Getter for the height
    public static double getDisplayHeight() {
        return DISPLAY_HEIGHT;
    }

    // Getter for the full heart ImageView
    public ImageView getFullHeart() {
        return fullHeart;
    }

    // Getter for the half heart ImageView
    public ImageView getHalfHeart() {
        return halfHeart;
    }

    // Getter for the empty heart ImageView
    public ImageView getEmptyHeart() {
        return emptyHeart;
    }
}
