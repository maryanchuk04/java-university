package core.Repositories;

import core.Db.MySqlConnection;
import core.Enums.Position;
import core.Enums.Sex;
import core.Exceptions.ValidationException;
import core.Models.Student;
import core.Models.Teacher;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.UUID;

public class TeachersRepository {
    private final String GET_BY_ID = "SELECT * FROM Teachers, Persons WHERE Teachers.id = ";
    private final String TEACHERS_TABLE = "Teachers";


    public Teacher getById(UUID id){
        Teacher teacher = null;
        try(Connection connection = MySqlConnection.getConnection();
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(GET_BY_ID + "'"+ id.toString() +"'")) {
            while (rs.next()) {
                teacher = new Teacher.Builder()
                        .withId(UUID.fromString(rs.getString("id")))
                        .withFirstName(rs.getString("firstName"))
                        .withLastName(rs.getString("lastName"))
                        .withBirthday(LocalDate.parse(rs.getString("birthday")))
                        .withSex(Sex.valueOf(rs.getString("sex")))
                        .withExperience(Integer.parseInt(rs.getString("experience")))
                        .withPosition(Position.valueOf(rs.getString("position")))
                        .withSalary(Integer.parseInt(rs.getString("salary")))
                        .build();
            }
        }catch (SQLException | ValidationException throwable) {
            throwable.printStackTrace();
        }
        return teacher;
    }
}
