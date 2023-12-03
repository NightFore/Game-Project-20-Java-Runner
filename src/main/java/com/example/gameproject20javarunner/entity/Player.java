// Player.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.map.TileMap;
import com.example.gameproject20javarunner.model.MovingThing;
import com.example.gameproject20javarunner.model.Thing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * A class representing the hero character in the game.
 * Extends the abstract class MovingThing.
 */
public class Player extends MovingThing {
    // Game Attributes
    private final Camera camera;
    private final Pane root;
    private final TileMap tileMap;

    // MovingThing Attributes
    private static final double INITIAL_X = 0;
    private static final double INITIAL_Y = 0;
    private static final double DISPLAY_WIDTH = 144;
    private static final double DISPLAY_HEIGHT = 144;
    private static final double FRAME_WIDTH = 48;
    private static final double FRAME_HEIGHT = 48;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Blue/Gunner_Blue_Run.png";
    private static final double HITBOX_WIDTH = 144;
    private static final double HITBOX_HEIGHT = 144;

    // Test

    private double accelerationX = 600;
    private double decelerationX = 800;
    private double maxSpeedX = 400;

    /**
     * Constructs a Hero with the specified camera and root pane.
     *
     * @param camera The camera used for positioning.
     * @param root   The root pane where the elements are added.
     */
    public Player(Camera camera, Pane root, TileMap tileMap) {
        // Call the constructor of the parent class MovingThing with initial parameters
        super(camera, root, INITIAL_X, INITIAL_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT, FRAME_WIDTH, FRAME_HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, SPRITE_SHEET_PATH);
        setHitboxSize(HITBOX_WIDTH, HITBOX_HEIGHT);

        // Initialize game attributes
        this.camera = camera;
        this.root = root;
        this.tileMap = tileMap;
    }

    /**
     * Updates the horizontal movement logic of the player.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateRunning(double deltaTime) {
        double currentSpeedX = Math.abs(getSpeedX());

        // Check horizontal movement
        if (getDirectionX() != 0) {
            // Acceleration
            if (currentSpeedX < maxSpeedX) {
                setSpeedX(getSpeedX() + accelerationX * getDirectionX() * deltaTime);
            }
            // Maximum speed
            else {
                setSpeedX(maxSpeedX * getDirectionX());
            }
        }
        else {
            // Check for deceleration
            if (currentSpeedX != 0) {
                // Decelerate
                int direction = (int) Math.signum(getSpeedX());
                double newSpeedX = getSpeedX() - decelerationX * direction * deltaTime;

                // Apply the new speed after deceleration
                if (Math.signum(newSpeedX) == direction) {
                    setSpeedX(newSpeedX);
                }
                // Stop when reaching low speed
                else {
                    setSpeedX(0);
                }
            }
        }
    }

    private void applyMovement(double deltaTime) {
        // Calculate new position based on speed, direction, and time
        double newX = getX() + getSpeedX() * deltaTime;
        double newY = getY();
        // double newY = getY() + getSpeedY() * deltaTime;

        // Set the new position
        setPosition(newX, newY);
    }

    /**
     * Checks for potential horizontal collision with tiles using future position.
     *
     * @param deltaTime Time since last update.
     * @return true if collision, false otherwise.
     */
    public boolean checkCollisionX(double deltaTime) {
        // Calculate future position and hitbox
        double futureX = getHitboxX() + getSpeedX() * getDirectionX() * deltaTime;
        Rectangle futureHitbox = new Rectangle(futureX, getY(), getHitboxWidth(), getHitboxHeight());

        // Adjusted position for tile calculation
        double adjustedX = futureX + getHitboxWidth() * (int) ((1 + Math.signum(getDirectionX())) / 2);
        int tileIndexX = (int) (adjustedX / tileMap.getDisplayTileWidth());

        // Min and max Y indices for tile collision
        int tileIndexYMin = (int) (getHitboxY() / tileMap.getDisplayTileHeight());
        int tileIndexYMax = (int) ((getHitboxHeight() + getHitboxY()) / tileMap.getDisplayTileHeight());

        // Check for collision with potential tiles along the Y-axis
        boolean collision = false;
        for (int tileIndexY = tileIndexYMin; tileIndexY <= tileIndexYMax; tileIndexY++) {
            Thing tile = tileMap.getTile(tileIndexY, tileIndexX);
            if (tile != null) {
                collision = futureHitbox.getBoundsInParent().intersects(tile.getHitboxRectangle().getBoundsInParent());
                if (collision) {
                    break;
                }
            }
        }

        return collision;
    }

    /**
     * Checks for potential vertical collision with tiles using future position.
     *
     * @param deltaTime Time since last update.
     * @return true if collision, false otherwise.
     */
    public boolean checkCollisionY(double deltaTime) {
        // Calculate future position and hitbox
        double futureY = getHitboxY() + getSpeedY() * getDirectionY() * deltaTime;
        Rectangle futureHitbox = new Rectangle(getX(), futureY, getHitboxWidth(), getHitboxHeight());

        // Adjusted position for tile calculation
        double adjustedY = futureY + getHitboxHeight() * (int) ((1 + Math.signum(getDirectionY())) / 2);
        int tileIndexY = (int) (adjustedY / tileMap.getDisplayTileHeight());

        // Min and max X indices for tile collision
        int tileIndexXMin = (int) (getHitboxX() / tileMap.getDisplayTileWidth());
        int tileIndexXMax = (int) ((getHitboxWidth() + getHitboxX()) / tileMap.getDisplayTileWidth());

        // Check for collision with potential tiles along the X-axis
        boolean collision = false;
        for (int tileIndexX = tileIndexXMin; tileIndexX <= tileIndexXMax; tileIndexX++) {
            Thing tile = tileMap.getTile(tileIndexY, tileIndexX);
            if (tile != null) {
                collision = futureHitbox.getBoundsInParent().intersects(tile.getHitboxRectangle().getBoundsInParent());
                if (collision) {
                    break;
                }
            }
        }

        return collision;
    }

    /**
     * Updates the hero's rendering logic.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (getDirectionX() != 0 && checkCollisionX(deltaTime)) {
        }
        if (getDirectionY() != 0 && checkCollisionX(deltaTime)) {
        }

        updateRunning(deltaTime);
        applyMovement(deltaTime);
    }

    /**
     * Draws the hero and their projectiles.
     */
    @Override
    public void draw() {
        super.draw();
    }
}
