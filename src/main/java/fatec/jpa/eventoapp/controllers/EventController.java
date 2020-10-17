package fatec.jpa.eventoapp.controllers;

import fatec.jpa.eventoapp.dao.EventDaoJpa;
import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.entity.PersistenceManager;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import fatec.jpa.eventoapp.exception.BadRequestException;
import fatec.jpa.eventoapp.exception.NotFoundException;

@WebServlet("/events")
public class EventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        String idParam = req.getParameter("id");

        if (idParam == null) throw new BadRequestException("Id param not found");

        ObjectMapper objectMapper = new ObjectMapper();
        EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
        Event event = eventDaoJpa.findById(Integer.parseInt(idParam));

        if (event == null) throw new NotFoundException("Event not found");

        String result = objectMapper.writeValueAsString(event);
        statusAndSend(200, res, result);
    }

    private void statusAndSend(Integer status, HttpServletResponse res, String data) throws IOException {
        res.setStatus(200);
        res.getWriter().print(data);
        res.getWriter().flush();
    }
}
