package core.collections;

import core.Models.Student;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;

public class StudentCollectionStreamsService {
    public List<Student> searchCollection(List<Student> studentList, String name){
        var searchText = name.trim();
        return studentList.stream().filter(x->x.getFirstName().contains(searchText) || x.getLastName().contains(searchText)).toList();
    }

    public boolean emailIsExist(List<Student> students, String email){
        return students.stream().anyMatch(x->x.getEmail() == email);
    }
}
