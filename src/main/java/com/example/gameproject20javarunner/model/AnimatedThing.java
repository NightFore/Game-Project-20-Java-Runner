// AnimatedThing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

/**
 * A class representing an animated element in the game.
 * Extends the abstract class StaticThing.
 */
public abstract class AnimatedThing extends SpriteThing {
    // Animation Attributes
    private int index = 0;
    private int spriteDuration = 0;
    private final int attitude;
    private final int maxIndex;
    private final int duration;

    /**
     * Constructs an AnimatedThing with the specified initial position, display size, sprite dimensions, sprite offsets, attitude, max index, duration, and image file.
     *
     * @param camera         The camera used for positioning.
     * @param root           The root pane where the elements are added.
     * @param initialX       The initial x position.
     * @param initialY       The initial y position.
     * @param displayWidth   The width of the displayed image.
     * @param displayHeight  The height of the displayed image.
     * @param spriteWidth    The width of each sprite in the sprite sheet.
     * @param spriteHeight   The height of each sprite in the sprite sheet.
     * @param spriteOffsetX  The x offset of the first sprite in the sprite sheet.
     * @param spriteOffsetY  The y offset of the first sprite in the sprite sheet.
     * @param attitude       The attitude of the element (e.g., running).
     * @param maxIndex       The maximum index of sprites in the animation.
     * @param duration       The total duration of the animation.
     * @param fileName       The file name of the image resource.
     */
    public AnimatedThing(Camera camera, Pane root, double initialX, double initialY, double displayWidth, double displayHeight, double spriteWidth, double spriteHeight, double spriteOffsetX, double spriteOffsetY, int maxIndex, int attitude, int duration, String fileName) {
        // Call to the constructor of the parent class StaticThing
        super(camera, root, initialX, initialY, displayWidth, displayHeight, spriteWidth, spriteHeight, spriteOffsetX, spriteOffsetY, fileName);

        // Set animation attributes
        this.attitude = attitude;
        this.maxIndex = maxIndex;
        this.duration = duration;

        // Set the initial viewport of the ImageView to the first sprite
        updateViewport(index, attitude);
    }

    /**
     * Update method to handle animation logic.
     */
    public void update() {
        // Increment the sprite duration
        spriteDuration++;

        // Check if it's time to change sprites
        if (spriteDuration >= duration) {
            // Reset the duration
            spriteDuration = 0;

            // Move to the next sprite
            index++;

            // Check if we reached the maximum index
            if (index > maxIndex) {
                // Reset to the first sprite
                index = 0;
            }

            // Update the viewport based on the new index
            updateViewport(index, attitude);
        }
    }
}
