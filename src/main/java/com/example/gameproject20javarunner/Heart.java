// Heart.java

package com.example.gameproject20javarunner;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

public class Heart {
    // Instances
    private final ImageView imageView;

    // Constants
    private static final String HEART_SPRITE_SHEET_PATH = "/img/NicoleMarieT_Heart_Sprite_Sheet_32x32.png";
    private static final double SIZE = 32.0;
    private static final double FULL_HEART_FRAME_X = 0;
    private static final double FULL_HEART_FRAME_Y = 0;

    // Constructor taking the position
    public Heart(double x, double y) {
        // Load heart sprite sheet image from resources
        Image heartSpriteSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream(HEART_SPRITE_SHEET_PATH)));

        // Create an ImageView with the heart sprite sheet
        imageView = new ImageView(heartSpriteSheet);

        // Set the size of the ImageView
        imageView.setFitWidth(SIZE);
        imageView.setFitHeight(SIZE);

        // Define the rectangle for the full heart frame on the sprite sheet
        Rectangle2D fullHeartFrame = new Rectangle2D(FULL_HEART_FRAME_X, FULL_HEART_FRAME_Y, SIZE, SIZE);

        // Set the viewport of the ImageView to display the full heart frame
        imageView.setViewport(fullHeartFrame);

        // Position the heart
        imageView.setX(x);
        imageView.setY(y);
    }

    // Getter for the heart's ImageView
    public ImageView getImageView() {
        return imageView;
    }

    // Getter for the SIZE constant
    public static double getSize() {
        return SIZE;
    }
}
