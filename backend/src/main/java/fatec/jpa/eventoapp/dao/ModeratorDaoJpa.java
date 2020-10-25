package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Moderator;
import fatec.jpa.eventoapp.entity.Notice;
import fatec.jpa.eventoapp.entity.User;
import fatec.jpa.eventoapp.enums.ModeratorLevelEnum;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class ModeratorDaoJpa extends BaseDaoJpa<Moderator> implements BaseDao<Moderator> {
    public ModeratorDaoJpa(EntityManager em) {
        super(em);
    }

    public Moderator find(Integer id) {
        return  em.find(Moderator.class, id);
    }
}
