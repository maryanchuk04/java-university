package core.Services;


import core.Interfaces.IStudentService;
import core.Models.Student;
import core.Repositories.StudentsRepository;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class StudentService implements IStudentService {
    private final StudentsRepository studentRepository;

    public StudentService(StudentsRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> sortStudentsByMarks() {
        var students = studentRepository.getAll();
        Collections.sort(students, new MarksComparator());
        return students;
    }

    @Override
    public List<Student> getAllStudents() {
        return this.studentRepository.getAll();
    }

    @Override
    public Student getStudentById(UUID id) {
        return this.studentRepository.getById(id);
    }


}
