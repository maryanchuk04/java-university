package core.collections;

import core.Models.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentCollectionService {
    public List<Student> searchCollection(List<Student> students, String name){
        var resList = new ArrayList<Student>();
        var searchText = name.trim();
        for (var student: students) {
            if(student.getFirstName().contains(searchText) || student.getLastName().contains(searchText)){
                resList.add(student);
            }
        }
        return resList;
    }

    public boolean emailIsExist(List<Student> students, String email){
        for (var student:
             students) {
            if(student.getEmail() == email){
                return true;
            }
        }
        return false;
    }
}
