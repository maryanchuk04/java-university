package apis;

import ViewModels.ErrorViewModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import core.Models.Group;
import core.Repositories.GroupRepository;
import core.Services.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDate;

@WebServlet(name = "GroupServlet", value = "/GroupServlet")
public class GroupServlet extends HttpServlet {
    private final GroupRepository groupRepository = new GroupRepository();
    private final GroupService groupService = new GroupService();
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");
        if(req.getParameter("searchText") == null){
            var list = groupRepository.getAll();
            String jsonData = gson.toJson(list);
            PrintWriter out = resp.getWriter();
            try {
                out.println(jsonData);
            } catch (Exception e) {
                resp.setStatus(400);
            } finally {
                out.close();
            }
            return;
        }

        var search = req.getParameter("searchText");
        var res = groupService.searchGroup(Integer.parseInt(search));
        String jsonData = gson.toJson(res);
        PrintWriter out = resp.getWriter();
        try {
            out.println(jsonData);
        } catch (Exception e) {
            resp.setStatus(400);
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        resp.setContentType("application/json");
        resp.setCharacterEncoding("utf-8");

        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();
        var res = gson.fromJson(data, Group.class);
        try{
            var group = groupService.insertGroup(res);
            resp.setStatus(200);
            var json = gson.toJson(group);
            PrintWriter out = resp.getWriter();
            out.println(json);
        }catch (NullPointerException e){
            resp.setStatus(400);
            PrintWriter out = resp.getWriter();
            out.println(gson.toJson(new ErrorViewModel(e.getMessage())));
        }
    }
}
