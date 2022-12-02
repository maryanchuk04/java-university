package core.Models;

import java.util.UUID;

public class Lesson {
    private UUID id = UUID.randomUUID();
    private String name;
    private Teacher teacher;

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }
}

