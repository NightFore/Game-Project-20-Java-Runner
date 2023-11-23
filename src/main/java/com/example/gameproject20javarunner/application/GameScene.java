// GameScene.java

package com.example.gameproject20javarunner.application;

import com.example.gameproject20javarunner.manager.BackgroundManager;
import com.example.gameproject20javarunner.manager.FoeManager;
import com.example.gameproject20javarunner.manager.HeartManager;
import com.example.gameproject20javarunner.manager.HeroManager;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;

public class GameScene extends Scene {
    // Instances
    private final Camera camera;
    private final BackgroundManager backgroundManager;
    private final HeartManager heartManager;
    private final HeroManager heroManager;
    private final FoeManager foeManager;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        backgroundManager = new BackgroundManager(root);
        heartManager = new HeartManager(root);
        heroManager = new HeroManager(root);
        foeManager = new FoeManager(root);

        // Add the click listener for the hero's jump
        setOnMouseClicked(event -> heroManager.jump());
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

        backgroundManager.update();
        heartManager.update();
        heroManager.update(deltaTime);
        foeManager.update();
        backgroundManager.draw(camera);
        heartManager.draw();
        heroManager.draw(camera);
        foeManager.draw(camera);
    }
}
