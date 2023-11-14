package com.example.gameproject20javarunner;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameScene extends Scene {
    private final Camera camera;

    // Constructor taking a Camera and other necessary parameters
    public GameScene(Camera camera, StackPane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;
    }

    // Getter for the camera
    public Camera getGameCamera() {
        return camera;
    }
}
