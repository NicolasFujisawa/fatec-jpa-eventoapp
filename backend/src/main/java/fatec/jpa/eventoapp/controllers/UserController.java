package fatec.jpa.eventoapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fatec.jpa.eventoapp.dao.UserDaoJpa;
import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.entity.PublicEvent;
import fatec.jpa.eventoapp.entity.User;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/users/*")
public class UserController extends BaseController implements BaseControllerInterface {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {

    }

    private User getUserByUsername(String username) {
        UserDaoJpa userDaoJpa = new UserDaoJpa();
        return userDaoJpa.findByName(username);
    }

    private List<PublicEvent> getUserEvents(User user) {
        UserDaoJpa userDaoJpa = new UserDaoJpa();
        return userDaoJpa.getUserPublicEvents(user);
    }
}
