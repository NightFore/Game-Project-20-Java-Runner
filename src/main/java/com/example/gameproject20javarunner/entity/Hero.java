// Hero.java

package com.example.gameproject20javarunner;

import com.example.gameproject20javarunner.model.AnimatedThing;

public class Hero extends AnimatedThing {
    // Variables
    private final double initialX;
    private final double initialY;
    private double jumpSpeed;
    private double jumpTopTime;
    private boolean isJumping;
    private long invincibilityTime;  // in nanoseconds

    // Constants (AnimatedThing)
    private static final String HERO_BLUE_RUN_SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Blue/Gunner_Blue_Run.png";
    private static final double HERO_WIDTH = 48;
    private static final double HERO_HEIGHT = 48;
    private static final double HERO_FINAL_SIZE = 144;
    private static final int ATTITUDE = 0;
    private static final int INDEX = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;

    // Constants (Hero)
    private static final double MOVEMENT_SPEED = 100.0;
    private static final double INITIAL_JUMP_SPEED = -600;
    private static final double JUMP_ACCELERATION_UP = 1800;
    private static final double JUMP_ACCELERATION_DOWN = 1200;
    private static final double JUMP_TOP_DURATION = 0.10;
    private static final double MAX_JUMP_HEIGHT = 100;

    public Hero(double x, double y) {
        super(x, y, HERO_WIDTH, HERO_HEIGHT, ATTITUDE, INDEX, MAX_INDEX, DURATION, FRAME_OFFSET_X, FRAME_OFFSET_Y, HERO_BLUE_RUN_SPRITE_SHEET_PATH);
        setFinalSizeAndAdjustView(HERO_FINAL_SIZE, HERO_FINAL_SIZE);
        this.initialX = x;
        this.initialY = y;
        this.jumpSpeed = 0;
        this.jumpTopTime = 0;
        this.isJumping = false;
        this.invincibilityTime = 0;
    }

    // Method for movement with direction
    public void move(double direction, double deltaTime) {
        setX(getX() + MOVEMENT_SPEED * direction * deltaTime);
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
            invincibilityTime = 25000000000L;  // Set invincibility time to 25 seconds
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

    // Method to handle the hero's rendering logic
    @Override
    public void render(double deltaTime) {
        super.render(deltaTime);
        updateJump(deltaTime);

        // Subtract the time passed from invincibility time
        if (invincibilityTime > 0) {
            invincibilityTime -= deltaTime * 1_000_000_000L;  // Convert deltaTime to nanoseconds
            if (invincibilityTime <= 0) {
                setInvincible(false);  // Reset invincibility once time is up
            }
        }
    }
}
