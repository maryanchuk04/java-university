package core.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Group extends BaseModel {
    @JsonIgnore
    @Nullable
    private Teacher curator;
    @NotNull
    private int number;
    @NotNull
    private int countStudents;

    public Group() {}

    public Group(Teacher curator, int number, List<Student> students, int countStudents) {
        this.curator = curator;
        this.number = number;
//        this.students = students;
        this.countStudents = countStudents;
    }

    public Group(int number) {
        this.number = number;
    }

    public Teacher getCurator() {
        return curator;
    }

    public void setCurator(Teacher curator) {
        this.curator = curator;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }



    @Override
    public String toString() {
        return "Group{" +
                "\n\t\tcurator=" + curator +
                "\n\t\tnumber=" + number +
                "\n\t\tcountStudents=" + countStudents +
                "\n\t}";
    }
}
