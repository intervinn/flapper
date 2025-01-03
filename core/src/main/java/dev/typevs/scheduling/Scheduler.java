package dev.typevs.scheduling;

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Function;

public class Scheduler {
    private ArrayList<Task> callbacks;

    public Scheduler() {
        callbacks = new ArrayList<>();
    }

    public void each(int interval, Function<Float, TaskState> callback) {
        callbacks.add(new ValidatedTask(
            (Float delta) -> callback.apply(delta),
            (ValidatedTask t) -> {
                long now = System.currentTimeMillis();
                if (t.getScheduledAt() == null) {
                    t.setScheduledAt(now);
                }

                if (now - t.getScheduledAt() > interval) {
                    t.setScheduledAt(null);
                    return true;
                }
                return false;
            }
        ));
    }

    public void delay(int delay, Consumer<Float> callback) {
        callbacks.add(new ValidatedTask(
            (Float delta) -> {
                callback.accept(delta);
                return TaskState.Finished;
            },
            (ValidatedTask t) -> {
                long now = System.currentTimeMillis();
                if (now - t.getScheduledAt() > delay) {
                    return true;
                }
                return false;
            }
        ));
    }

    public void update(Float delta) {
        for (int i = 0; i < callbacks.size(); i++) {
            Task callback = callbacks.get(i);

            TaskState state = callback.update(delta);
            if (state.equals(TaskState.Finished)) {
                callbacks.remove(i);
            }
        }
    }
}
