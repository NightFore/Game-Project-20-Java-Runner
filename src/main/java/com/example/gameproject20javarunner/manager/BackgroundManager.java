// BackgroundManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.model.StaticThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * A manager class responsible for handling the background in the game.
 */
public class BackgroundManager {
    // Game Attributes
    private final Camera camera;

    // StaticThing Attributes
    private final double sceneWidth;
    private final double sceneHeight;

    // BackgroundManager Attributes
    private final StaticThing backgroundLeft;
    private final StaticThing backgroundRight;

    /**
     * Constructs a BackgroundManager with the specified camera, root pane, scene width, and scene height.
     *
     * @param camera      The camera used for positioning.
     * @param root        The root pane where the elements are added.
     * @param sceneWidth  The width of the game scene.
     * @param sceneHeight The height of the game scene.
     */
    public BackgroundManager(Camera camera, Pane root, double sceneWidth, double sceneHeight) {
        // Set Game Attributes
        this.camera = camera;

        // Set StaticThing Attributes
        this.sceneWidth = sceneWidth;
        this.sceneHeight = sceneHeight;

        // Initialize Background Attributes
        backgroundLeft = new StaticThing(camera, root, -sceneWidth / 2, 0, sceneWidth, sceneHeight, "");
        backgroundRight = new StaticThing(camera, root, sceneWidth / 2, 0, sceneWidth, sceneHeight, "");
    }

    /**
     * Update method to handle background movement and looping effect.
     */
    public void update() {
        // Check if the camera is near the left edge of the backgroundLeft
        if (backgroundLeft.getX() > camera.getX()) {
            // Move background to the left to create a looping effect
            backgroundLeft.setX(backgroundLeft.getX() - backgroundLeft.getDisplayWidth());
            backgroundRight.setX(backgroundRight.getX() - backgroundRight.getDisplayWidth());
        }

        // Check if the camera is near the right edge of the backgroundRight
        if (backgroundRight.getX() < camera.getX()) {
            // Move background to the right to create a looping effect
            backgroundLeft.setX(backgroundLeft.getX() + backgroundLeft.getDisplayWidth());
            backgroundRight.setX(backgroundRight.getX() + backgroundRight.getDisplayWidth());
        }
    }

    /**
     * Set the background image dynamically.
     *
     * @param imagePath The path to the new background image.
     */
    public void setBackgroundImage(String imagePath) {
        // Set backgrounds
        backgroundLeft.setImage(imagePath);
        backgroundRight.setImage(imagePath);

        // Update the dimensions of the background images
        updateBackgroundSize();
    }

    /**
     * Updates the dimensions of the background images to fit the current scene dimensions while maintaining aspect ratio.
     */
    public void updateBackgroundSize() {
        // Get the dimensions of the current background image
        double imageWidth = backgroundLeft.getDisplayWidth();
        double imageHeight = backgroundLeft.getDisplayHeight();

        // Calculate the scaling factors for width and height
        double widthScale = sceneWidth / imageWidth;
        double heightScale = sceneHeight / imageHeight;

        // Calculate the new dimensions while maintaining aspect ratio
        double newWidth, newHeight;
        if (widthScale > heightScale) {
            // If the width scale is larger, adjust the width and calculate the corresponding height
            newWidth = sceneWidth;
            newHeight = imageHeight * widthScale;
        } else {
            // If the height scale is larger or equal, adjust the height and calculate the corresponding width
            newWidth = imageWidth * heightScale;
            newHeight = sceneHeight;
        }

        // Set the new dimensions for the background images
        backgroundLeft.setDisplaySize(newWidth, newHeight);
        backgroundRight.setDisplaySize(newWidth, newHeight);
    }

    /**
     * Draw method to update the position of background elements based on the camera.
     */
    public void draw() {
        for (StaticThing background : List.of(backgroundLeft, backgroundRight)) {
            background.getImageView().setX(background.getX() - camera.getX());
            background.getImageView().setY(camera.getY());
        }
    }
}
