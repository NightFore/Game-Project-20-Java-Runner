package com.example.gameproject20javarunner.model;


public class MovingThing extends AnimatedThing {
    private double speed;

    public MovingThing(double x, double y, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, int attitude, int maxIndex, int duration, String fileName) {
        super(x, y, frameWidth, frameHeight, frameOffsetX, frameOffsetY, attitude, maxIndex, duration, fileName);
    }
}
