// Player.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.manager.AudioManager;
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
public class Player {
    // Game Attributes
    private final Camera camera;
    private final Pane root;
    private final TileMap tileMap;
    private final AudioManager audioManager;
    private double deltaTime = 0;

    // Thing Attributes
    private static final double INITIAL_X = 400;
    private static final double INITIAL_Y = 2160;
    private static final double DISPLAY_WIDTH = 48;
    private static final double DISPLAY_HEIGHT = 48;
    private static final double HITBOX_WIDTH = 24;
    private static final double HITBOX_HEIGHT = 24;
    private static final double SPRITE_WIDTH = 48;
    private static final double SPRITE_HEIGHT = 48;
    private static final double SPRITE_OFFSET_X = 0;
    private static final double SPRITE_OFFSET_Y = 0;
    private static final double SPRITE_DASH_WIDTH = 128;
    private static final double SPRITE_DASH_HEIGHT = 128;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX_IDLE = 4;
    private static final int MAX_INDEX_RUN = 5;
    private static final int MAX_INDEX_JUMP = 1;
    private static final int MAX_INDEX_DASH = 8;
    private static final int DURATION_IDLE = 8;
    private static final int DURATION_RUN = 8;
    private static final int DURATION_JUMP = 0;
    private static final int DURATION_DASH = 8;
    private static final String SPRITE_SHEET_PATH_IDLE = "/img/sprite_SecretHideout_Gunner_Blue_Idle_48.png";
    private static final String SPRITE_SHEET_PATH_RUN = "/img/sprite_SecretHideout_Gunner_Blue_Run_48.png";
    private static final String SPRITE_SHEET_PATH_JUMP = "/img/sprite_SecretHideout_Gunner_Blue_Jump_48.png";
    private static final String SPRITE_SHEET_PATH_DASH = "/img/effect_Pimen_Energy_Ball_128x128.png";
    private MovingThing currentThing;
    private final MovingThing idleThing;
    private final MovingThing runThing;
    private final MovingThing jumpThing;
    private final MovingThing dashThing;

    // Run Attributes
    private final double runAcceleleration = 1000;
    private final double runDeceleration = 400;
    private final double runMax = 90;
    private final double holdRunAcceleration = 100;
    private final double holdRunMax = 180;

    // Jump Attributes
    private boolean isJumping = true;
    private boolean isOnGround = false;
    private final double jumpSpeed = -260;

    // Fall Attributes
    private final double fallGravity = 900;
    private final double fallMax = 160;

    // Fast Fall Attributes
    private boolean isFastFalling = false;
    private final double fastFallAccel = 2700;
    private final double fastFallMax = 640;

    // Dash Attributes
    private boolean isDashing = false;
    private boolean canDash = true;
    private double dashTimer = 0.0;
    private double dashCooldownTimer = 0.0;
    private double dashSpeedX = 0.0;
    private double dashSpeedY = 0.0;
    private final double DashSpeed = 600;
    private final double EndDashMult = 0.75;
    private final double DashTime = 0.15;
    private final double DashCooldown = 0.2;


    /**
     * Constructs a Hero with the specified camera and root pane.
     *
     * @param camera The camera used for positioning.
     * @param root   The root pane where the elements are added.
     */
    public Player(Camera camera, Pane root, TileMap tileMap, AudioManager audioManager) {
        // Set Game Attributes
        this.camera = camera;
        this.root = root;
        this.tileMap = tileMap;
        this.audioManager = audioManager;

        idleThing = new MovingThing(camera, root, INITIAL_X, INITIAL_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, ATTITUDE, MAX_INDEX_IDLE, DURATION_IDLE, SPRITE_SHEET_PATH_IDLE);
        runThing = new MovingThing(camera, root, INITIAL_X, INITIAL_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, ATTITUDE, MAX_INDEX_RUN, DURATION_RUN, SPRITE_SHEET_PATH_RUN);
        jumpThing = new MovingThing(camera, root, INITIAL_X, INITIAL_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT, SPRITE_WIDTH, SPRITE_HEIGHT, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, ATTITUDE, MAX_INDEX_JUMP, DURATION_JUMP, SPRITE_SHEET_PATH_JUMP);
        dashThing = new MovingThing(camera, root, INITIAL_X, INITIAL_Y, DISPLAY_WIDTH, DISPLAY_HEIGHT, SPRITE_DASH_WIDTH, SPRITE_DASH_HEIGHT, SPRITE_OFFSET_X, SPRITE_OFFSET_Y, ATTITUDE, MAX_INDEX_DASH, DURATION_DASH, SPRITE_SHEET_PATH_DASH);

        idleThing.setHitboxSize(HITBOX_WIDTH, HITBOX_HEIGHT);
        runThing.setHitboxSize(HITBOX_WIDTH, HITBOX_HEIGHT);
        jumpThing.setHitboxSize(HITBOX_WIDTH, HITBOX_HEIGHT);
        dashThing.setHitboxSize(HITBOX_WIDTH, HITBOX_HEIGHT);

        runThing.setVisible(true, true, true);
        idleThing.setVisible(false, false, false);
        jumpThing.setVisible(false, false, false);
        dashThing.setVisible(false, false, false);
        currentThing = runThing;
    }


