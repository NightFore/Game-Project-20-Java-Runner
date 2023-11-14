package com.example.gameproject20javarunner;

import javafx.scene.Scene;
import javafx.scene.layout.StackPane;

public class GameScene extends Scene {
    private Camera camera;
    private StaticThing backgroundLeft;
    private StaticThing backgroundRight;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, StackPane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        // Instantiate StaticThings to represent the background (one on the left and one on the right)
        backgroundLeft = new StaticThing(800, 600, "/img/desert.png");
        backgroundRight = new StaticThing(800, 600, "/img/desert.png");

        // Add the ImageViews of StaticThings to the main container
        root.getChildren().addAll(backgroundLeft.getImageView(), backgroundRight.getImageView());
    }

    // Getter for the camera
    public Camera getGameCamera() {
        return camera;
    }

    // Rendering method to adjust the position of elements based on the camera
    public void render() {
        // Get the camera coordinates
        double cameraX = camera.getX();
        double cameraY = camera.getY();

        // Adjust the position of the ImageViews of StaticThings based on the camera
        backgroundLeft.getImageView().setTranslateX(cameraX - getWidth() / 2);
        backgroundLeft.getImageView().setTranslateY(cameraY);

        backgroundRight.getImageView().setTranslateX(cameraX + getWidth() / 2);
        backgroundRight.getImageView().setTranslateY(cameraY);
    }
}
