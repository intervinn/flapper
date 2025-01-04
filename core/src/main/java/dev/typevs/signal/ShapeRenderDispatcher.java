package dev.typevs.signal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.ArrayList;

public class ShapeRenderDispatcher extends ArrayList<ShapeRenderListener> implements ShapeRenderListener {
    public ShapeRenderDispatcher() {
        super();
    }

    public void update(float delta) {
        this.forEach(listener -> {
            listener.update(delta);
        });
    }

    public void draw(ShapeRenderer renderer) {
        this.forEach(listener -> {
            listener.draw(renderer);
        });
    }
}