    public void setThing(MovingThing thing) {
        thing.setPosition(getX(), getY());
        thing.setSpeed(getSpeedX(), getSpeedY());
        thing.setDirection(getDirectionX(), getDirectionY());
        thing.setVisible(true, true, true);
        currentThing.setVisible(false, false, false);
        currentThing = thing;
    }

    public double getX() {
        return currentThing.getX();
    }

    public double getY() {
        return currentThing.getY();
    }

    public double getHitboxX() {
        return currentThing.getHitboxX();
    }

    public double getHitboxY() {
        return currentThing.getHitboxY();
    }

    public double getHitboxWidth() {
        return currentThing.getHitboxWidth();
    }

    public double getHitboxHeight() {
        return currentThing.getHitboxHeight();
    }

    public double getDirectionX() {
        return currentThing.getDirectionX();
    }

    public double getDirectionY() {
        return currentThing.getDirectionY();
    }

    public double getSpeedX() {
        return currentThing.getSpeedX();
    }

    public double getSpeedY() {
        return currentThing.getSpeedY();
    }

    public void setX(double x) {
        currentThing.setX(x);
    }

    public void setY(double y) {
        currentThing.setY(y);
    }

    public void setDirectionX(double directionX) {
        currentThing.setDirectionX(directionX);
    }

    public void setDirectionY(double directionY) {
        currentThing.setDirectionY(directionY);
    }

    public void setSpeedX(double speedX) {
        currentThing.setSpeedX(speedX);
    }

    public void setSpeedY(double speedY) {
        currentThing.setSpeedY(speedY);
    }

    public void setSpeed(double speedX, double speedY) {
        currentThing.setSpeed(speedX, speedY);
    }

