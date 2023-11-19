// Hero.java

package com.example.gameproject20javarunner;

public class Hero extends AnimatedThing {
    // Variables
    private final double initialX;
    private final double initialY;
    private double jumpSpeed;
    private double jumpTopTime;
    private boolean isJumping;

    // Constants
    private static final double MOVEMENT_SPEED = 100.0;
    private static final double INITIAL_JUMP_SPEED = -800;
    private static final double JUMP_ACCELERATION_UP = 2400;
    private static final double JUMP_ACCELERATION_DOWN = 1200;
    private static final double JUMP_TOP_DURATION = 0.15;
    private static final double MAX_JUMP_HEIGHT = 100;

    public Hero(double x, double y) {
        super(x, y, 84, 100, 0, 0, 5, 8, 5, 0, "/img/heros.png");
        this.initialX = x;
        this.initialY = y;
        this.jumpSpeed = 0;
        this.jumpTopTime = 0;
        this.isJumping = false;
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

    // Draw method to update the position of the hero based on the camera
    public void draw(Camera camera) {
        getImageView().setX(getX() - camera.getX());
        getImageView().setY(getY() - camera.getY());
    }

    // Method to handle the hero's rendering logic
    @Override
    public void render(double deltaTime) {
        super.render(deltaTime);
        updateJump(deltaTime);
    }
}
