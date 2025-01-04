package dev.typevs.logic;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import dev.typevs.App;
import dev.typevs.signal.ShapeRenderListener;

public class Player implements ShapeRenderListener {
    public static final int SIZE = 80;
    public static final int FORCE = 300;
    public Rectangle rectangle;
    public float gravity = -10;
    public float force = 0;

    public Player() {
        rectangle = new Rectangle();
        rectangle.setWidth(SIZE);
        rectangle.setHeight(SIZE);
    }

    public void draw(ShapeRenderer renderer) {
        renderer.setColor(Color.YELLOW);
        renderer.rect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public void update(float delta) {
        if (App.state.equals(AppState.Stopped)) return;

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            force = FORCE;
        }
        force += gravity;

        rectangle.setY(rectangle.getY() + force * delta);
    }
}
