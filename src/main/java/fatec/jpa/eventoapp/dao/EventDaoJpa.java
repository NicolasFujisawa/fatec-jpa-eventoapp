package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.entity.PrivateEvent;
import fatec.jpa.eventoapp.entity.PublicEvent;

import javax.persistence.EntityManager;
import java.sql.Date;

public class EventDaoJpa extends BaseDaoJpa<Event> implements BaseDao<Event> {
    public EventDaoJpa() {
        super();
    }

    public EventDaoJpa(EntityManager em) {
        super(em);
    }

    public Event create(Event event, String name, Date eventDate) {
        event.setName(name);
        event.setEventDate(eventDate);
        return save(event);
    }
}
