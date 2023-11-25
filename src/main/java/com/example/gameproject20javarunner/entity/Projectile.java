// Projectile.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.MovingThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public class Projectile extends MovingThing {
    // MovingThing Attributes
    private static final double INITIAL_X = 0;
    private static final double INITIAL_Y = 0;
    private static final double DISPLAY_WIDTH = 128;
    private static final double DISPLAY_HEIGHT = 128;
    private static final double FRAME_WIDTH = 128;
    private static final double FRAME_HEIGHT = 128;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 8;
    private static final int DURATION = 8;
    private static final String SPRITE_SHEET_PATH = "/img/effect_Pimen_Energy_Ball_128x128.png";

    // Projectile Attributes
    private static final double MOVEMENT_SPEED = 1000;

    /**
     * Constructs a Projectile with the specified camera and root pane.
     *
     * @param camera The camera used for positioning.
     * @param root   The root pane where the elements are added.
     */
    public Projectile(Camera camera, Pane root) {
        // Call the constructor of the parent class MovingThing with initial parameters
        super(camera, root, INITIAL_X, INITIAL_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, SPRITE_SHEET_PATH);

        // Initialize movement attributes
        setSpeedX(MOVEMENT_SPEED);
    }

    /**
     * Checks if the projectile collides with an enemy.
     *
     * @param enemy The enemy to check for collision.
     * @return True if the projectile collides with the enemy, false otherwise.
     */
    public boolean collidesWithEnemy(Foe enemy) {
        return this.getHitboxRectangle().getBoundsInParent().intersects(enemy.getHitboxRectangle().getBoundsInParent());
    }
}
