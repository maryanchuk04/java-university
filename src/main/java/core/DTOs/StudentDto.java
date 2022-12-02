package core.DTOs;


import com.fasterxml.jackson.annotation.JsonIgnore;
import core.Enums.Sex;
import core.Models.Group;
import core.Models.Mark;
import core.Models.Speciality;

import java.util.ArrayList;
import java.util.List;

public class StudentDto {
    private String firstName;
    private String lastName;
    private String birthday;
    private Sex sex;
    private Group group;
    private Speciality speciality;
    @JsonIgnore
    private List<MarkDto> marks;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Speciality getSpeciality() {
        return speciality;
    }

    public void setSpeciality(Speciality speciality) {
        this.speciality = speciality;
    }

    public List<MarkDto> getMarks() {
        return marks;
    }

    public void setMarks(List<MarkDto> marks) {
        this.marks = marks;
    }

    public StudentDto(){}

    public StudentDto(String firstName, String lastName, String birthday, Sex sex, Group group, Speciality speciality, List<Mark> marks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.sex = sex;
        this.group = group;
        this.speciality = speciality;
        this.marks = new ArrayList<>();
        for (var i: marks) {
            this.marks.add(new MarkDto(i.getValue(), i.getDate().toString()));
        }

    }

    @Override
    public String toString() {
        return "StudentDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex=" + sex +
                ", group=" + group +
                ", speciality=" + speciality +
                ", marks=" + marks +
                '}';
    }
}
