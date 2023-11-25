// BackgroundManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.model.StaticThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.List;

/**
 * A manager class responsible for handling the background in the game.
 */
public class BackgroundManager {
    // Game Attributes
    private final Camera camera;

    // StaticThing Attributes
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final String IMAGE_PATH = "/img/desert.png";

    // BackgroundManager Attributes
    private final StaticThing backgroundLeft;
    private final StaticThing backgroundRight;

    /**
     * Constructs a BackgroundManager with the specified camera and root pane.
     *
     * @param camera The camera used for positioning.
     * @param root   The root pane where the elements are added.
     */
    public BackgroundManager(Camera camera, Pane root) {
        // Set Game attributes
        this.camera = camera;

        // Initialize backgrounds
        backgroundLeft = new StaticThing(camera, root, -WIDTH / 2, 0, WIDTH, HEIGHT, IMAGE_PATH);
        backgroundRight = new StaticThing(camera, root, WIDTH / 2, 0, WIDTH, HEIGHT, IMAGE_PATH);
    }

    /**
     * Getter for the left background ImageView.
     *
     * @return The ImageView associated with the left background.
     */
    public ImageView getBackgroundLeft() {
        return backgroundLeft.getImageView();
    }

    /**
     * Getter for the right background ImageView.
     *
     * @return The ImageView associated with the right background.
     */
    public ImageView getBackgroundRight() {
        return backgroundRight.getImageView();
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
     * Draw method to update the position of background elements based on the camera.
     */
    public void draw() {
        for (StaticThing background : List.of(backgroundLeft, backgroundRight)) {
            background.getImageView().setX(background.getX() - camera.getX());
            background.getImageView().setY(camera.getY());
        }
    }
}
