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
    private static final double DISPLAY_WIDTH = 32;
    private static final double DISPLAY_HEIGHT = 32;
    private static final double HITBOX_WIDTH = 32;
    private static final double HITBOX_HEIGHT = 32;
    private static final double FRAME_WIDTH = 48;
    private static final double FRAME_HEIGHT = 48;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Blue/Gunner_Blue_Run.png";

    // Test

    private final double runAcceleleration = 1000;
    private final double runDeceleration = 400;
    private final double runMax = 90;
    private final double jumpSpeed = -260;
    private final double gravity = 900;
    private final double maxFall = 160;
    private boolean isJumping = true;
    private boolean isOnGround = false;
    private boolean canVariableJump = true;

    private static final double jumpHeight = 64;
    private static final double timeToJumpApex = 0.3;
    private double jumpTime = 0;


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
            if (currentSpeedX < runMax) {
                setSpeedX(getSpeedX() + runAcceleleration * getDirectionX() * deltaTime);
            }
            // Maximum speed
            else {
                setSpeedX(runMax * getDirectionX());
            }
        }
        else {
            // Check for deceleration
            if (currentSpeedX != 0) {
                // Decelerate
                int direction = (int) Math.signum(getSpeedX());
                double newSpeedX = getSpeedX() - runDeceleration * direction * deltaTime;

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

    /**
     * Updates the gravity logic of the player.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateGravity(double deltaTime) {
        // Update the vertical speed based on gravity
        double newSpeedY = getSpeedY() + gravity * deltaTime;

        // Limit the fall speed to the maximum allowed
        if (newSpeedY > maxFall) {
            newSpeedY = maxFall;
        }

        // Set the new vertical speed
        setSpeedY(newSpeedY);
    }

    public void jump() {
        if (!isJumping && isOnGround) {
            isJumping = true;
            setSpeedY(jumpSpeed);
        }
    }

    private void applyMovement(double deltaTime) {
        // Calculate new position based on speed, direction, and time
        double newX = getX() + getSpeedX() * deltaTime;
        double newY = getY() + getSpeedY() * deltaTime;

        if (!checkCollisionX(deltaTime)) {
            setX(newX);
        } else {
            setSpeedX(0);
        }
        if (!checkCollisionY(deltaTime)) {
            setY(newY);
        } else {
            if (getSpeedY() > 0) {
                isJumping = false;
                isOnGround = true;
            }
            setSpeedY(0);
        }
    }

    /**
     * Checks for potential horizontal collision with tiles using future position.
     *
     * @param deltaTime Time since last update.
     * @return true if collision, false otherwise.
     */
    public boolean checkCollisionX(double deltaTime) {
        // Calculate future X position and hitbox
        double futureX = getHitboxX() + getSpeedX() * deltaTime;
        Rectangle futureHitboxX = new Rectangle(futureX, getY(), getHitboxWidth(), getHitboxHeight());

        // Adjusted position for tile calculation
        double adjustedX = futureX + getHitboxWidth() * (int) ((1 + Math.signum(getSpeedX())) / 2);
        int tileIndexX = (int) (adjustedX / tileMap.getDisplayTileWidth());

        // Min and max Y indices for tile collision
        int tileIndexYMin = (int) (getHitboxY() / tileMap.getDisplayTileHeight());
        int tileIndexYMax = (int) ((getHitboxHeight() + getHitboxY()) / tileMap.getDisplayTileHeight());

        // Check for collision with potential tiles along the Y-axis
        boolean collision = false;
        for (int tileIndexY = tileIndexYMin; tileIndexY <= tileIndexYMax; tileIndexY++) {
            Thing tile = tileMap.getTile(tileIndexY, tileIndexX);
            if (tile != null) {
                collision = futureHitboxX.getBoundsInParent().intersects(tile.getHitboxRectangle().getBoundsInParent());
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
        // Calculate future Y position and hitbox
        double futureY = getHitboxY() + getSpeedY() * deltaTime;
        Rectangle futureHitboxY = new Rectangle(getX(), futureY, getHitboxWidth(), getHitboxHeight());

        // Adjusted position for tile calculation
        double adjustedY = futureY + getHitboxHeight() * (int) ((1 + Math.signum(getSpeedY())) / 2);
        int tileIndexY = (int) (adjustedY / tileMap.getDisplayTileHeight());

        // Min and max X indices for tile collision
        int tileIndexXMin = (int) (getHitboxX() / tileMap.getDisplayTileWidth());
        int tileIndexXMax = (int) ((getHitboxWidth() + getHitboxX()) / tileMap.getDisplayTileWidth());

        // Check for collision with potential tiles along the X-axis
        boolean collision = false;
        for (int tileIndexX = tileIndexXMin; tileIndexX <= tileIndexXMax; tileIndexX++) {
            Thing tile = tileMap.getTile(tileIndexY, tileIndexX);
            if (tile != null) {
                collision = futureHitboxY.getBoundsInParent().intersects(tile.getHitboxRectangle().getBoundsInParent());
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

        // Horizontal Logic
        updateRunning(deltaTime);

        // Vertical Logic
        updateGravity(deltaTime);

        // Movement Logic
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