    // -------------------- Horizontal Movement Logic -------------------- //
    /**
     * Updates the player's horizontal speed based on input.
     */
    private void updateHorizontalMovement() {
        double currentSpeedX = Math.abs(getSpeedX());

        // Check if the player is trying to move horizontally
        if (getDirectionX() != 0) {
            // Check if the player is changing direction
            if (getDirectionX() != (int) Math.signum(getSpeedX())) {
                // Apply acceleration and deceleration to change direction
                setSpeedX(getSpeedX() + (runAcceleleration - runDeceleration) * getDirectionX() * deltaTime);
            }
            // Accelerate if the current speed is less than runMax
            else if (currentSpeedX < runMax) {
                setSpeedX(getSpeedX() + runAcceleleration * getDirectionX() * deltaTime);
            }
            // Accelerate (Hold) if the current speed is less than holdRunMax
            else if (currentSpeedX < holdRunMax) {
                setSpeedX(getSpeedX() + holdRunAcceleration * getDirectionX() * deltaTime);
            }
            // Maintain constant speed if the current speed is greater than holdRunMax
            else if (getDirectionX() == (int) Math.signum(getSpeedX())){
                setSpeedX(holdRunMax * getDirectionX());
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
     */
    private void updateVerticalMovement() {
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
            isJumping = true;
            dashTimer = DashTime;
            setThing(dashThing);

            // Play a dash sound effect
            audioManager.playSound("dash_sound");

            // Calculate the magnitude of the direction vector
            double dirMagnitude = Math.sqrt(getDirectionX() * getDirectionX() + getDirectionY() * getDirectionY());

            // Normalize the direction vector
            double normalizedDirX = getDirectionX() / dirMagnitude;
            double normalizedDirY = getDirectionY() / dirMagnitude;

            // Calculate the components of the dash speed vector using normalized direction
            double dashSpeedX = DashSpeed * normalizedDirX;
            double dashSpeedY = DashSpeed * normalizedDirY;

            setSpeed(dashSpeedX, dashSpeedY);
        }
    }

    /**
     * Updates the dash logic, including checking for the end of the dash.
     * Manages dash cooldown timers when not dashing.
     */
    private void updateDash() {
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
        setThing(runThing);
        dashCooldownTimer = DashCooldown;
        setSpeed(dashSpeedX * EndDashMult, dashSpeedY * EndDashMult);
    }



    // -------------------- Collision Logic -------------------- //
    /**
     * Checks for potential horizontal collision with tiles using future position.
     *
     * @return The tile with which collision occurs, or null if no collision.
     */
    public Thing checkCollisionX() {
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
        Thing collidedTileX = null;
        for (int tileIndexY = tileIndexYMin; tileIndexY <= tileIndexYMax; tileIndexY++) {
            Thing tile = tileMap.getTile(tileIndexY, tileIndexX);
            if (tile != null) {
                if (futureHitboxX.getBoundsInLocal().intersects(tile.getHitboxRectangle().getBoundsInLocal())) {
                    collidedTileX = tile;
                    break;
                }
            }
        }

        return collidedTileX;
    }

    /**
     * Checks for potential vertical collision with tiles using future position.
     *
     * @return The tile with which collision occurs, or null if no collision.
     */
    public Thing checkCollisionY() {
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
        Thing collidedTileY = null;
        for (int tileIndexX = tileIndexXMin; tileIndexX <= tileIndexXMax; tileIndexX++) {
            Thing tile = tileMap.getTile(tileIndexY, tileIndexX);
            if (tile != null) {
                if (futureHitboxY.getBoundsInLocal().intersects(tile.getHitboxRectangle().getBoundsInLocal())) {
                    collidedTileY = tile;
                    break;
                }
            }
        }

        return collidedTileY;
    }



    // -------------------- Movement Logic -------------------- //
    /**
     * Applies the player's movement based on current speed, collisions, and elapsed time.
     */
    private void applyMovement() {

        // Check for horizontal collision
        Thing collidedTileX = checkCollisionX();
        if (collidedTileX != null) {
            // Adjust X position
            int adjustedX;
            if (Math.signum(getSpeedX()) == 1) {
                adjustedX = (int) (getX() + collidedTileX.getHitboxX() - getHitboxX() - getHitboxWidth());
            } else {
                adjustedX = (int) (getX() + getHitboxX() - collidedTileX.getX() - collidedTileX.getHitboxWidth());
            }
            setX(adjustedX);

            // Stop horizontal movement
            setSpeedX(0);
        }

        // Check for vertical collisions
        Thing collidedTileY = checkCollisionY();
        if (collidedTileY != null) {
            // Adjust Y position
            int adjustedY;
            if (Math.signum(getSpeedY()) == 1) {
                adjustedY = (int) (getY() + collidedTileY.getHitboxY() - getHitboxY() - getHitboxHeight());
            } else {
                adjustedY = (int) (getY() + getHitboxY() - collidedTileY.getY() - collidedTileY.getHitboxHeight());
            }
            setY(adjustedY);

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
    public void update(double deltaTime) {
        // Call the parent class's update method
        currentThing.update(deltaTime);
        this.deltaTime = deltaTime;

        // Execute movement logic only if not currently dashing
        if (!isDashing) {
            // Update horizontal movement logic
            updateHorizontalMovement();

            // Update vertical movement logic
            updateVerticalMovement();
        }

        // Update dash logic
        updateDash();

        // Apply overall movement logic, including collisions
        applyMovement();
    }

    /**
     * Draws the player and any associated visual elements.
     */
    public void draw() {
        // Call the parent class's draw method
        currentThing.draw();
    }
}
