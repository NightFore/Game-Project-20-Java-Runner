// StaticThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

import java.util.Objects;

public class StaticThing extends Thing {
    /**
     * Constructs a StaticThing with the specified camera, root, initial position, display size, and image file.
     *
     * @param camera        The camera used for positioning.
     * @param root          The root pane where the elements are added.
     * @param initialX      The initial x position.
     * @param initialY      The initial y position.
     * @param displayWidth  The initial display width.
     * @param displayHeight The initial display height.
     * @param fileName      The file name of the image.
     */
    public StaticThing(Camera camera, Pane root, double initialX, double initialY, double displayWidth, double displayHeight, String fileName) {
        // Call to the constructor of the parent class Thing
        super(camera, root, initialX, initialY, displayWidth, displayHeight, new javafx.scene.image.Image(Objects.requireNonNull(StaticThing.class.getResourceAsStream(fileName))));

        // Add visual elements to the root pane
        root.getChildren().add(imageView);
        root.getChildren().add(displayRectangle);
        root.getChildren().add(hitboxRectangle);
    }
}
