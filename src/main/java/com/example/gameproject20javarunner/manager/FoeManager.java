// FoeManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.view.Camera;
import com.example.gameproject20javarunner.entity.Foe;
import com.example.gameproject20javarunner.entity.Hero;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FoeManager {
    // Game Attributes
    private final Camera camera;
    private final Pane root;
    private final List<Foe> foes;

    // Constants (Foe)
    private static final int MIN_FOES = 50;
    private static final int MAX_FOES = 100;
    private static final float FOE_MIN_X = 800;
    private static final float FOE_MAX_X = 1600;
    private static final float FOE_MIN_Y = 400;
    private static final float FOE_MAX_Y = 450;

    public FoeManager(Camera camera, Pane root) {
        this.camera = camera;
        this.root = root;
        foes = new ArrayList<>();
        addRandomFoes();
    }

    // Add a random number of Foes to the list
    public void addRandomFoes() {
        Random random = new Random();
        int numberOfFoes = random.nextInt(MAX_FOES - MIN_FOES + 1) + MIN_FOES;

        for (int i = 0; i < numberOfFoes; i++) {
            double x = random.nextDouble() * (FOE_MAX_X - FOE_MIN_X) + FOE_MIN_X;
            double y = random.nextDouble() * (FOE_MAX_Y - FOE_MIN_Y) + FOE_MIN_Y;
            addSingleFoe(x, y);
        }
    }

    public void addSingleFoe(double x, double y) {
        Foe foe = new Foe(x, y);
        foes.add(foe);
        root.getChildren().add(foe.getImageView());
        root.getChildren().add(foe.getHitboxRectangle());
    }

    public void checkHeroCollisions(Hero hero) {
        for (Foe foe : foes) {
            if (hero.collidesWithEnemy(foe)) {
                System.out.println("Test");
            }
        }
    }

    public List<Foe> getFoes() {
        return foes;
    }

    public void update(double deltaTime) {
        for (Foe foe : foes) {
            foe.update(deltaTime);
        }
    }

    public void draw() {
        for (Foe foe : foes) {
            foe.draw(camera);
        }
    }
}
