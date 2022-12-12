package core.Models;


import core.Enums.Position;
import jakarta.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Teacher extends Person {
    @Nullable
    private List<Lesson> lessons;
    @Nullable
    private List<Group> groups;
    private Position position;
    private int experience;
    private int salary;

    public List<Lesson> getLessons() {
        return lessons;
    }

    public void setLessons(List<Lesson> lessons) {
        this.lessons = lessons;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Teacher(Builder builder) {
        super(builder);
        this.position = builder.position;
        this.groups = builder.groups;
        this.experience = builder.experience;
        this.lessons = builder.lessons;
        this.salary = builder.salary;
    }

    @Override
    public String toString() {
        var thisPerson = new Person(getFirstName(), getLastName(), getBirthday(), getSex(), getId());
        return "Teacher{" +
                "\n\tperson=" + thisPerson +
                "\n\tlessons=" + lessons +
                "\n\tgroups=" + groups +
                "\n\tposition=" + position +
                "\n\texperience=" + experience +
                "\n\tsalary=" + salary +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Teacher teacher = (Teacher) o;
        return experience == teacher.experience && salary == teacher.salary
                && Objects.equals(lessons, teacher.lessons)
                && Objects.equals(groups, teacher.groups)
                && position == teacher.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(lessons, groups, position, experience, salary);
    }

    public static class Builder extends Person.Builder<Builder> {
        private List<Lesson> lessons;
        private List<Group> groups;
        private Position position;
        private int experience;
        private int salary;


        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withLessons(List<Lesson> lessons) {
            this.lessons = lessons;
            return this;
        }

        public Builder withGroups(List<Group> groups){
            if(this.groups == null){
                this.groups = new ArrayList<>();
                return this;
            }
            this.groups = groups;
            return this;
        }

        public Builder withExperience(int experience){
            this.experience = experience;
            return this;
        }

        public Builder withPosition(Position position){
            this.position = position;
            return this;
        }

        public Builder withSalary(int salary){
            this.salary = salary;
            return this;
        }

        public Teacher build() {
            return new Teacher(this);
        }

    }
}
