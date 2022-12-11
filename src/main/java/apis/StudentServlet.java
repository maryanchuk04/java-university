package apis;
import ViewModels.ErrorViewModel;
import com.google.gson.*;
import com.google.gson.Gson;
import core.Models.Group;
import core.Models.Student;
import core.Repositories.StudentsRepository;
import core.Services.StudentService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "StudentServlet", value = "/StudentServlet")
public class StudentServlet extends HttpServlet {
    private StudentService studentService;
    private Gson gson;

    @Override
    public void init() throws ServletException {
        super.init();
        gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new LocalDateSerializer()).create();
        studentService = new StudentService(new StudentsRepository());
    }

    @Override
    protected void doGet(javax.servlet.http.HttpServletRequest req, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        response.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        var search = req.getParameter("searchText");

        if(search == null){
            getAllRequest(response);
            return;
        }
        searchRequest(search, response);
    }

    @Override
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws ServletException, IOException {
        StringBuilder buffer = new StringBuilder();
        BufferedReader reader = request.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            buffer.append(line);
            buffer.append(System.lineSeparator());
        }
        String data = buffer.toString();
        System.out.println(data);
        var res = gson.fromJson(data, Student.class);
        try {
            var student = studentService.insertStudent(res);
            response.setStatus(200);
            var json = gson.toJson(student);
            PrintWriter out = response.getWriter();
            out.println(json);
        }catch (NullPointerException e){
            response.setStatus(400);
            PrintWriter out = response.getWriter();
            out.println(gson.toJson(new ErrorViewModel(e.getMessage())));
        }
    }

    private void getAllRequest(HttpServletResponse response) throws IOException{
        var list = studentService.getAllStudents();
        String jsonData = gson.toJson(list);
        PrintWriter out = response.getWriter();
        try {
            out.println(jsonData);
        }
        catch (Exception e){
            response.setStatus(400);
        }
        finally {
            out.close();
        }
    }

    private void searchRequest(String text, HttpServletResponse response) throws IOException {
        var list = studentService.getFiltered(text);
        String jsonData = gson.toJson(list);
        PrintWriter out = response.getWriter();
        try {
            out.println(jsonData);
        }
        catch (Exception e){
            response.setStatus(400);
        }
        finally {
            out.close();
        }
    }

}

class LocalDateSerializer implements JsonSerializer <LocalDate> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");

    @Override
    public JsonElement serialize(LocalDate localDate, Type srcType, JsonSerializationContext context) {
        return new JsonPrimitive(formatter.format(localDate));
    }
}


