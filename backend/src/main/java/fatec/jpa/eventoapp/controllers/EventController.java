package fatec.jpa.eventoapp.controllers;

import fatec.jpa.eventoapp.dao.EventDaoJpa;
import fatec.jpa.eventoapp.dao.UserDaoJpa;
import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.entity.PersistenceManager;

import javax.persistence.EntityManager;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import fatec.jpa.eventoapp.entity.PublicEvent;
import fatec.jpa.eventoapp.entity.User;
import fatec.jpa.eventoapp.exception.BadRequestException;
import fatec.jpa.eventoapp.exception.NotFoundException;

@WebServlet("/events")
public class  EventController extends BaseController implements BaseControllerInterface {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        ObjectMapper objectMapper = new ObjectMapper();
        String result = "";

        String idParam = req.getParameter("id");
        String usernameParam = req.getParameter("username");

        if (idParam != null) {
            EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
            Event event = eventDaoJpa.findById(Integer.parseInt(idParam), true);

            if (event == null) throw new NotFoundException("Event not found");

            result = objectMapper.writeValueAsString(event);
        } else if (usernameParam != null) {
            UserDaoJpa userDaoJpa = new UserDaoJpa(manager);
            User user = userDaoJpa.findByName(usernameParam);

            if (user == null) throw new NotFoundException("User not found");

            List<PublicEvent> eventList = userDaoJpa.getUserPublicEvents(user);

            result = objectMapper.writeValueAsString(eventList);
        }
        statusAndSend(200, res, result);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws IOException {
        EntityManager manager = PersistenceManager.getInstance().getEntityManager();
        ObjectMapper objectMapper = new ObjectMapper();

        User user = (User) req.getAttribute("user");

        PublicEvent event = objectMapper.readValue(req.getReader(), PublicEvent.class);
        EventDaoJpa eventDaoJpa = new EventDaoJpa(manager);
        Event newEvent = eventDaoJpa.create(event, event.getName(), event.getEventDate());
        String createdEvent = objectMapper.writeValueAsString(event);
        String location = req.getServerName() + ":" + req.getServerPort() + req.getContextPath() + "/events?id=" + event.getId();

        List<Event> newEventList = user.getEvents();
        newEventList.add(newEvent);
        user.setEvents(newEventList);

        UserDaoJpa userDaoJpa = new UserDaoJpa(manager);
        userDaoJpa.save(user);

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
}
