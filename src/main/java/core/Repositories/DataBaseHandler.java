package core.Repositories;

import core.Db.MySqlConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class DataBaseHandler {
    private final static String CREATE_PERSONS_TABLE = "firstName varchar(40) not null, lastName varchar(40), birthday Date, sex ENUM ('Male', 'Female')";
    private final static String CREATE_STUDENTS_TABLE = "email varchar(40) not null, groupId varchar(50), specialityId varchar(50), personId varchar(50)";
    private final static String CREATE_MARKS_TABLE = "value int not null, studentId varchar(50), date DATE";
    private final static String CREATE_TEACHERS_TABLE = "position ENUM('Director', 'Teacher', 'Head_teacher'), experience int, salary int, personId varchar(50)";
    private final static String CREATE_GROUP_TABLE= "groupNumber int, countStudents int ";
    private final static String CREATE_LESSONS_TABLE = "name varchar(50), teacherId varchar(50)";
    private final static String CREATE_SPECIALITY_TABLE = "name varchar(50)";

    private static void createTable(String tableName, String createTableDetails){
        try (Connection conn = MySqlConnection.getConnection();
             var statement = conn.createStatement();) {
            String sql = "CREATE TABLE " + tableName +
                    "(id varchar(50) default(uuid()) PRIMARY KEY, " +
                    createTableDetails + ")";
            statement.executeUpdate(sql);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void createAllTables(){
        createTable("Students", CREATE_STUDENTS_TABLE);
        createTable("Persons", CREATE_PERSONS_TABLE);
        createTable("Marks", CREATE_MARKS_TABLE);
        createTable("Lessons", CREATE_LESSONS_TABLE);
        createTable("Speciality", CREATE_SPECIALITY_TABLE);
        createTable("Teachers", CREATE_TEACHERS_TABLE);
        createTable("Groupes", CREATE_GROUP_TABLE);
    }
}
