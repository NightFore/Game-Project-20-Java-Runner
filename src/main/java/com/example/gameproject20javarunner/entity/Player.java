// Player.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.map.TileMap;
import com.example.gameproject20javarunner.model.MovingThing;
import com.example.gameproject20javarunner.model.Thing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

/**
 * A class representing the player in the game.
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
    private static final double HITBOX_WIDTH = 16;
    private static final double HITBOX_HEIGHT = 16;
    private static final double FRAME_WIDTH = 48;
    private static final double FRAME_HEIGHT = 48;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Blue/Gunner_Blue_Run.png";

    // Running Attributes
    private final double runAcceleleration = 1000;
    private final double runDeceleration = 400;
    private final double runMax = 160;

    // Jump Attributes
    private boolean isJumping = true;
    private boolean isOnGround = false;
    private final double jumpSpeed = -260;

    // Fall Attributes
    private final double fallGravity = 900;
    private final double fallMax = 160;

    // Fast Fall Attributes
    private boolean isFastFalling = false;
    private final double fastFallAccel = 300;
    private final double fastFallMax = 320;

    // Dash Attributes
    private boolean isDashing = false;
    private boolean canDash = true;
    private double dashTimer = 0.0;
    private double dashCooldownTimer = 0.0;
    private double dashSpeedX = 0.0;
    private double dashSpeedY = 0.0;
    private final double DashSpeed = 240;
    private final double EndDashUpMult = 0.75;
    private final double DashTime = 0.15;
    private final double DashCooldown = 0.2;


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

        // Set Game Attributes
        this.camera = camera;
        this.root = root;
        this.tileMap = tileMap;
    }



    // -------------------- Horizontal Movement Logic -------------------- //
    /**
     * Updates the player's horizontal speed based on input.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateHorizontalMovement(double deltaTime) {
        double currentSpeedX = Math.abs(getSpeedX());

        // Check if the player is trying to move horizontally
        if (getDirectionX() != 0) {
            // Apply acceleration
            if (currentSpeedX < runMax) {
                setSpeedX(getSpeedX() + runAcceleleration * getDirectionX() * deltaTime);
            }
            // Maximum speed value
            else {
                setSpeedX(runMax * getDirectionX());
            }
        }

        // Decelerate if the player is not actively moving
        else {
            if (currentSpeedX != 0) {
                // Decelerate in the opposite direction
                int direction = (int) Math.signum(getSpeedX());
                double newSpeedX = getSpeedX() - runDeceleration * direction * deltaTime;

                // Ensure deceleration doesn't reverse the direction
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



    // -------------------- Vertical Movement Logic -------------------- //
    /**
     * Updates the player's vertical speed based on gravity and fast falling state.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateVerticalMovement(double deltaTime) {
        double newSpeedY = getSpeedY();

        // Check if the player is fast falling
        if (!isFastFalling) {
            // Apply regular gravity
            newSpeedY += fallGravity * deltaTime;

            // Limit the fall speed
            if (newSpeedY > fallMax) {
                newSpeedY = fallMax;
            }
        } else {
            // Apply fast fall acceleration
            newSpeedY += fastFallAccel * deltaTime;

            // Limit the fast fall speed
            if (newSpeedY > fastFallMax) {
                newSpeedY = fastFallMax;
            }
        }

        // Set the new vertical speed
        setSpeedY(newSpeedY);
    }

    /**
     * Initiates a jump if the player is on the ground and not currently jumping.
     */
    public void jump() {
        if (!isJumping && isOnGround) {
            isJumping = true;
            setSpeedY(jumpSpeed);
        }
    }

    /**
     * Initiates the fast fall
     */
    public void startFastFall() {
        // Check if the player is airborne
        if (!isOnGround) {
            // Set the player to fast falling state
            isFastFalling = true;
        }
    }

    /**
     * Ends the fast fall
     */
    public void endFastFall() {
        // Check if the player is airborne
        if (!isOnGround) {
            // Reset the player's fast falling state
            isFastFalling = false;
        }
    }




    // -------------------- Dash Logic -------------------- //
    /**
     * Initiates a dash if the player is not currently dashing.
     * Sets the dash timer, dash speed, and updates cooldown timers.
     */
    public void dash() {
        // Check dash cooldown and direction input
        if (canDash && (getDirectionX() != 0 || getDirectionY() != 0)) {
            canDash = false;
            isDashing = true;
            dashTimer = DashTime;
            dashSpeedX = DashSpeed * getDirectionX();
            dashSpeedY = DashSpeed * getDirectionY();
            setSpeed(dashSpeedX, dashSpeedY);
        }
    }

    /**
     * Updates the dash logic, including checking for the end of the dash.
     * Manages dash cooldown timers when not dashing.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateDash(double deltaTime) {
        if (isDashing) {
            dashTimer -= deltaTime;

            // Check for end of dash and ground contact
            if (dashTimer <= 0) {
                endDash();
            }
        } else {
            // Update dash cooldown timers
            dashCooldownTimer -= deltaTime;

            // Enable dash when on ground and cooldown is complete
            if (isOnGround && dashCooldownTimer <= 0) {
                canDash = true;
            }
        }
    }

    /**
     * Ends the dash and adjusts the player's speed and cooldown timers.
     */
    private void endDash() {
        isDashing = false;
        dashCooldownTimer = DashCooldown;
        setSpeed(dashSpeedX * EndDashUpMult, dashSpeedY * EndDashUpMult);
    }



    // -------------------- Collision Logic -------------------- //
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
                collision = futureHitboxX.getBoundsInLocal().intersects(tile.getHitboxRectangle().getBoundsInLocal());
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
                collision = futureHitboxY.getBoundsInLocal().intersects(tile.getHitboxRectangle().getBoundsInLocal());
                if (collision) {
                    break;
                }
            }
        }

        return collision;
    }



    // -------------------- Movement Logic -------------------- //
    /**
     * Applies the player's movement based on current speed, collisions, and elapsed time.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void applyMovement(double deltaTime) {
        // Check for horizontal collision
        if (checkCollisionX(deltaTime)) {
            // Stop horizontal movement
            setSpeedX(0);
        }

        // Check for vertical collisions
        if (checkCollisionY(deltaTime)) {
            // Reset ground flag if descending (collision with the ground)
            if (getSpeedY() > 0) {
                isOnGround = true;
                isJumping = false;
                isFastFalling = false;
            }
            // Stop vertical movement
            setSpeedY(0);
        }

        // Check if airborne
        else {
            isOnGround = false;
        }

        // Set the new position based on current speed and time
        setX(getX() + getSpeedX() * deltaTime);
        setY(getY() + getSpeedY() * deltaTime);
    }



    // -------------------- Rendering Logic --------------------//
    /**
     * Updates the rendering and movement logic of the player.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        // Call the parent class's update method
        super.update(deltaTime);

        // Execute movement logic only if not currently dashing
        if (!isDashing) {
            // Update horizontal movement logic
            updateHorizontalMovement(deltaTime);

            // Update vertical movement logic
            updateVerticalMovement(deltaTime);
        }

        // Update dash logic
        updateDash(deltaTime);

        // Apply overall movement logic, including collisions
        applyMovement(deltaTime);
    }

    /**
     * Draws the player and any associated visual elements.
     */
    @Override
    public void draw() {
        // Call the parent class's draw method
        super.draw();
    }
}
