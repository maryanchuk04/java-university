package core.Repositories;

import core.Db.MySqlConnection;

import core.Exceptions.ValidationException;
import core.Models.Group;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GroupRepository {
    private static final String GROUP_TABLE = "Groupes";
    private static final String GET_ALL = "SELECT * FROM " + GROUP_TABLE;

    public List<Group> getAll() {
        var groups = new ArrayList<Group>();
        try (Connection conn = MySqlConnection.getConnection()) {
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(GET_ALL);
            while (rs.next()) {
                var group = new Group(UUID.fromString(rs.getString("id")),
                        Integer.parseInt(rs.getString("groupNumber")));
                groups.add(group);
            }
        } catch (SQLException | ValidationException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Group insertGroup(Group group) {
        var id = UUID.randomUUID();
        String ADD_NEW_GROUP = "INSERT INTO Groupes(id, groupNumber)" + "values(?,?)";

        try (Connection connection = MySqlConnection.getConnection();
             var statement = connection.prepareStatement(ADD_NEW_GROUP);
        ) {
            statement.setString(1, id.toString());
            statement.setString(2, String.valueOf(group.getNumber()));
            statement.executeUpdate();
            group.setId(id);
        } catch (SQLException throwable) {
            throwable.printStackTrace();
            group = null;
        }
        return group;
    }

    public List<Group> getFiltered(String fieldName, String searchText){
        String SEARCH_QUERY = "SELECT * FROM "+ GROUP_TABLE + " WHERE "+ fieldName +" like '%"+searchText+"%'";
        var groups = new ArrayList<Group>();
        try (Connection connection = MySqlConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet rs = statement.executeQuery(SEARCH_QUERY);
        ) {
            while (rs.next()) {
                var group = new Group(UUID.fromString(rs.getString("id")),
                        Integer.parseInt(rs.getString("groupNumber")));
                groups.add(group);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return groups;
    }
}
