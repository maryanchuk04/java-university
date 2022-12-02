package core.Models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import core.Enums.Sex;
import core.Exceptions.ValidationException;
import core.Interfaces.TxtFormat;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.Email;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Student extends Person implements TxtFormat<Student>, Comparable<Student> {
    @Nullable
    private Group group;
    @Nullable
    private Speciality speciality;
    @Nullable
    private List<Mark> marks;
    @Email(message = "Student must have email address")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Mark> getMarks() {
        return marks;
    }

    public void setMarks(List<Mark> marks) {
        this.marks = marks;
    }

    public Student(Builder builder) {
        super(builder);
        this.group = builder.group;
        this.speciality = builder.speciality;
        this.marks = builder.marks;
        this.email = builder.email;
    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Student(@JsonProperty("firstName") String name,
                   @JsonProperty("lastName") String lastName,
                   @JsonProperty("birthday") LocalDate birthday,
                   @JsonProperty("sex") Sex sex,
                   @JsonProperty("group") Group group,
                   @JsonProperty("speciality") Speciality speciality) {
        this.setFirstName(name);
        this.setLastName(lastName);
        this.setBirthday(birthday);
        this.setSex(sex);
        this.group = group;
        this.speciality = speciality;
    }

    public Student(){
        super();
    }

    public Student(Student student){
        super(student.getFirstName(),student.getLastName(), student.getBirthday(), student.getSex(), student.getId());
        this.group = student.getGroup();
        this.speciality = student.getSpeciality();
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

    @Override
    public String toString() {
        var thisPerson = new Person(getFirstName(), getLastName(),getBirthday(),getSex(), getId());
        String marksStr = (marks == null) ? "marks list is clear" : marks.toString();
        String groupStr = (group == null) ? "student dont have group" : group.toString();
        String specialityStr =  speciality ==null ? "student dont have speciality" : speciality.toString();
        return "Student{\n" +
                "\tperson = " + thisPerson.toString()+
                "\n\tid = " + getId().toString() +
                "\n\tgroup=" + groupStr +
                "\n\tspeciality=" + specialityStr +
                "\n\tMarks = "  + marksStr +
                "\n}";
    }


    @Override
    public String toStringSerialize() {
        return "firstName = " + this.getFirstName() +
                ",lastName = " + this.getLastName() +
                ",birthday=" + this.getBirthday() +
                ",sex=" + this.getSex() +
                ",groupNumber=" + group.getNumber() +
                ",speciality=" + speciality.getName();
    }

    @Override
    public Student toObject(String string) throws ValidationException {
        String[] str = string.split(",");
        var values = new ArrayList<String>();
        for (String item : str) {
            String[] innerItem=item.split("=");
            values.add(innerItem[1]);
        }
        for (var i :
             values) {
            i.trim();
        }

        var student = new Student.Builder()
                .withFirstName(values.get(0))
                .withLastName(values.get(1))
                .withBirthday(LocalDate.parse(values.get(2)))
                .withSex(Sex.valueOf(values.get(3)))
                .withGroup(new Group(Integer.parseInt(values.get(4))))
                .withSpeciality(new Speciality(values.get(5)))
                .build();

        return student;
    }

    @Override
    public int compareTo(Student o) {
        if (this.getBirthday().getYear() != o.getBirthday().getYear()) {
            if (this.getBirthday().getYear() > o.getBirthday().getYear())
                return 1;
            else
                return -1;
        }
        if (this.getBirthday().getMonth() != o.getBirthday().getMonth()) {
            if (this.getBirthday().getMonthValue() > o.getBirthday().getMonthValue())
                return 1;
            else
                return -1;
        }
        if (this.getBirthday().getMonth() != o.getBirthday().getMonth()) {
            if (this.getBirthday().getMonthValue() > o.getBirthday().getMonthValue())
                return 1;
            else
                return -1;
        }
        return 0;
    }

    public static class Builder extends Person.Builder<Builder> {
        private Group group;
        private Speciality speciality;
        private List<Mark> marks;
        private String email;

        @Override
        public Builder getThis() {
            return this;
        }

        public Builder withSpeciality(Speciality speciality) {
            this.speciality = speciality;
            return this;
        }

        public Builder withGroup(Group group){
            this.group = group;
            return this;
        }

        public Builder withMarks(List<Mark> marks){
            this.marks = marks;
            return this;
        }

        public Builder withEmail(String email){
            this.email = email;
            return this;
        }

        public Student build() throws ValidationException {
            var student = new Student(this);
            validate(student);
            return student;
        }

        public Student validate(Student obj) throws ValidationException {
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            Validator validator = factory.getValidator();
            Set<ConstraintViolation<Student>> violations = validator.validate(obj);
            if (violations.isEmpty())
                return obj;

            StringBuilder sb=new StringBuilder("\n");
            for (var violation : violations) {
                sb.append("Invalid value : ").append(violation.getInvalidValue()).append(" " + violation.getMessage() + "\n");
            }
            throw new ValidationException(sb.toString());
        }
    }

}