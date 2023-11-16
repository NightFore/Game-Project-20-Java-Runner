// GameScene.java

package com.example.gameproject20javarunner;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    private final Camera camera;
    private final StaticThing backgroundLeft;
    private final StaticThing backgroundRight;
    private final Hero hero;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        // Instantiate static elements to represent the background (one on the left and one on the right)
        backgroundLeft = new StaticThing(800, 600, "/img/desert.png");
        backgroundRight = new StaticThing(800, 600, "/img/desert.png");

        // Add the ImageViews of static elements to the main container
        root.getChildren().addAll(backgroundLeft.getImageView(), backgroundRight.getImageView());

        // Initial number of lives
        int numberOfLives = 3;

        // Instantiate hearts to indicate the initial number of lives
        Heart[] hearts = new Heart[numberOfLives];
        for (int i = 0; i < numberOfLives; i++) {
            double heartX = 10 + i * Heart.getSize();
            double heartY = 10;

            // Instantiate a heart and add it to the main container
            hearts[i] = new Heart(heartX, heartY);
            root.getChildren().add(hearts[i].getImageView());
        }

        // Instantiate the hero and add its ImageView to the main container
        hero = new Hero(100, 300);
        root.getChildren().add(hero.getImageView());
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

        // Adjust the position of the ImageViews of static elements based on the camera
        backgroundLeft.getImageView().setX(cameraX - getWidth() / 2);
        backgroundLeft.getImageView().setY(cameraY);

        backgroundRight.getImageView().setX(cameraX + getWidth() / 2);
        backgroundRight.getImageView().setY(cameraY);

        // Render the hero
        hero.render();
    }
}
