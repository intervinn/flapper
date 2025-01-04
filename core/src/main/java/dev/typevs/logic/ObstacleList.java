package dev.typevs.logic;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.typevs.App;
import dev.typevs.scheduling.Scheduler;
import dev.typevs.signal.ShapeRenderListener;

import java.util.ArrayList;

public class ObstacleList extends ArrayList<Obstacle> implements ShapeRenderListener {
    public static final int SPEED = -70;
    public static final int WIDTH = 80;

    public ObstacleList() {
        super();
    }

    public void update(float delta) {
        if (App.state.equals(AppState.Stopped)) return;

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
        obstacle.move(width + WIDTH);
        add(obstacle);
    }

    public void schedule(Scheduler scheduler) {

    }

    public void draw(ShapeRenderer renderer) {
        for (Obstacle obstacle : this) {
            obstacle.draw(renderer);
        }
    }
}
