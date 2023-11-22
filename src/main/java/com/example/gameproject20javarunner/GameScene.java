// GameScene.java

package com.example.gameproject20javarunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.geometry.Rectangle2D;

public class GameScene extends Scene {
    // Instances
    private final Camera camera;
    private final Background background;
    private final HeartManager heartManager;
    private Hero hero;
    private List<Foe> foes;

    // Constants
    private static final double INITIAL_HERO_X = 0;
    private static final double INITIAL_HERO_Y = 425;

    // Constants (Foe)
    private static final int MIN_FOES = 2;
    private static final int MAX_FOES = 10;
    private static final float FOE_MIN_X = 500;
    private static final float FOE_MAX_X = 1000;
    private static final float FOE_MIN_Y = 400;
    private static final float FOE_MAX_Y = 450;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        background = new Background(root);
        heartManager = new HeartManager(root);
        initializeHero(root);
        initializeFoes(root);
    }

    // Separate method to initialize the foes
    private void initializeHero(Pane root) {
        // Instantiate the hero and add its ImageView to the main container
        hero = new Hero(INITIAL_HERO_X, INITIAL_HERO_Y);
        root.getChildren().add(hero.getImageView());

        // Add the click listener for the hero's jump
        setOnMouseClicked(event -> hero.jump());
    }

    // Separate method to initialize the hero
    private void initializeFoes(Pane root) {
        // Initialize the list of Foes
        foes = new ArrayList<>();

        // Add a single Foe at pixel 250
        foes.add(new Foe(250, 0));

        // Add the ImageViews of Foes to the main container
        for (Foe foe : foes) {
            root.getChildren().add(foe.getImageView());
        }
    }

    // Add a random number of Foes to the list
    private void addRandomFoes(Pane root) {
        Random random = new Random();
        int numberOfFoes = random.nextInt(MAX_FOES - MIN_FOES + 1) + MIN_FOES;

        for (int i = 0; i < numberOfFoes; i++) {
            double x = random.nextDouble() * (FOE_MAX_X - FOE_MIN_X) + FOE_MIN_X;
            double y = random.nextDouble() * (FOE_MAX_Y - FOE_MIN_Y) + FOE_MIN_Y;

            Foe foe = new Foe(x, y);
            foes.add(foe);
            root.getChildren().add(foe.getImageView());
        }
    }

    // Check for collisions between Hero and Foes
    private void checkCollisions() {
        if (!hero.isInvincible()) {
            Rectangle2D heroHitBox = hero.getHitBox();
            for (Foe foe : foes) {
                Rectangle2D foeHitBox = foe.getHitBox();
                if (heroHitBox.intersects(foeHitBox)) {
                    // Collision detected
                    handleCollision();
                    break;  // Assuming only one collision at a time
                }
            }
        }
    }

    private void handleCollision() {
        // Add logic for handling collisions, e.g., decrease lives, reset positions, etc.
        hero.setInvincible(true);
    }

    // Rendering method to adjust the position of elements based on the camera
    public void render(double deltaTime) {
        // Default direction
        double heroDirection = 1.0;
        double foeDirection = -1.0;

        // Move the hero based on the direction
        hero.move(heroDirection, deltaTime);

        // Move the foes based on the direction
        for (Foe foe : foes) {
            foe.move(foeDirection, deltaTime);
        }

        // Move the camera using physics equations
        camera.update(deltaTime, hero.getX());

        background.update();
        background.draw(camera);

        // Render the hero at the camera position
        hero.draw(camera);
        hero.render(deltaTime);

        for (Foe foe : foes) {
            foe.draw(camera);
            foe.render(deltaTime);
        }

        // Check for collisions
        checkCollisions();
    }
}
