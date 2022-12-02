package core.Interfaces;



import core.Models.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentService {
    List<Student> sortStudentsByMarks();

    List<Student> getAllStudents();

    Student getStudentById(UUID id);
}

