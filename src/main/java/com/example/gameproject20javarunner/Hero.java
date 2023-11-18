// Hero.java

package com.example.gameproject20javarunner;

public class Hero extends AnimatedThing {
    private static final double GRAVITY = 1000;
    private static final double JUMP_INITIAL_SPEED = -400;
    private double speed;
    private final double initialX;
    private final double initialY;
    private boolean isJumping;
    private double jumpSpeed;

    public Hero(double x, double y) {
        super(x, y, 84, 100, 0, 0, 5, 8, 5, 0, "/img/heros.png");
        this.initialX = x; // Initial X position
        this.initialY = y; // Initial Y position
        this.jumpSpeed = 0; // Initial jump speed
        this.isJumping = false; // Jump flag
    }

    // Method for movement with direction
    public void move(double direction) {
        setX(getX() + direction);
    }

    // Setter for speed
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    // Getter for speed
    public double getSpeed() {
        return speed;
    }

    // Method to handle the hero's jump
    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpSpeed = JUMP_INITIAL_SPEED;
        }
    }

    // Method to update the hero's position during a jump
    private void updateJump(double deltaTime) {
        if (isJumping) {
            double newY = getY() + jumpSpeed * deltaTime;
            // Check if the jump height is reached
            if (newY >= initialY) {
                newY = initialY;
                isJumping = false;
                jumpSpeed = 0;
            }
            setY(newY);
            jumpSpeed += GRAVITY * deltaTime; // Apply gravity during the jump
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
