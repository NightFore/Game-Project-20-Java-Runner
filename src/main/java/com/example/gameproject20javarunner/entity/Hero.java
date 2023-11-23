// Hero.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.model.MovingThing;

public class Hero extends MovingThing {
    // Variables
    private final double initialX;
    private final double initialY;
    private double jumpSpeed;
    private double jumpTopTime;
    private boolean isJumping;
    private long invincibilityTime;  // in nanoseconds

    // Constants (AnimatedThing)
    private static final double WIDTH = 48;
    private static final double HEIGHT = 48;
    private static final double DISPLAY_WIDTH = 144;
    private static final double DISPLAY_HEIGHT = 144;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final String HERO_BLUE_RUN_SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Blue/Gunner_Blue_Run.png";

    // Constants (MovingThing)
    private static final double MOVEMENT_SPEED = 100.0;
    private static final double INITIAL_JUMP_SPEED = -600;
    private static final double JUMP_ACCELERATION_UP = 1800;
    private static final double JUMP_ACCELERATION_DOWN = 1200;
    private static final double JUMP_TOP_DURATION = 0.10;
    private static final double MAX_JUMP_HEIGHT = 100;

    public Hero(double x, double y) {
        super(x, y, WIDTH, HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, HERO_BLUE_RUN_SPRITE_SHEET_PATH);
        setDisplaySize(DISPLAY_WIDTH, DISPLAY_HEIGHT);
        this.initialX = x;
        this.initialY = y;
        this.jumpSpeed = 0;
        this.jumpTopTime = 0;
        this.isJumping = false;
        this.invincibilityTime = 0;

        setDirection(1, 0);
        setSpeed(100, 0);
    }

    // Method to handle the hero's jump
    public void jump() {
        if (!isJumping) {
            jumpSpeed = INITIAL_JUMP_SPEED;
            jumpTopTime = 0;
            isJumping = true;
        }
    }

    public boolean isInvincible() {
        return invincibilityTime > 0;
    }

    public void setInvincible(boolean invincible) {
        if (invincible) {
            invincibilityTime = 1000000000L;  // Set invincibility time to 1 second
        } else {
            invincibilityTime = 0;
        }
    }

    // Method to update the hero's position during a jump
    private void updateJump(double deltaTime) {
        if (isJumping) {
            double newY = getY() + jumpSpeed * deltaTime;

            // Phase 4: Land
            if (newY > initialY) {
                newY = initialY;
                isJumping = false;
            }
            // Phase 3: Descend
            else if (jumpTopTime > JUMP_TOP_DURATION) {
                jumpSpeed += JUMP_ACCELERATION_DOWN * deltaTime;
            }
            // Phase 2: Pause
            else if (newY <= initialY - MAX_JUMP_HEIGHT) {
                newY = initialY - MAX_JUMP_HEIGHT;
                jumpSpeed = 0;
                jumpTopTime += deltaTime;
            }
            // Phase 1: Ascend
            else if (jumpTopTime < JUMP_TOP_DURATION) {
                jumpSpeed += JUMP_ACCELERATION_UP * deltaTime;
            }

            // Update position
            setY(newY);
        }
    }

    private void updateInvincibility(double deltaTime) {
        // Subtract the time passed from invincibility time
        if (invincibilityTime > 0) {
            invincibilityTime -= deltaTime * 1_000_000_000L;  // Convert deltaTime to nanoseconds
            if (invincibilityTime <= 0) {
                setInvincible(false);  // Reset invincibility once time is up
            }
        }
    }

    // Method to handle the hero's rendering logic
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateJump(deltaTime);
        updateInvincibility(deltaTime);
    }
}
