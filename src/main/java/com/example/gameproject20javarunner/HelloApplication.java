package com.example.gameproject20javarunner;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Objects;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        // Configuration of the stage
        primaryStage.setTitle("JavaFX Game Runner");

        // Create an instance of Camera
        Camera camera = new Camera(0, 0);

        // Create the main container (StackPane)
        StackPane root = createRootPane();

        // Create an instance of GameScene with the Camera, main container, and scene dimensions
        GameScene scene = new GameScene(camera, root, 800, 600);

        // Configuration of the stage with the scene
        primaryStage.setScene(scene);

        // Test: Print the camera coordinates using getGameCamera()
        System.out.println(scene.getGameCamera());

        // Display the stage
        primaryStage.show();
    }

    private StackPane createRootPane() {
        // Creation of the main container (StackPane)
        StackPane root = new StackPane();

        // Load the background image from resources
        Image backgroundImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/desert.png")));

        // Create an image view with the background image
        ImageView backgroundImageView = new ImageView(backgroundImage);

        // Add the ImageView to the main container
        root.getChildren().add(backgroundImageView);

        // Load the sprite sheet from resources
        Image spriteSheet = new Image(Objects.requireNonNull(getClass().getResourceAsStream("/img/heros.png")));

        // Create an ImageView with the loaded sprite sheet
        ImageView sprite = createImageView(spriteSheet);

        // Add the hero's ImageView to the main container
        root.getChildren().add(sprite);

        return root;
    }

    private ImageView createImageView(Image spriteSheet) {
        // Create an ImageView with the loaded sprite sheet
        ImageView sprite = new ImageView(spriteSheet);

        // Set the viewport with the specified values
        sprite.setViewport(new Rectangle2D(20, 0, 65, 100));

        // Set the position where the image will be displayed on the screen
        sprite.setX(200);
        sprite.setY(300);

        return sprite;
    }
}
