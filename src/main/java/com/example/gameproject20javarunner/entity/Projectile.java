// Projectile.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.MovingThing;
import javafx.scene.layout.Pane;

public class Projectile extends MovingThing {
    // Screen variables
    private final Pane root;

    // Constants (AnimatedThing)
    private static final double INITIAL_X = 0;
    private static final double INITIAL_Y = 0;
    private static final double WIDTH = 128;
    private static final double HEIGHT = 128;
    private static final double DISPLAY_WIDTH = 128;
    private static final double DISPLAY_HEIGHT = 128;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 8;
    private static final int DURATION = 8;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final String SPRITE_SHEET_PATH = "/img/effect_Pimen_Energy_Ball_128x128.png";

    // Constants (MovingThing)
    private static final double MOVEMENT_SPEED = 1000;

    public Projectile(Pane root) {
        super(INITIAL_X, INITIAL_Y, WIDTH, HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, SPRITE_SHEET_PATH);
        setDisplaySize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        this.root = root;
        setSpeedX(MOVEMENT_SPEED);
        root.getChildren().add(getImageView());
        root.getChildren().add(getHitboxRectangle());
    }

    public void removeFromRoot() {
        if (root.getChildren().contains(getImageView())) {
            root.getChildren().remove(getImageView());
            root.getChildren().remove(getHitboxRectangle());
        }
    }

    // Method to handle the hero's rendering logic
    public void update(double deltaTime) {
        super.update(deltaTime);
    }
}
