package dev.typevs.scheduling;

public interface Task {
    void call(Float delta);
    TaskState update(Float delta);

    Long getScheduledAt();
    void setScheduledAt(Long scheduled);
}
