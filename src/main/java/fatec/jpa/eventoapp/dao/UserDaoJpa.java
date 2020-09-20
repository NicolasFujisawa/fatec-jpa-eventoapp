package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Notice;
import fatec.jpa.eventoapp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.sql.Date;
import java.util.List;

public class UserDaoJpa extends BaseDaoJpa<User> implements BaseDao<User> {
    public UserDaoJpa() {
        super();
    }

    public UserDaoJpa(EntityManager em) {
        super(em);
    }

    public User create(String username) {
        User user = User.builder()
                .name(username)
                .build();
        return save(user);
    }

    public List<Notice> getNoticesFromFutureUserEvents(User user) {
        String queryText = "select ntc from User usr\n" +
                "join usr.events eve\n" +
                "join eve.notices ntc\n" +
                "where usr = :user and\n" +
                "eve.eventDate >= :date";

        Query query = em.createQuery(queryText);
        query.setParameter("user", user);
        query.setParameter("date", new Date(System.currentTimeMillis()));

        return query.getResultList();
    }
}
