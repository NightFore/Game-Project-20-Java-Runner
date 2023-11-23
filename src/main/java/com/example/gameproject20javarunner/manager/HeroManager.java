// HeroManager.java

package com.example.gameproject20javarunner.manager;

import com.example.gameproject20javarunner.view.Camera;
import com.example.gameproject20javarunner.entity.Hero;
import javafx.scene.layout.Pane;

public class HeroManager {
    private final Hero hero;
    private static final double INITIAL_HERO_X = 0;
    private static final double INITIAL_HERO_Y = 425;

    public HeroManager(Pane root) {
        hero = new Hero(INITIAL_HERO_X, INITIAL_HERO_Y);

        root.getChildren().add(hero.getImageView());
    }

    public void jump() {
        hero.jump();
    }

    public boolean isInvincible() {
        return hero.isInvincible();
    }

    public void setInvincible(boolean invincible) {
        hero.setInvincible(invincible);
    }

    public double getX() {
        return hero.getX();
    }

    public double getY() {
        return hero.getY();
    }

    public void setX(double x) {
        hero.setX(x);
    }

    public void setY(double y) {
        hero.setY(y);
    }

    public void update(double deltaTime) {
        hero.update(deltaTime);
    }

    public void draw(Camera camera) {
        hero.draw(camera);
    }
}
