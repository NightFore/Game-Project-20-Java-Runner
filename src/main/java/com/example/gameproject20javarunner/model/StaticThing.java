// StaticThing.java

package com.example.gameproject20javarunner.model;

// A class representing a static game element
public abstract class StaticThing extends Thing {
    // Constructor taking initial position, display size, and image file
    public StaticThing(double x, double y, double displayWidth, double displayHeight, String fileName) {
        // Call to the constructor of the parent class Thing
        super(x, y, fileName);

        // Set the display size of the ImageView
        setDisplayWidth(displayWidth);
        setDisplayHeight(displayHeight);
    }
}
