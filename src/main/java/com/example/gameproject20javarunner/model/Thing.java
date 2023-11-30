// Thing.java

package com.example.gameproject20javarunner.model;

import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * An abstract class representing a game element.
 */
public class Thing {
    // Game Attributes
    private final Camera camera;
    private final Pane root;

    // ImageView Attributes
    private double x;
    private double y;
    protected final ImageView imageView;

    // Display Attributes
    private double displayWidth;
    private double displayHeight;
    protected final Rectangle displayRectangle;
    private static final Color DISPLAY_RECTANGLE_COLOR = Color.CYAN;

    // Hitbox Attributes
    private double hitboxWidth;
    private double hitboxHeight;
    protected final Rectangle hitboxRectangle;
    private static final Color HITBOX_RECTANGLE_COLOR = Color.RED;

    /**
     * Constructs a Thing with the specified camera, root, initial position, display size and image file.
     *
     * @param camera        The camera used for positioning.
     * @param root          The root pane where the elements are added.
     * @param initialX      The initial x position.
     * @param initialY      The initial y position.
     * @param displayWidth  The initial display width.
     * @param displayHeight The initial display height.
     * @param image         The image associated with the Thing.
     */
    public Thing(Camera camera, Pane root, double initialX, double initialY, double displayWidth, double displayHeight, Image image) {
        // Set Game attributes
        this.camera = camera;
        this.root = root;

        // Initialize the display rectangle
        displayRectangle = new Rectangle();
        displayRectangle.setStroke(DISPLAY_RECTANGLE_COLOR);
        displayRectangle.setFill(Color.TRANSPARENT);
        displayRectangle.setVisible(false);

        // Initialize the hitbox rectangle
        hitboxRectangle = new Rectangle();
        hitboxRectangle.setStroke(HITBOX_RECTANGLE_COLOR);
        hitboxRectangle.setFill(Color.TRANSPARENT);
        hitboxRectangle.setVisible(false);

        // Initialize the imageView
        imageView = new ImageView(image);
        setInitialPosition(initialX, initialY);
        setDisplaySize(displayWidth, displayHeight);
    }

    /**
     * Sets the initial position of the Thing.
     *
     * @param initialX The initial x position.
     * @param initialY The initial y position.
     */
    public void setInitialPosition(double initialX, double initialY) {
        this.x = initialX;
        this.y = initialY;
        imageView.setX(initialX);
        imageView.setY(initialY);
    }

    /**
     * Getter for the ImageView.
     *
     * @return The ImageView associated with the Thing.
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * Getter for the x position.
     *
     * @return The x position of the Thing.
     */
    public double getX() {
        return x;
    }

    /**
     * Getter for the y position.
     *
     * @return The y position of the Thing.
     */
    public double getY() {
        return y;
    }

    /**
     * Set the x position.
     *
     * @param x The new x position.
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Set the y position.
     *
     * @param y The new y position.
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Set both x and y positions.
     *
     * @param x The new x position.
     * @param y The new y position.
     */
    public void setPosition(double x, double y) {
        setX(x);
        setY(y);
    }

    /**
     * Method to get the surface rectangle.
     *
     * @return The surface rectangle of the Thing.
     */
    public Rectangle getDisplayRectangle() {
        return displayRectangle;
    }

    /**
     * Getter for the display width.
     *
     * @return The display width of the Thing.
     */
    public double getDisplayWidth() {
        return displayWidth;
    }

    /**
     * Getter for the display height.
     *
     * @return The display height of the Thing.
     */
    public double getDisplayHeight() {
        return displayHeight;
    }

    /**
     * Set the display width.
     *
     * @param displayWidth The new display width.
     */
    public void setDisplayWidth(double displayWidth) {
        this.displayWidth = displayWidth;
        imageView.setFitWidth(displayWidth);
        updateDisplayRectangle();
    }

