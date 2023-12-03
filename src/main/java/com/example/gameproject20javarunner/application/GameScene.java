// GameScene.java

package com.example.gameproject20javarunner.application;

import com.example.gameproject20javarunner.entity.Player;
import com.example.gameproject20javarunner.level.Level;
import com.example.gameproject20javarunner.manager.AudioManager;
import com.example.gameproject20javarunner.manager.BackgroundManager;
import com.example.gameproject20javarunner.manager.FoeManager;
import com.example.gameproject20javarunner.manager.HeartManager;
import com.example.gameproject20javarunner.map.TileMap;
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
    private final Player player;
    private final AudioManager audioManager;
    private final Level currentLevel;
    private final TileMap tileMap;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double sceneWidth, double sceneHeight) {
        super(root, sceneWidth, sceneHeight);
        this.camera = camera;
        backgroundManager = new BackgroundManager(camera, root, sceneWidth, sceneHeight);

        // Load the current level using the Level class
        currentLevel = new Level();
        currentLevel.loadLevel(camera, root, backgroundManager, "/leveldata/level1.json");

        tileMap = currentLevel.getTileMap();

        heartManager = new HeartManager(camera, root);
        foeManager = new FoeManager(camera, root);
        player = new Player(camera, root, tileMap);

        setOnMouseClicked(event -> handleClickPress(event.getButton()));
        setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        setOnKeyReleased(event -> handleKeyRelease(event.getCode()));

        // AudioManager (Test)
        audioManager = new AudioManager();
        audioManager.loadAudio("Kuru_Kuru_Kururin", "/music/Raphiiel_Herta_Kuru_Kuru_Kururin.mp3");
        audioManager.loadAudio("Field_of_Memories", "/music/Waterflame_music_Field_of_Memories.mp3");
        audioManager.loadAudio("Glorious_Morning", "/music/Waterflame_music_Glorious_Morning.mp3");
    }

    // Method to handle click events
    private void handleClickPress(MouseButton button) {
        switch (button) {
            case PRIMARY -> {} // Left-click
            case SECONDARY -> {} // Right-click
        }
    }

    // Method to handle key press events
    private void handleKeyPress(KeyCode code) {
        switch (code) {
            case LEFT, A -> player.setDirectionX(-1);
            case RIGHT, D -> player.setDirectionX(1);
            case UP, W, SPACE -> player.jump();
            case I -> audioManager.playMusic("Kuru_Kuru_Kururin");
            case O -> audioManager.playMusic("Field_of_Memories");
            case P -> audioManager.playMusic("Glorious_Morning");
        }
    }

    // Method to handle key release events
    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case LEFT, RIGHT, A, D -> player.setDirectionX(0);
        }
    }

    // Method to update the scene elements
    public void update(double deltaTime) {
        // Move the camera using physics equations
        camera.update(deltaTime, player.getX());

        backgroundManager.update();
        player.update(deltaTime);
        foeManager.update(deltaTime);

    }

    // Method to draw the scene elements
    public void draw() {
        backgroundManager.draw();
        player.draw();
        foeManager.draw();
        currentLevel.draw();
    }
}
