package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Notice;

import javax.persistence.EntityManager;

public class NoticeDaoJpa extends BaseDaoJpa<Notice> implements BaseDao<Notice> {
    public NoticeDaoJpa() {
        super();
    }

    public NoticeDaoJpa(EntityManager em) {
        super(em);
    }
}
