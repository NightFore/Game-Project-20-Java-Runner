// Hero.java

package com.example.gameproject20javarunner.entity;

import com.example.gameproject20javarunner.manager.FoeManager;
import com.example.gameproject20javarunner.model.MovingThing;
import com.example.gameproject20javarunner.view.Camera;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * A class representing the hero character in the game.
 * Extends the abstract class MovingThing.
 */
public class Hero extends MovingThing {
    // Game Attributes
    private final Camera camera;
    private final Pane root;

    // AnimatedThing Attributes
    private static final double DISPLAY_WIDTH = 144;
    private static final double DISPLAY_HEIGHT = 144;
    private static final double INITIAL_X = 0;
    private static final double INITIAL_Y = 425;
    private static final double FRAME_WIDTH = 48;
    private static final double FRAME_HEIGHT = 48;
    private static final double FRAME_OFFSET_X = 0;
    private static final double FRAME_OFFSET_Y = 0;
    private static final int ATTITUDE = 0;
    private static final int MAX_INDEX = 5;
    private static final int DURATION = 8;
    private static final String SPRITE_SHEET_PATH = "/img/SecretHideout_Gunner/Blue/Gunner_Blue_Run.png";

    // Movement Attributes
    private static final double MOVEMENT_SPEED = 250;

    // Jump Attributes
    private double jumpSpeed;
    private double jumpTopTime;
    private boolean isJumping;
    private static final double INITIAL_JUMP_SPEED = -600;
    private static final double JUMP_ACCELERATION_UP = 1800;
    private static final double JUMP_ACCELERATION_DOWN = 1200;
    private static final double JUMP_TOP_DURATION = 0.10;
    private static final double MAX_JUMP_HEIGHT = 100;

    // Invincibility Attributes
    private long invincibilityTime;  // in nanoseconds

    // Projectile Attributes
    private final List<Projectile> projectiles;
    private static final double PROJECTILE_DIRECTION = 1;

    /**
     * Constructs a Hero with the specified camera and root pane.
     *
     * @param camera The camera used for positioning.
     * @param root   The root pane where the elements are added.
     */
    public Hero(Camera camera, Pane root) {
        // Call the constructor of the parent class MovingThing with initial parameters
        super(camera, root, INITIAL_X, INITIAL_Y, FRAME_WIDTH, FRAME_HEIGHT, FRAME_OFFSET_X, FRAME_OFFSET_Y, ATTITUDE, MAX_INDEX, DURATION, SPRITE_SHEET_PATH);

        // Set the display size of the hero
        setDisplaySize(DISPLAY_WIDTH, DISPLAY_HEIGHT);

        // Initialize game attributes
        this.camera = camera;
        this.root = root;

        // Initialize movement attributes
        setSpeedX(MOVEMENT_SPEED);

        // Initialize jump attributes
        this.jumpSpeed = 0;
        this.jumpTopTime = 0;
        this.isJumping = false;

        // Initialize invincibility attributes
        this.invincibilityTime = 0;

        // Initialize projectile attributes
        this.projectiles = new ArrayList<>();
    }

    /**
     * Method to set the hero to the jump state.
     */
    public void setJump() {
        if (!isJumping) {
            jumpSpeed = INITIAL_JUMP_SPEED;
            jumpTopTime = 0;
            isJumping = true;
        }
    }

