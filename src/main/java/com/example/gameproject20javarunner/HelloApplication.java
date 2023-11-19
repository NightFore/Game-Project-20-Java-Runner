// HelloApplication.java

package com.example.gameproject20javarunner;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    // Constants
    private static final String PROJECT_TITLE = "[Game Project 20] Java Runner";
    private static final int SCENE_WIDTH = 800;
    private static final int SCENE_HEIGHT = 600;
    private static final int CAMERA_INITIAL_X = 0;
    private static final int CAMERA_INITIAL_Y = 0;
    private static final double CAMERA_M = 1;
    private static final double CAMERA_K = 35;
    private static final double CAMERA_F = 10;

    public static void main(String[] args) {
        // Launch the JavaFX application
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Configure the stage
        primaryStage.setTitle(PROJECT_TITLE);

        // Create an instance of Camera
        Camera camera = new Camera(CAMERA_INITIAL_X, CAMERA_INITIAL_Y, CAMERA_M, CAMERA_K, CAMERA_F);

        // Create the main container (Pane)
        Pane root = new Pane();

        // Create an instance of GameScene with the Camera, main container, and scene dimensions
        GameScene scene = new GameScene(camera, root, SCENE_WIDTH, SCENE_HEIGHT);

        // Configure the stage with the scene
        primaryStage.setScene(scene);

        // Display the stage
        primaryStage.show();

        // Use an AnimationTimer to create a game loop
        AnimationTimer gameLoop = new AnimationTimer() {
            long prevTime = System.nanoTime(); // Initialize prevTime with the current time in nanoseconds

            @Override
            public void handle(long now) {
                // Calculate the deltaTime (time elapsed since the last frame)
                double deltaTime = (now - prevTime) / 1e9; // Convert nanoseconds to seconds

                // Game logic update
                scene.render(deltaTime);

                // Update the previous time for the next frame
                prevTime = now;
            }
        };

        // Start the game loop
        gameLoop.start();
    }
}
