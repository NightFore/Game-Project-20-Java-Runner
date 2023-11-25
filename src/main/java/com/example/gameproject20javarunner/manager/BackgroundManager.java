// BackgroundManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.view.Background;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class BackgroundManager {
    // Game Attributes
    private final Camera camera;
    private final Pane root;

    // StaticThing Attributes
    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final String IMAGE_PATH = "/img/desert.png";

    // BackgroundManager Attributes
    private final Background backgroundLeft;
    private final Background backgroundRight;

    public BackgroundManager(Camera camera, Pane root) {
        backgroundLeft = new Background(-WIDTH / 2, 0, WIDTH, HEIGHT, IMAGE_PATH);
        backgroundRight = new Background(WIDTH / 2, 0, WIDTH, HEIGHT, IMAGE_PATH);
        root.getChildren().addAll(getBackgroundLeft(), getBackgroundRight());

        this.camera = camera;
        this.root = root;
    }

    public ImageView getBackgroundLeft() {
        return backgroundLeft.getImageView();
    }

    public ImageView getBackgroundRight() {
        return backgroundRight.getImageView();
    }

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

    public void draw() {
        backgroundLeft.getImageView().setX(backgroundLeft.getX() - camera.getX());
        backgroundLeft.getImageView().setY(camera.getY());

        backgroundRight.getImageView().setX(backgroundRight.getX() - camera.getX());
        backgroundRight.getImageView().setY(camera.getY());
    }
}
