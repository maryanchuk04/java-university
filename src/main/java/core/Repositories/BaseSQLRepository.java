package core.Repositories;

import core.Db.MySqlConnection;

import java.sql.*;

public interface BaseSQLRepository {
    static void createAndDelete(String query){
        try (Connection connection = MySqlConnection.getConnection();
             Statement stmt = connection.createStatement()) {
             stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }

    static String getIdByQuery(String query){
        String id = null;
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(query)) {
            while (rs.next()) {
                id = rs.getString("id");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    static void deleteByQuery(String query){
        try(Connection connection = MySqlConnection.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.execute();
        }catch (SQLException throwable) {
            throwable.printStackTrace();
        }
    }
}
