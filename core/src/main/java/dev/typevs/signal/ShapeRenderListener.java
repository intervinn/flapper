package dev.typevs.signal;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public interface ShapeRenderListener {
    void update(float delta);
    void draw(ShapeRenderer renderer);
}
