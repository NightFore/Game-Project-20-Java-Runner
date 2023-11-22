// StaticThing.java

package com.example.gameproject20javarunner.model;

public class StaticThing extends Thing{
    public StaticThing(double sizeX, double sizeY, String fileName) {
        super(fileName);

        // Set the size of the ImageView
        imageView.setFitWidth(sizeX);
        imageView.setFitHeight(sizeY);
    }
}
