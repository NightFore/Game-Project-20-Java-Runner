// StaticThing.java

package com.example.gameproject20javarunner.model;

// A class representing a static game element
public abstract class StaticThing extends Thing {
    // Constructor taking initial position, size, and image file
    public StaticThing(double x, double y, double sizeX, double sizeY, String fileName) {
        // Call to the constructor of the parent class Thing
        super(x, y, fileName);

        // Set the size of the ImageView
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
    }
}
