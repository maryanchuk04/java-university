package core.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class Group extends BaseModel {
    @NotNull
    private int number;
    @NotNull
    private int countStudents;

    public Group() {}

    public Group(Teacher curator, int number, List<Student> students, int countStudents) {
        this.number = number;
//        this.students = students;
        this.countStudents = countStudents;
    }

    public Group(int number) {
        this.number = number;
    }

    public Group(UUID id, int number){
        this.setId(id);
        this.number = number;
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
                "\n\t\tnumber=" + number +
                "\n\t\tcountStudents=" + countStudents +
                "\n\t}";
    }
}
