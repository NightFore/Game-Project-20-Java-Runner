package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.MovingThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class Foe extends MovingThing {
    // MovingThing Attributes
    private static final double DISPLAY_WIDTH = 144;
    private static final double DISPLAY_HEIGHT = 144;
    private static final double FRAME_WIDTH = 48;
    private static final double FRAME_HEIGHT = 48;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Red/Gunner_Red_Run.png";

    // Health Attributes
    private int health = 1;

    // Movement Attributes
    private static final double MOVEMENT_SPEED = 100.0;

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

    /**
     * Gets the current health of the Foe.
     *
     * @return The health of the Foe.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Reduces the health of the Foe by the specified amount.
     *
     * @param damage The amount of damage to be taken.
     */
    public void takeDamage(int damage) {
        health -= damage;

        if (health <= 0) {
            health = 0;
            removeFromRoot();
        }
    }
}
