package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.MovingThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class Foe extends MovingThing {
    // MovingThing Attributes
    private static final double DISPLAY_WIDTH = 64;
    private static final double DISPLAY_HEIGHT = 64;
    private static final double FRAME_WIDTH = 498;
    private static final double FRAME_HEIGHT = 498;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 2;
    private static final String SPRITE_SHEET_PATH = "/img/HonkaiStarRail_Herta_Kuru_Kuru_Kururin_498x498.png";

    // Movement Attributes
    private static final double MOVEMENT_SPEED = 200.0;

    /**
     * Constructs a Foe with the specified camera, root pane, initial position, and health.
     *
     * @param camera The camera used for positioning.
     * @param root   The root pane where the elements are added.
     * @param x      The initial x position.
     * @param y      The initial y position.
     */
    public Foe(Camera camera, Pane root, double x, double y) {
        // Call the constructor of the parent class MovingThing with initial parameters
        super(camera, root, x, y, DISPLAY_WIDTH, DISPLAY_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, SPRITE_SHEET_PATH);

        // Initialize movement attributes
        setSpeedX(MOVEMENT_SPEED);
        setDirectionX(-1);
    }
}
