// GameScene.java

package com.example.gameproject20javarunner.application;

import com.example.gameproject20javarunner.manager.BackgroundManager;
import com.example.gameproject20javarunner.manager.FoeManager;
import com.example.gameproject20javarunner.manager.HeartManager;
import com.example.gameproject20javarunner.entity.Hero;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    // Instances
    private final Camera camera;
    private final BackgroundManager backgroundManager;
    private final HeartManager heartManager;
    private final FoeManager foeManager;
    private final Hero hero;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        backgroundManager = new BackgroundManager(root);
        heartManager = new HeartManager(root);
        foeManager = new FoeManager(root);
        hero = new Hero(camera, root);

        setOnMouseClicked(event -> handleClickPress(event.getButton()));
        setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        setOnKeyReleased(event -> handleKeyRelease(event.getCode()));
    }

    // Method to handle click events
    private void handleClickPress(MouseButton button) {
        switch (button) {
            case PRIMARY -> hero.shootProjectile(); // Left-click
            case SECONDARY -> {} // Right-click
        }
    }

    // Method to handle key press events
    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case LEFT, A -> hero.setMoveLeft();
            case RIGHT, D -> hero.setMoveRight();
            case UP, W, SPACE -> hero.jump();
        }
    }

    // Method to handle key release events
    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case LEFT, RIGHT, A, D -> hero.setMoveStop();
        }
    }

    // Method to update the scene elements
    public void update(double deltaTime) {
        // Move the camera using physics equations
        camera.update(deltaTime, hero.getX());

        backgroundManager.update();
        heartManager.update();
        hero.update(deltaTime);
        foeManager.update(deltaTime);
    }

    // Method to draw the scene elements
    public void draw() {
        backgroundManager.draw(camera);
        heartManager.draw();
        hero.draw();
        foeManager.draw(camera);
    }
}
