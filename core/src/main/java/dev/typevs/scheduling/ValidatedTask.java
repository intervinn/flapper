package dev.typevs.scheduling;

import java.util.function.Function;

public class ValidatedTask implements Task {
    private Long scheduledAt;
    private final Function<Float, TaskState> callback;
    private final Function<ValidatedTask, Boolean> validator;

    public ValidatedTask(Function<Float, TaskState> callback, Function<ValidatedTask, Boolean> validator) {
        this.callback = callback;
        this.validator = validator;
        scheduledAt = System.currentTimeMillis();
    }

    public void call(Float delta) {
        callback.apply(delta);
    }

    public Long getScheduledAt() {
        return scheduledAt;
    }

    public void setScheduledAt(Long scheduled) {
        this.scheduledAt = scheduled;
    }

    public TaskState update(Float delta) {
        if (validator.apply(this)) {
            return callback.apply(delta);
        }
        return TaskState.Going;
    }
}
