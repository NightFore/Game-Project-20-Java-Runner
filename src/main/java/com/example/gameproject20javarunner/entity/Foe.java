package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.MovingThing;

public class Foe extends MovingThing {
    private final double initialX;
    private final double initialY;

    // Constants (AnimatedThing)
    private static final double WIDTH = 48;
    private static final double HEIGHT = 48;
    private static final double FINAL_WIDTH = 144;
    private static final double FINAL_HEIGHT = 144;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Red/Gunner_Red_Run.png";

    // Constants (MovingThing)
    private static final double MOVEMENT_SPEED = -100.0;

    public Foe(double x, double y) {
        super(x, y, WIDTH, HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, SPRITE_SHEET_PATH);
        setDisplaySize(FINAL_WIDTH, FINAL_HEIGHT);
        this.initialX = x;
        this.initialY = y;
        setSpeed(MOVEMENT_SPEED, 0);
    }
}
