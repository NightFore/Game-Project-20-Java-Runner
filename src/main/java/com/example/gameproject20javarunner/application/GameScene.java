// GameScene.java

package com.example.gameproject20javarunner.application;

import com.example.gameproject20javarunner.level.LevelLoader;
import com.example.gameproject20javarunner.manager.AudioManager;
import com.example.gameproject20javarunner.manager.BackgroundManager;
import com.example.gameproject20javarunner.manager.FoeManager;
import com.example.gameproject20javarunner.manager.HeartManager;
import com.example.gameproject20javarunner.entity.Hero;
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
    private final Hero hero;
    private final AudioManager audioManager;
    private TileMap tileMap;

    // Constructor taking the camera, the main container, and the dimensions of the scene
    public GameScene(Camera camera, Pane root, double width, double height) {
        super(root, width, height);
        this.camera = camera;

        backgroundManager = new BackgroundManager(camera, root);
        heartManager = new HeartManager(camera, root);
        foeManager = new FoeManager(camera, root);
        hero = new Hero(camera, root);

        setOnMouseClicked(event -> handleClickPress(event.getButton()));
        setOnKeyPressed(event -> handleKeyPress(event.getCode()));
        setOnKeyReleased(event -> handleKeyRelease(event.getCode()));

        // AudioManager (Test)
        audioManager = new AudioManager();
        audioManager.loadAudio("Kuru_Kuru_Kururin", "/music/Raphiiel_Herta_Kuru_Kuru_Kururin.mp3");
        audioManager.loadAudio("Field_of_Memories", "/music/Waterflame_music_Field_of_Memories.mp3");
        audioManager.loadAudio("Glorious_Morning", "/music/Waterflame_music_Glorious_Morning.mp3");

        // TileMap (Test)
        String[][] map = {
                {"0,0", "1,0", "1,0", "1,0", "1,0", "1,0", "2,0"},
                {"0,1", "1,1", "1,1", "1,1", "1,1", "1,1", "2,1"},
                {"0,0", "1,0", "2,0", "3,0", "4,0", "5,0", "6,0"},
                {"0,1", "1,1", "2,1", "3,1", "4,1", "5,1", "6,1"},
                {"0,2", "1,2", "2,2", "3,2", "4,2", "5,2", "6,2"},
                {"0,3", "1,3", "2,3", "3,3", "4,3", "5,3", "6,3"},
                {"0,4", "1,4", "2,4", "3,4", "4,4", "5,4", "6,4"},
                {"0,5", "1,5", "2,5", "3,5", "4,5", "5,5", "6,5"},
                {"0,6", "1,6", "2,6", "3,6", "4,6", "5,6", "6,6"}
        };

        tileMap = new TileMap(camera, root,"/img/Tileset_Maaot_Mossy.png", map, 512, 512, 32, 32);
        tileMap.addToRoot();

        // Load level using LevelLoader
        LevelLoader levelLoader = new LevelLoader();
        levelLoader.loadLevel("/leveldata/level1.json", root, camera);

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
            case LEFT, A -> hero.setDirectionX(-1);
            case RIGHT, D -> hero.setDirectionX(1);
            case UP, W, SPACE -> hero.setJump();
            case I -> audioManager.playMusic("Kuru_Kuru_Kururin");
            case O -> audioManager.playMusic("Field_of_Memories");
            case P -> audioManager.playMusic("Glorious_Morning");
        }
    }

    // Method to handle key release events
    private void handleKeyRelease(KeyCode code) {
        switch (code) {
            case LEFT, RIGHT, A, D -> hero.setDirectionX(0);
        }
    }

    // Method to update the scene elements
    public void update(double deltaTime) {
        // Move the camera using physics equations
        camera.update(deltaTime, hero.getX());

        backgroundManager.update();
        hero.update(deltaTime);
        foeManager.update(deltaTime);

        foeManager.checkHeroCollisions(hero);
        hero.checkProjectileCollisions(foeManager);
    }

    // Method to draw the scene elements
    public void draw() {
        backgroundManager.draw();
        hero.draw();
        foeManager.draw();
        tileMap.draw();
    }
}
