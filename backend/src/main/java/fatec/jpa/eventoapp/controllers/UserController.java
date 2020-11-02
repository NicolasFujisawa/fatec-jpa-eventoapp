package fatec.jpa.eventoapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserController extends BaseController implements BaseControllerInterface {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        String result = objectMapper.writeValueAsString(req.getAttribute("user"));

        statusAndSend(200, res, result);
    }
}
