// HeartManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.entity.Heart;
import javafx.scene.layout.Pane;

public class HeartManager {
    private final Heart[] hearts;

    // Constants
    private static final int NUMBER_OF_LIVES = 3;
    private static final double HEART_START_X = 10;
    private static final double HEART_START_Y = 10;

    public HeartManager(Pane root) {
        hearts = new Heart[NUMBER_OF_LIVES];

        for (int i = 0; i < NUMBER_OF_LIVES; i++) {
            hearts[i] = new Heart(HEART_START_X + i * Heart.getDisplayWidth(), HEART_START_Y, 0);
            root.getChildren().addAll(hearts[i].getFullHeart(), hearts[i].getHalfHeart(), hearts[i].getEmptyHeart());
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
