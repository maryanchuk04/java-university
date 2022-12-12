package core.Repositories;

import core.Db.MySqlConnection;
import core.Exceptions.ValidationException;
import core.Models.Lesson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LessonsRepository {
    private final String GET_ALL = "SELECT * FROM Lessons";
    private final String ADD_NEW_LESSON = "INSERT INTO Lessons( ?,?,? )" +  "values( ?,?,? )";
    private final TeachersRepository teachersRepository = new TeachersRepository();

    public List<Lesson> getAll(){
        var lessons = new ArrayList<Lesson>();
        try (Connection conn = MySqlConnection.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL);
            while (rs.next()) {
                var lesson = new Lesson();
                lesson.setName(rs.getString("name"));
                lesson.setTeacher(teachersRepository.getById(UUID.fromString(rs.getString("id"))));
                lessons.add(lesson);
            }
        } catch (SQLException | ValidationException e) {
            e.printStackTrace();
        }
        return lessons;
    }


    public Lesson insert(Lesson lesson){
        var id = UUID.randomUUID();
        try(Connection connection = MySqlConnection.getConnection();
            var statement = connection.prepareStatement(ADD_NEW_LESSON);
        ) {
            statement.setString(1, id.toString());
            statement.setString(2, lesson.getName());
            statement.setString(3, lesson.getTeacher().getId().toString());
            statement.executeUpdate();
            return lesson;
        }catch (SQLException throwable) {
            throwable.printStackTrace();
            lesson = null;
        }
        return null;
    }
}
