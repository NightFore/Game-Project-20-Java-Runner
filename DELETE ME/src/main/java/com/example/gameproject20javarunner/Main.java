package com.example.gameproject20javarunner;

import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Game Project");

        // Create a group (Group) to hold scene elements.
        Group root = new Group();
        // Create a pane (Pane) using the group as the root.
        Pane pane = new Pane(root);

        // Load the background image and set the view.
        Image backgroundImage = new Image("file:img/desert.png");
        ImageView background = new ImageView(backgroundImage);
        background.setFitWidth(600); // Width of the scene
        background.setFitHeight(400); // Height of the scene

        // Load the hero (sprite) image and set the view.
        Image spriteSheet = new Image("file:img/heros.png");
        ImageView hero = new ImageView(spriteSheet);
        hero.setViewport(new Rectangle2D(20, 0, 65, 100));
        hero.setX(200); // Hero's X position
        hero.setY(300); // Hero's Y position

        // Add the background and hero to the group.
        root.getChildren().addAll(background, hero);

        // Create the scene and set its dimensions.
        Scene theScene = new Scene(pane, 600, 400, true);

        // Configure the scene using the root view of the pane.
        primaryStage.setScene(theScene);
        primaryStage.show();
    }
}
