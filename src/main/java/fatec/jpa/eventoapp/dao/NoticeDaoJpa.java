package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.entity.Notice;

import javax.persistence.EntityManager;

public class NoticeDaoJpa extends BaseDaoJpa<Notice> implements BaseDao<Notice> {
    public NoticeDaoJpa() {
        super();
    }

    public NoticeDaoJpa(EntityManager em) {
        super(em);
    }

    public Notice create(String title, String description, Event event) {
        Notice notice = Notice.builder()
                .title(title)
                .description(description)
                .event(event)
                .build();

        return save(notice);
    }
}
