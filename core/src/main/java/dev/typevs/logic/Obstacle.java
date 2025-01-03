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
        bottom.x += x;
        top.x += x;
        bottom.y += y;
        top.y += y;
    }

    public void move(float x) {
        bottom.x += x;
        top.x += x;
    }

    public static Obstacle random(int width, int height) {
        Random random = new Random();
        Obstacle obstacle = new Obstacle();

        int gap = random.nextInt(height) + Player.SIZE;
        int gapY = random.nextInt(height);

        obstacle.top.width = width;
        obstacle.top.height = height - gapY - gap;

        obstacle.bottom.width = width;
        obstacle.bottom.height = height - obstacle.top.height - gap;

        obstacle.top.x = 0;
        obstacle.top.y = height - obstacle.top.height;
        obstacle.bottom.x = 0;
        obstacle.bottom.y = 0;

        return obstacle;
    }

    public void draw(ShapeRenderer renderer) {
        renderer.setColor(Color.RED);
        renderer.rect(top.x, top.y, top.width, top.height);
        renderer.setColor(Color.BLUE);
        renderer.rect(bottom.x, bottom.y, bottom.width, bottom.height);
    }
}
