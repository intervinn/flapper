package dev.typevs.logic;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class ObstacleList extends ArrayList<Obstacle> {
    public static final int SPEED = -20;
    public static final int WIDTH = 50;

    public ObstacleList() {
        super();
    }

    public void update(float delta) {
        for (int i = 0; i < size(); i++) {
            Obstacle obstacle = get(i);
            obstacle.move(SPEED * delta);

            if (obstacle.top.x < -obstacle.top.width) {
                remove(i);
            }
        }
    }

    public void spawn(int width, int height) {
        Obstacle obstacle = Obstacle.random(WIDTH, height);
        obstacle.move(width);
        add(obstacle);
    }

    public void draw(ShapeRenderer renderer) {
        for (Obstacle obstacle : this) {
            obstacle.draw(renderer);
        }
    }
}
