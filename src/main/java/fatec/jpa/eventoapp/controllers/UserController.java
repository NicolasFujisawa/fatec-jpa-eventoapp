package fatec.jpa.eventoapp.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import fatec.jpa.eventoapp.dao.UserDaoJpa;
import fatec.jpa.eventoapp.entity.PersistenceManager;
import fatec.jpa.eventoapp.entity.User;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users")
public class UserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        ObjectMapper objectMapper = new ObjectMapper();

        User user = objectMapper.readValue(req.getReader(), User.class);
        UserDaoJpa userDaoJpa = new UserDaoJpa(manager);
        User foundUser = userDaoJpa.findByNameAndPassword(user.getName(), user.getPassword());

        if (foundUser == null) {
            res.setStatus(401);
            res.getWriter().flush();
            return;
        }

        String result = objectMapper.writeValueAsString(foundUser);
        statusAndSend(200, res, result);
    }

    private void statusAndSend(Integer status, HttpServletResponse res, String data) throws IOException {
        res.setStatus(status);
        res.getWriter().print(data);
        res.getWriter().flush();
    }
}
