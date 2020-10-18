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

    public Event create(Event event, String name, Date eventDate) {
        event.setName(name);
        event.setEventDate(eventDate);
        return save(event);
    }

    public Event findById(Integer id) {
        return this.em.find(Event.class, id);
    }

    public void delete(Event event) {
        this.em.remove(event);
    }

    public Event update(Event event) {
        return save(event);
    }
}
