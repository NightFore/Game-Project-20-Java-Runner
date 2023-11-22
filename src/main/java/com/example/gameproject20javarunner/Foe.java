package com.example.gameproject20javarunner;

import javafx.scene.shape.Rectangle;

public class Foe extends MovingThing {
    // Variables
    private final double initialX;
    private final double initialY;

    // Constants (MovingThing)
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Red/Gunner_Red_Run.png";
    private static final double WIDTH = 48;
    private static final double HEIGHT = 48;
    private static final double FINAL_WIDTH = 144;
    private static final double FINAL_HEIGHT = 144;
    private static final int ATTITUDE = 0;
    private static final int INDEX = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;

    // Constants (Foe)
    private static final double MOVEMENT_SPEED = 100.0;

    public Foe(double x, double y) {
        super(x, y, WIDTH, HEIGHT, ATTITUDE, INDEX, MAX_INDEX, DURATION, FRAME_OFFSET_X, FRAME_OFFSET_Y, SPRITE_SHEET_PATH);
        setFinalSizeAndAdjustView(FINAL_WIDTH, FINAL_HEIGHT);
        this.initialX = x;
        this.initialY = y;
    }

    // Method for movement with direction
    public void move(double direction, double deltaTime) {
        setX(getX() + MOVEMENT_SPEED * direction * deltaTime);
    }
}
