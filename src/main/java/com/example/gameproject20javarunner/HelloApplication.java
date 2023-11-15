// HelloApplication.java

package com.example.gameproject20javarunner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Configure the stage
        primaryStage.setTitle("[Game Project 20] Java Runner");

        // Create an instance of Camera
        Camera camera = new Camera(0, 0);

        // Create the main container (Pane)
        Pane root = new Pane();

        // Create an instance of GameScene with the Camera, main container, and scene dimensions
        GameScene scene = new GameScene(camera, root, 800, 600);

        // Configure the stage with the scene
        primaryStage.setScene(scene);

        // Display the stage
        primaryStage.show();

        // Use an AnimationTimer to create a game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            @Override
            public void handle(long now) {
                // Game logic update (call render, camera movement, etc.)
                scene.render();
                camera.move(-2, 0);  // Test: Move the camera each frame
            }
        };

        // Start the game loop
        gameLoop.start();
    }
}
