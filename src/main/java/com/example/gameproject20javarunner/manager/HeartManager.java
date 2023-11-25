// HeartManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.entity.Heart;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class HeartManager {
    // Game Attributes
    private final Camera camera;
    private final Pane root;
    private final Heart[] hearts;

    // Constants
    private static final int NUMBER_OF_LIVES = 3;
    private static final double HEART_START_X = 10;
    private static final double HEART_START_Y = 10;

    public HeartManager(Camera camera, Pane root) {
        // Initialize game attributes
        this.camera = camera;
        this.root = root;

        hearts = new Heart[NUMBER_OF_LIVES];

        for (int i = 0; i < NUMBER_OF_LIVES; i++) {
            hearts[i] = new Heart(camera, root,0, 0, 0);
            hearts[i].setPosition(HEART_START_X + i * hearts[i].getDisplayWidth(), HEART_START_Y);
        }

        // Test the different heart states: Full, Half, Empty
        hearts[0].setHeartState(0); // Full heart
        hearts[1].setHeartState(1); // Half heart
        hearts[2].setHeartState(2); // Empty heart
    }

    // Method to decrease the number of hearts
    public void decreaseHearts() {
    }

    public void update() {
    }

    public void draw() {
    }
}
