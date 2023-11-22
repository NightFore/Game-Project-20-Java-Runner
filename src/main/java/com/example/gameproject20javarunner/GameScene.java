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
    private final HeroManager heroManager;
    private final FoeManager foeManager;
    private List<Foe> foes;

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
        heroManager = new HeroManager(root);
        foeManager = new FoeManager(root);

        // Add the click listener for the hero's jump
        setOnMouseClicked(event -> heroManager.jump());
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

    // Rendering method to adjust the position of elements based on the camera
    public void render(double deltaTime) {
        // Default direction
        double heroDirection = 1.0;
        double foeDirection = -1.0;

        // Move the hero based on the direction
        heroManager.move(heroDirection, deltaTime);

        // Move the foes based on the direction
        foeManager.moveFoes(foeDirection, deltaTime);

        // Move the camera using physics equations
        camera.update(deltaTime, heroManager.getX());

        background.update();
        heartManager.update();
        heroManager.update(deltaTime);
        foeManager.update(deltaTime);
        background.draw(camera);
        heartManager.draw();
        heroManager.draw(camera);
        foeManager.draw(camera);
    }
}
