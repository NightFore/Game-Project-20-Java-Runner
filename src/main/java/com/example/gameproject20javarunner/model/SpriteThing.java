package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.geometry.Rectangle2D;
import javafx.scene.layout.Pane;

/**
 * A class representing a game element with sprite animations.
 * Extends the abstract class StaticThing.
 */
public abstract class SpriteThing extends StaticThing {
    // Sprite Attributes
    protected final double spriteWidth;
    protected final double spriteHeight;
    protected final double spriteOffsetX;
    protected final double spriteOffsetY;

    /**
     * Constructs a SpriteThing with the specified initial position, display size, sprite dimensions, sprite offsets, and image file.
     *
     * @param camera         The camera used for positioning.
     * @param root           The root pane where the elements are added.
     * @param x              The initial x position.
     * @param y              The initial y position.
     * @param displayWidth   The width of the displayed image.
     * @param displayHeight  The height of the displayed image.
     * @param spriteWidth    The width of each sprite in the sprite sheet.
     * @param spriteHeight   The height of each sprite in the sprite sheet.
     * @param spriteOffsetX  The x offset of the first sprite in the sprite sheet.
     * @param spriteOffsetY  The y offset of the first sprite in the sprite sheet.
     * @param fileName       The file name of the image resource.
     */
    public SpriteThing(Camera camera, Pane root, double x, double y, double displayWidth, double displayHeight, double spriteWidth, double spriteHeight, double spriteOffsetX, double spriteOffsetY, String fileName) {
        // Call to the constructor of the parent class StaticThing
        super(camera, root, x, y, displayWidth, displayHeight, fileName);

        // Set sprite attributes
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;
        this.spriteOffsetX = spriteOffsetX;
        this.spriteOffsetY = spriteOffsetY;

        // Set the initial viewport of the ImageView to the first sprite
        updateViewport(0, 0);
    }

    /**
     * Method to update the viewport based on the current index.
     */
    protected void updateViewport(int indexX, int indexY) {
        double spriteX = indexX * spriteWidth + spriteOffsetX;
        double spriteY = indexY * spriteHeight + spriteOffsetY;

        Rectangle2D viewport = new Rectangle2D(spriteX, spriteY, spriteWidth, spriteHeight);
        imageView.setViewport(viewport);
    }
}
