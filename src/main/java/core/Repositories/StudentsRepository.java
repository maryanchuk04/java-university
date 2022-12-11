package core.Repositories;

import core.Db.MySqlConnection;
import core.Enums.Sex;
import core.Exceptions.ValidationException;
import core.Models.Group;
import core.Models.Student;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentsRepository {
    private static final String STUDENT_TABLE = "Students";
    private static final String PERSON_TABLE = "Persons";
    private static final String GET_ALL = "SELECT * FROM " + STUDENT_TABLE + " ," + PERSON_TABLE;

    public List<Student> getAll() {
        var studentsList = new ArrayList<Student>();
        try (Connection conn = MySqlConnection.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL);
            while (rs.next()) {
                var student = new Student.Builder()
                        .withId(UUID.fromString(rs.getString("id")))
                        .withFirstName(rs.getString("firstName"))
                        .withLastName(rs.getString("lastName"))
                        .withBirthday(LocalDate.parse(rs.getString("birthday")))
                        .withEmail(rs.getString("email"))
                        .withSex(Sex.valueOf(rs.getString("sex")))
                        .build();
                studentsList.add(student);
            }
        } catch (SQLException | ValidationException e) {
            e.printStackTrace();
        }
        return studentsList;
    }


    public Student getById(UUID id) {
        Student student = null;
        String GET_BY_ID = "SELECT * FROM " + STUDENT_TABLE + ", "+ PERSON_TABLE + " WHERE Students.id = '" + id.toString() + "'";
        try(Connection connection = MySqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_BY_ID)) {
            while (rs.next()) {
                student = new Student.Builder()
                        .withId(UUID.fromString(rs.getString("id")))
                        .withFirstName(rs.getString("firstName"))
                        .withLastName(rs.getString("lastName"))
                        .withBirthday(LocalDate.parse(rs.getString("birthday")))
                        .withEmail(rs.getString("email"))
                        .withSex(Sex.valueOf(rs.getString("sex")))
                        .build();
            }
        }catch (SQLException | ValidationException throwable) {
            throwable.printStackTrace();
        }
        return student;
    }

    public Student insertStudent(Student student) {
        var id = UUID.randomUUID();
        String ADD_NEW_PERSON = "INSERT INTO Persons(id, firstName, lastName, birthday, sex)" + "values(?,?,?,?,?)";
        String ADD_NEW_STUDENT = "INSERT INTO Students(email, groupId, specialityId, personId)" + "values(?,?,?,?)";

        try(Connection connection = MySqlConnection.getConnection();
            var statementPerson = connection.prepareStatement(ADD_NEW_PERSON);
            var statementStudent = connection.prepareStatement(ADD_NEW_STUDENT);
            ) {
            statementPerson.setString(1,id.toString());
            statementPerson.setString(2,student.getFirstName());
            statementPerson.setString(3, student.getLastName());
            statementPerson.setString(4, student.getBirthday().toString());
            statementPerson.setString(5, student.getSex().toString());
            statementPerson.executeUpdate();

            statementStudent.setString(1, student.getEmail());

            if(student.getGroup() != null){
                statementStudent.setString(2, student.getGroup().getId().toString());
            }else statementStudent.setString(2, null);

            if(student.getSpeciality() != null){
                statementStudent.setString(3, student.getSpeciality().getId().toString());
            } else statementStudent.setString(3, null);
            statementStudent.setString(4, id.toString());
            statementStudent.executeUpdate();
        }catch (SQLException throwable) {
            throwable.printStackTrace();
            student = null;
        }

        return student;
    }

    public void deleteStudent(UUID id) {
        try(Connection connection = MySqlConnection.getConnection();
        ) {

        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }

    public List<Student> getFiltered(String fieldName, String searchText){
        List<Student> studentList = new ArrayList<>();
        String SEARCH_QUERY = "SELECT * FROM "+ STUDENT_TABLE + ", " + PERSON_TABLE + " WHERE "+ fieldName +" like '%"+searchText+"%' OR firstName LIKE '%"+searchText +"%'";
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SEARCH_QUERY);
        ) {
            while (rs.next()) {
                var student = new Student.Builder()
                        .withId(UUID.fromString(rs.getString("id")))
                        .withFirstName(rs.getString("firstName"))
                        .withLastName(rs.getString("lastName"))
                        .withBirthday(LocalDate.parse(rs.getString("birthday")))
                        .withEmail(rs.getString("email"))
                        .withSex(Sex.valueOf(rs.getString("sex")))
                        .build();
                studentList.add(student);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return studentList;
    }
}
