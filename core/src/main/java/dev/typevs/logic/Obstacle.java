package dev.typevs.logic;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import java.util.Random;

public class Obstacle {
    public Rectangle top;
    public Rectangle bottom;

    public Obstacle() {
        top = new Rectangle();
        bottom = new Rectangle();
    }

    public void move(float x, float y) {
        bottom.setX(bottom.getX() + x);
        top.setX(top.getX() + x);
        bottom.setY(bottom.getY() + y);
        top.setY(top.getY() + y);
    }

    public void move(float x) {
        bottom.setX(bottom.getX() + x);
        top.setX(top.getX() + x);
    }

    public static Obstacle random(int width, int height) {
        Random random = new Random();
        Obstacle obstacle = new Obstacle();

        int gap = random.nextInt(height) + (int)(Player.SIZE * 1.5);
        int gapY = random.nextInt(height - Player.SIZE);

        obstacle.top.setWidth(width);
        obstacle.top.setHeight(height - gapY - gap);

        obstacle.bottom.setWidth(width);
        obstacle.bottom.setHeight(height - obstacle.top.height - gap);

        obstacle.top.setX(0);
        obstacle.top.setY(height - obstacle.top.height);
        obstacle.bottom.setX(0);
        obstacle.bottom.setY(0);

        return obstacle;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.rect(top.x, top.y, top.width, top.height);
        renderer.setColor(Color.BLUE);
        renderer.rect(bottom.x, bottom.y, bottom.width, bottom.height);
    }
}
