package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
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
        event.setEnabled(true);
        return save(event);
    }

    public Event findById(Integer id, boolean enabled) {
        String queryText = "Select eve from Event eve where eve.id = :eventId and eve.enabled = :enabled";
        Query query = em.createQuery(queryText);
        query.setParameter("eventId", id);
        query.setParameter("enabled", enabled);
        try {
            return (Event) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(String.format("[ERROR]: %s", e.getMessage()));
            return null;
        }
    }

    public Event update(Event event) {
        return save(event);
    }

    @Override
    public void delete(Event event) {
        event.setEnabled(false);
        save(event);
    }
}
