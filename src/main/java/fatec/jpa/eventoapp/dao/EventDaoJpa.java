package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Event;

import javax.persistence.EntityManager;
import java.sql.Date;

public class EventDaoJpa extends BaseDaoJpa<Event> implements BaseDao<Event> {
    public EventDaoJpa() {
        super();
    }

    public EventDaoJpa(EntityManager em) {
        super(em);
    }

    public Event create(String name, Date eventDate) {
        Event event = Event.builder()
                .name(name)
                .eventDate(eventDate)
                .build();

        return save(event);
    }
}