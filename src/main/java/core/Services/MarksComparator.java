package core.Services;


import core.Models.Student;

import java.util.Comparator;
import java.util.List;

public class MarksComparator implements Comparator<Student> {
    @Override
    public int compare(Student student1, Student student2) {
        var averageMarkStudent1 = calcAverageMark(student1.getMarks().stream().map(x->x.getValue()).toList());
        var averageMarkStudent2 = calcAverageMark(student2.getMarks().stream().map(x->x.getValue()).toList());
        return Double.compare(averageMarkStudent1, averageMarkStudent2);
    }

    private static double calcAverageMark(List<Integer> marks){
        return marks.stream().mapToInt(x->x.intValue()).sum()/marks.size();
    }
}
