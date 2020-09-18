package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Event;

import javax.persistence.EntityManager;

public class EventDaoJpa extends BaseDaoJpa<Event> implements BaseDao<Event> {
    public EventDaoJpa() {
        super();
    }

    public EventDaoJpa(EntityManager em) {
        super(em);
    }
}
