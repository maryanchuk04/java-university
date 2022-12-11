package core.Services;

import core.Models.Group;
import core.Repositories.GroupRepository;

import java.sql.SQLException;
import java.util.List;

public class GroupService {
    private final GroupRepository groupRepository = new GroupRepository();

    public List<Group> searchGroup(int number){
        return groupRepository.getFiltered("groupNumber", String.valueOf(number));
    }

    public Group insertGroup(Group group){
        var res = groupRepository.insertGroup(group);
        if(res == null){
            throw new NullPointerException("Error in insert data");
        }
        return res;
    }
}
