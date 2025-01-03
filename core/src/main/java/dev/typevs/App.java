package dev.typevs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.typevs.logic.Obstacle;
import dev.typevs.logic.ObstacleList;
import dev.typevs.scheduling.Scheduler;
import dev.typevs.scheduling.TaskState;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class App extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Scheduler scheduler;
    private int width, height;

    private ObstacleList obstacles;

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();

        scheduler = new Scheduler();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        obstacles = new ObstacleList();

        obstacles.spawn(width, height);
        scheduler.each(7000, this::spawnObstacle);
    }


    public TaskState spawnObstacle(float delta) {
        obstacles.spawn(width, height);
        return TaskState.Going;
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        scheduler.update(delta);
        obstacles.update(delta);
    }

    @Override
    public void render() {
        update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        obstacles.draw(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void dispose() {
    }
}
