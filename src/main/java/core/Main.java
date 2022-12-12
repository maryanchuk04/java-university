package core;

import core.Enums.Sex;
import core.Exceptions.ValidationException;
import core.Models.Group;
import core.Models.Mark;
import core.Models.Speciality;
import core.Models.Student;
import core.Repositories.StudentsRepository;
import core.Repositories.TeachersRepository;
import core.Services.StudentService;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

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



        var studentRepo = new StudentService(new StudentsRepository());
       var list = studentRepo.getAllStudents();
       System.out.println(list);

       var st = studentRepo.getStudentById(UUID.fromString("4c82e3e0-636c-11ed-bd24-0242ac110002"));
        System.out.println(st);

        var repo = new TeachersRepository();
        repo.getById(UUID.fromString("e9723775-bb62-4e00-b476-ac50c87f3b68"));
        System.out.println(repo);
    }
}

