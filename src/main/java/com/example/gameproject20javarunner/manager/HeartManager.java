// HeartManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.entity.Heart;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class HeartManager {
    // Game Attributes
    private final Camera camera;
    private final Pane root;

    // HeartManager Attributes
    private final Heart[] hearts;
    private static final int NUMBER_OF_LIVES = 3;

    // Heart Attributes
    private static final double INITIAL_X = 10;
    private static final double INITIAL_Y = 10;
    private static final double DISPLAY_WIDTH = 32.0;
    private static final double DISPLAY_HEIGHT = 32.0;
    private static final double SPRITE_WIDTH = 32.0;
    private static final double SPRITE_HEIGHT = 32.0;
    private static final double SPRITE_OFFSET_X = 0.0;
    private static final double SPRITE_OFFSET_Y = 0.0;
    private static final String SPRITE_SHEET_PATH = "/img/NicoleMarieT_Heart_Sprite_Sheet_32x32.png";


    public HeartManager(Camera camera, Pane root) {
        // Initialize game attributes
        this.camera = camera;
        this.root = root;

        hearts = new Heart[NUMBER_OF_LIVES];

        for (int i = 0; i < NUMBER_OF_LIVES; i++) {
            double heartX = INITIAL_X + i * DISPLAY_WIDTH;
            double heartY = INITIAL_Y;
            hearts[i] = new Heart(camera, root, heartX, heartY, DISPLAY_WIDTH, DISPLAY_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, SPRITE_SHEET_PATH);
        }

        // Test the different heart states: Full, Half, Empty
        hearts[0].setHeartState(0); // Full heart
        hearts[1].setHeartState(1); // Half heart
        hearts[2].setHeartState(2); // Empty heart
    }

    // Method to decrease the number of hearts
    public void decreaseHearts() {
    }
}
