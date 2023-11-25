// StaticThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

/**
 * A class representing a static game element.
 * Extends the abstract class Thing.
 */
public abstract class StaticThing extends Thing {
    /**
     * Constructs a StaticThing with the specified camera, root, initial position, display size, and image file.
     *
     * @param camera         The camera used for positioning.
     * @param root           The root pane where the elements are added.
     * @param x              The initial x position.
     * @param y              The initial y position.
     * @param displayWidth   The initial display width.
     * @param displayHeight  The initial display height.
     * @param fileName       The file name of the image resource.
     */
    public StaticThing(Camera camera, Pane root, double x, double y, double displayWidth, double displayHeight, String fileName) {
        // Call to the constructor of the parent class Thing
        super(camera, root, x, y, fileName);

        // Set the display size of the ImageView
        setDisplayWidth(displayWidth);
        setDisplayHeight(displayHeight);
    }
}
