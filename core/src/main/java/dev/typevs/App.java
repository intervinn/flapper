package dev.typevs;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.ScreenUtils;
import dev.typevs.logic.*;
import dev.typevs.scheduling.Scheduler;
import dev.typevs.scheduling.TaskState;
import dev.typevs.signal.ShapeRenderDispatcher;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class App extends ApplicationAdapter {
    private ShapeRenderer shapeRenderer;
    private Scheduler scheduler;
    private ShapeRenderDispatcher dispatcher;

    public static int width, height;
    public static ObstacleList obstacles;
    public static Player player;
    public static AppState state;

    @Override
    public void create() {
        width = Gdx.graphics.getWidth();
        height = Gdx.graphics.getHeight();
        state = AppState.Playing;

        scheduler = new Scheduler();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        dispatcher = new ShapeRenderDispatcher();

        obstacles = new ObstacleList();
        obstacles.spawn(width, height);
        scheduler.each(4000, this::spawnObstacle);

        player = new Player();

        dispatcher.add(obstacles);
        dispatcher.add(player);
        dispatcher.add(new StopChecker());
    }


    public TaskState spawnObstacle(float delta) {
        obstacles.spawn(width, height);
        return TaskState.Going;
    }

    public void update() {
        float delta = Gdx.graphics.getDeltaTime();
        scheduler.update(delta);
        dispatcher.update(delta);
    }

    @Override
    public void render() {
        update();
        ScreenUtils.clear(0.15f, 0.15f, 0.2f, 1f);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        dispatcher.draw(shapeRenderer);
        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        App.width = width;
        App.height = height;
    }

    @Override
    public void dispose() {
    }
}