    /**
     * Set the display height.
     *
     * @param displayHeight The new display height.
     */
    public void setDisplayHeight(double displayHeight) {
        this.displayHeight = displayHeight;
        imageView.setFitHeight(displayHeight);
        updateDisplayRectangle();
    }

    /**
     * Set the display size.
     *
     * @param displayWidth  The new display width.
     * @param displayHeight The new display height.
     */
    public void setDisplaySize(double displayWidth, double displayHeight) {
        setDisplayWidth(displayWidth);
        setDisplayHeight(displayHeight);
    }

    /**
     * Method to update the hitbox rectangle.
     */
    private void updateDisplayRectangle() {
        displayRectangle.setWidth(displayWidth);
        displayRectangle.setHeight(displayHeight);
    }

    /**
     * Method to get the hitbox rectangle.
     *
     * @return The hitbox rectangle of the Thing.
     */
    public Rectangle getHitboxRectangle() {
        return hitboxRectangle;
    }

    /**
     * Getter for the display width.
     *
     * @return The display width of the hitbox.
     */
    public double getHitboxWidth() {
        return hitboxWidth;
    }

    /**
     * Getter for the display height.
     *
     * @return The display height of the hitbox.
     */
    public double getHitboxHeight() {
        return hitboxHeight;
    }

    /**
     * Set the hitbox width.
     *
     * @param hitboxWidth The new hitbox width.
     */
    public void setHitboxWidth(double hitboxWidth) {
        this.hitboxWidth = hitboxWidth;
        updateHitboxRectangle();
    }

    /**
     * Set the hitbox height.
     *
     * @param hitboxHeight The new hitbox height.
     */
    public void setHitboxHeight(double hitboxHeight) {
        this.hitboxHeight = hitboxHeight;
        updateHitboxRectangle();
    }

    /**
     * Set the hitbox size.
     *
     * @param hitboxWidth  The new hitbox width.
     * @param hitboxHeight The new hitbox height.
     */
    public void setHitboxSize(double hitboxWidth, double hitboxHeight) {
        setHitboxWidth(hitboxWidth);
        setHitboxHeight(hitboxHeight);
    }

    /**
     * Method to update the hitbox rectangle.
     */
    private void updateHitboxRectangle() {
        hitboxRectangle.setWidth(hitboxWidth);
        hitboxRectangle.setHeight(hitboxHeight);
    }

    /**
     * Sets the viewport of the ImageView.
     *
     * @param viewportX The x-coordinate of the top-left corner of the viewport rectangle.
     * @param viewportY The y-coordinate of the top-left corner of the viewport rectangle.
     * @param viewportWidth The width of the viewport rectangle.
     * @param viewportHeight The height of the viewport rectangle.
     */
    public void setViewport(double viewportX, double viewportY, double viewportWidth, double viewportHeight) {
        imageView.setViewport(new javafx.geometry.Rectangle2D(viewportX, viewportY, viewportWidth, viewportHeight));
    }

    /**
     * Creates and returns a copy of the element.
     *
     * @return The copy of the element.
     */
    public Thing copy() {
        Thing copyThing = new Thing(camera, root, x, y, displayWidth, displayHeight, imageView.getImage());
        copyThing.getImageView().setViewport(imageView.getViewport());
        copyThing.setHitboxSize(hitboxWidth, hitboxHeight);
        return copyThing;
    }

    /**
     * Removes the visual elements from the root pane.
     */
    public void removeFromRoot() {
        root.getChildren().remove(imageView);
        root.getChildren().remove(displayRectangle);
        root.getChildren().remove(hitboxRectangle);
    }

    /**
     * Draw method to update the position based on the camera.
     */
    public void draw() {
        imageView.setX(x - camera.getX());
        imageView.setY(y - camera.getY());
        displayRectangle.setX(x - camera.getX());
        displayRectangle.setY(y - camera.getY());
        hitboxRectangle.setX(x - camera.getX() + (displayWidth - hitboxWidth) / 2);
        hitboxRectangle.setY(y - camera.getY() + (displayHeight - hitboxHeight) / 2);
    }
}
