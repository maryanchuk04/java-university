package core;

import core.Enums.Sex;
import core.Exceptions.ValidationException;
import core.Models.Group;
import core.Models.Mark;
import core.Models.Speciality;
import core.Models.Student;
import core.Repositories.StudentsRepository;
import core.Services.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    private static List<Mark> generateMarks(int count) {
        var rand = new Random(1);
        var list = new ArrayList<Mark>();
        for (int i = 0; i < count; i++) {
            list.add(new Mark(rand.nextInt(12)));
        }
        return list;
    }

    public static void main(String[] args) throws IOException, InstantiationException, IllegalAccessException, SQLException, ValidationException {
        var repository = new StudentsRepository();
        var firstGroup = new Group(1);
        var speciality = new Speciality("Applied Math");
        Student student = new Student.Builder()
                .withFirstName("Maks")
                .withLastName("Maryanchuk")
                .withBirthday(LocalDate.of(2004,01,01))
                .withSex(Sex.Male)
                .withMarks(generateMarks(4))
                .withEmail("lion")
                .build();
        repository.insertStudent(student);

        var studentRepo = new StudentService(new StudentsRepository());
       var list = studentRepo.getAllStudents();
       System.out.println(list);

//        var student = studentRepo.getById(UUID.fromString("4c82e3e0-636c-11ed-bd24-0242ac110002"));
//        System.out.println(student);
    }
}

