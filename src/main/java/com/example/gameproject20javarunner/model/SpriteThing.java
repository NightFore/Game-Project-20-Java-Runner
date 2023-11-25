// SpriteThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

public abstract class SpriteThing extends StaticThing {
    // Sprite Attributes
    protected final double frameWidth;
    protected final double frameHeight;
    protected final double frameOffsetX;
    protected final double frameOffsetY;

    /**
     * Constructs a SpriteThing with the specified initial position, display size, frame dimensions, frame offsets, and image file.
     *
     * @param camera         The camera used for positioning.
     * @param root           The root pane where the elements are added.
     * @param x              The initial x position.
     * @param y              The initial y position.
     * @param displayWidth   The width of the displayed image.
     * @param displayHeight  The height of the displayed image.
     * @param frameWidth     The width of each frame in the animation.
     * @param frameHeight    The height of each frame in the animation.
     * @param frameOffsetX   The x offset of the first frame in the animation.
     * @param frameOffsetY   The y offset of the first frame in the animation.
     * @param fileName       The file name of the image resource.
     */
    public SpriteThing(Camera camera, Pane root, double x, double y, double displayWidth, double displayHeight, double frameWidth, double frameHeight, double frameOffsetX, double frameOffsetY, String fileName) {
        // Call to the constructor of the parent class StaticThing
        super(camera, root, x, y, displayWidth, displayHeight, fileName);

        // Set sprite attributes
        this.frameWidth = frameWidth;
        this.frameHeight = frameHeight;
        this.frameOffsetX = frameOffsetX;
        this.frameOffsetY = frameOffsetY;
    }
}