    /**
     * Method to update the hero's position during a jump.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateJump(double deltaTime) {
        if (isJumping) {
            double newY = getY() + jumpSpeed * deltaTime;

            // Phase 4: Land
            if (newY > INITIAL_Y) {
                newY = INITIAL_Y;
                isJumping = false;
            }
            // Phase 3: Descend
            else if (jumpTopTime > JUMP_TOP_DURATION) {
                jumpSpeed += JUMP_ACCELERATION_DOWN * deltaTime;
            }
            // Phase 2: Pause
            else if (newY <= INITIAL_Y - MAX_JUMP_HEIGHT) {
                newY = INITIAL_Y - MAX_JUMP_HEIGHT;
                jumpSpeed = 0;
                jumpTopTime += deltaTime;
            }
            // Phase 1: Ascend
            else if (jumpTopTime < JUMP_TOP_DURATION) {
                jumpSpeed += JUMP_ACCELERATION_UP * deltaTime;
            }

            // Update position
            setY(newY);
        }
    }

    /**
     * Checks if the hero is currently invincible.
     *
     * @return True if the hero is invincible, false otherwise.
     */
    public boolean isInvincible() {
        return invincibilityTime > 0;
    }

    /**
     * Sets the hero's invincibility status.
     *
     * @param invincible True to make the hero invincible, false otherwise.
     */
    public void setInvincible(boolean invincible) {
        if (invincible) {
            invincibilityTime = 1000000000L;  // Set invincibility time to 1 second
        } else {
            invincibilityTime = 0;
        }
    }

    /**
     * Updates the remaining time of hero's invincibility.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateInvincibility(double deltaTime) {
        // Subtract the time passed from invincibility time
        if (invincibilityTime > 0) {
            invincibilityTime -= deltaTime * 1_000_000_000L;  // Convert deltaTime to nanoseconds
            if (invincibilityTime <= 0) {
                setInvincible(false);  // Reset invincibility once time is up
            }
        }
    }

    /**
     * Method to create and add a projectile to the hero's list of projectiles.
     */
    public void shootProjectile() {
        Projectile projectile = new Projectile(camera, root);
        double projectileX = getX() + getDisplayWidth();
        double projectileY = getY() + (getDisplayHeight() - projectile.getDisplayHeight()) / 2;
        projectile.setPosition(projectileX, projectileY);
        projectile.setDirectionX(PROJECTILE_DIRECTION);
        projectiles.add(projectile);
    }

    /**
     * Updates the hero's projectiles, removing those that go off-screen.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    private void updateProjectiles(double deltaTime) {
        // Remove projectiles that go off-screen and update the remaining projectiles
        projectiles.removeIf(projectile -> {
            double leftX = projectile.getX() - camera.getX() + projectile.getDisplayWidth();
            double rightX = projectile.getX() - camera.getX();
            boolean offScreen = leftX < -root.getWidth() || rightX > 2 * root.getWidth();
            if (offScreen) {
                projectile.removeFromRoot();
            }
            return offScreen;
        });

        // Update the remaining projectiles
        projectiles.forEach(projectile -> projectile.update(deltaTime));
    }

    /**
     * Draws the hero's projectiles.
     */
    private void drawProjectiles() {
        for (Projectile projectile : projectiles) {
            projectile.draw();
        }
    }

    /**
     * Checks if the hero collides with a foe.
     *
     * @param enemy The foe to check for collision.
     * @return True if the hero collides with the foe, false otherwise.
     */
    public boolean collidesWithEnemy(Foe enemy) {
        return this.getHitboxRectangle().getBoundsInParent().intersects(enemy.getHitboxRectangle().getBoundsInParent());
    }

    /**
     * Checks for collisions between the hero's projectiles and foes.
     *
     * @param foeManager The manager responsible for handling foes.
     */
    public void checkProjectileCollisions(FoeManager foeManager) {
        for (Projectile projectile : projectiles) {
            for (Foe foe : foeManager.getFoes()) {
                if (projectile.collidesWithEnemy(foe)) {
                    // foe.takeDamage();
                    projectile.removeFromRoot();
                }
            }
        }
    }

    /**
     * Updates the hero's rendering logic.
     *
     * @param deltaTime The time elapsed since the last update.
     */
    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        updateJump(deltaTime);
        updateInvincibility(deltaTime);
        updateProjectiles(deltaTime);
    }

    /**
     * Draws the hero and their projectiles.
     */
    @Override
    public void draw() {
        super.draw();
        drawProjectiles();
    }
}
