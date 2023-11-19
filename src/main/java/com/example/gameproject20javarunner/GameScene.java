// GameScene.java

package com.example.gameproject20javarunner;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    // Instances
    private final Camera camera;
    private StaticThing backgroundLeft;
    private StaticThing backgroundRight;
    private Hero hero;

    // Constants
    private static final String BACKGROUND_IMAGE_PATH = "/img/desert.png";
    private static final double BACKGROUND_WIDTH = 800;
    private static final double BACKGROUND_HEIGHT = 600;
    private static final double INITIAL_HERO_X = 0;
    private static final double INITIAL_HERO_Y = 425;
    private static final int NUMBER_OF_LIVES = 3;
    private static final double HEART_START_X = 10;
    private static final double HEART_START_Y = 10;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        initializeBackground(root);
        initializeHearts(root);
        initializeHero(root);
    }

    // Separate method to initialize the background
    private void initializeBackground(Pane root) {
        // Instantiate static elements to represent the background (one on the left and one on the right)
        backgroundLeft = new StaticThing(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BACKGROUND_IMAGE_PATH);
        backgroundRight = new StaticThing(BACKGROUND_WIDTH, BACKGROUND_HEIGHT, BACKGROUND_IMAGE_PATH);

        // Add the ImageViews of static elements to the main container
        root.getChildren().addAll(backgroundLeft.getImageView(), backgroundRight.getImageView());
    }

    // Separate method to initialize hearts
    private void initializeHearts(Pane root) {
        // Instantiate hearts to indicate the initial number of lives
        Heart[] hearts = new Heart[NUMBER_OF_LIVES];
        for (int i = 0; i < NUMBER_OF_LIVES; i++) {
            // Instantiate a heart and add it to the main container
            hearts[i] = new Heart(HEART_START_X + i * Heart.getSize(), HEART_START_Y, 0);
            root.getChildren().addAll(hearts[i].getFullHeart(), hearts[i].getHalfHeart(), hearts[i].getEmptyHeart());
        }

        // Test the different heart states: Full, Half, Empty
        hearts[0].setHeartState(0); // Full heart
        hearts[1].setHeartState(1); // Half heart
        hearts[2].setHeartState(2); // Empty heart
    }

    // Separate method to initialize the hero
    private void initializeHero(Pane root) {
        // Instantiate the hero and add its ImageView to the main container
        hero = new Hero(INITIAL_HERO_X, INITIAL_HERO_Y);
        root.getChildren().add(hero.getImageView());

        // Add the click listener for the hero's jump
        setOnMouseClicked(event -> {
            hero.jump();
        });
    }

    // Rendering method to adjust the position of elements based on the camera
    public void render(double deltaTime) {
        // Get the camera coordinates
        double cameraX = camera.getX();
        double cameraY = camera.getY();

        // Default direction
        double heroDirection = 1.0;

        // Move the hero based on the speed and direction
        hero.move(heroDirection, deltaTime);

        // Move the camera using physics equations
        camera.update(deltaTime, hero.getX());

        // Adjust the position of the ImageViews of static elements based on the camera
        backgroundLeft.getImageView().setX(cameraX - getWidth() / 2);
        backgroundLeft.getImageView().setY(cameraY);

        backgroundRight.getImageView().setX(cameraX + getWidth() / 2);
        backgroundRight.getImageView().setY(cameraY);

        // Render the hero at the camera position
        hero.draw(camera);
        hero.render(deltaTime);
    }
}
