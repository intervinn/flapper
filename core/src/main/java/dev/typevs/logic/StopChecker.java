package dev.typevs.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import dev.typevs.App;
import dev.typevs.signal.ShapeRenderListener;

public class StopChecker implements ShapeRenderListener {
    public void draw(ShapeRenderer renderer) {}

    public void update(float delta) {

        if (App.state.equals(AppState.Playing)) {
            App.obstacles.forEach(obstacle -> {
                if (App.player.rectangle.overlaps(obstacle.top) || App.player.rectangle.overlaps(obstacle.bottom)) {
                    App.state = AppState.Stopped;
                }
            });

            if (App.player.rectangle.getY() < 0) {
                App.state = AppState.Stopped;
            }
        }

        if (App.state.equals(AppState.Stopped)) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
                System.out.println("stop");
                this.reset();
            }
        }
    }



    public void reset() {
        App.obstacles.clear();
        App.player.rectangle.x = 0;
        App.player.rectangle.y = (float)App.height/2;
        App.state = AppState.Playing;
        App.player.force = Player.FORCE;
    }
}
