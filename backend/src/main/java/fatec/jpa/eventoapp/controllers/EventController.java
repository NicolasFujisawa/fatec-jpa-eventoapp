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
public class  EventController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        String idParam = req.getParameter("id");
        if (idParam == null) throw new BadRequestException("Id param not found");

        ObjectMapper objectMapper = new ObjectMapper();
        EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
        Event event = eventDaoJpa.findById(Integer.parseInt(idParam), true);

        if (event == null) throw new NotFoundException("Event not found");

        String result = objectMapper.writeValueAsString(event);
        statusAndSend(200, res, result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        ObjectMapper objectMapper = new ObjectMapper();

        Event event = objectMapper.readValue(req.getReader(), Event.class);
        EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
        eventDaoJpa.create(event, event.getName(), event.getEventDate());
        String createdEvent = objectMapper.writeValueAsString(event);
        String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/events?id=" + event.getId();
        res.addHeader("Location", location);
        statusAndSend(201, res, createdEvent);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        String idParam = req.getParameter("id");

        if (idParam == null) throw new BadRequestException("Id param not found");

        EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
        Event event = eventDaoJpa.findById(Integer.parseInt(idParam), true);
        eventDaoJpa.delete(event);
        status(204, res);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
        ObjectMapper objectMapper = new ObjectMapper();

        String idParam = req.getParameter("id");

        if (idParam == null) throw new BadRequestException("Id param not found");

        Event data = objectMapper.readValue(req.getReader(), Event.class);
        Event event = eventDaoJpa.findById(Integer.parseInt(idParam), true);
        event.setName(data.getName());
        event.setEventDate(data.getEventDate());
        eventDaoJpa.update(event);

        String updatedEvent = objectMapper.writeValueAsString(event);
        statusAndSend(201, res, updatedEvent);
    }

    private void statusAndSend(Integer status, HttpServletResponse res, String data) throws IOException {
        res.setStatus(status);
        res.getWriter().print(data);
        res.getWriter().flush();
    }

    private void status(Integer status, HttpServletResponse res) throws IOException {
        res.setStatus(status);
        res.getWriter().flush();
    }
}
