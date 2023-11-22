package com.example.gameproject20javarunner;

import javafx.geometry.Rectangle2D;

public class MovingThing extends AnimatedThing {
    private double speed;

    public MovingThing(double x, double y, double width, double height, int attitude, int index, int maxIndex, int duration, double frameOffsetX, double frameOffsetY, String fileName) {
        super(x, y, width, height, attitude, index, maxIndex, duration, frameOffsetX, frameOffsetY, fileName);
    }

    public void move(double deltaTime) {
        setX(getX() + speed * deltaTime);
    }

    // Add more methods or properties as needed

    // Override the getHitBox method to provide a more accurate hitbox for moving things
    @Override
    public Rectangle2D getHitBox() {
        // Assuming a rectangular hitbox based on the current position and size
        return new Rectangle2D(getX(), getY(), getFinalWidth(), getFinalHeight());
    }
}
