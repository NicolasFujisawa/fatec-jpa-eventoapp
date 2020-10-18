package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Moderator;
import fatec.jpa.eventoapp.entity.Notice;
import fatec.jpa.eventoapp.entity.User;
import fatec.jpa.eventoapp.enums.ModeratorLevelEnum;

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

    public User create(String username, String password) {
        User user = User.builder()
                .name(username)
                .password(password)
                .build();
        return save(user);
    }

    public Moderator create(String username, String password, ModeratorLevelEnum level) {
        Moderator mod = new Moderator();
        mod.setName(username);
        mod.setLevel(level);
        mod.setPassword(password);
        return (Moderator) save(mod);
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

    public User findByNameAndPassword(String name, String password) {
        Query query = em.createQuery("select usr from User usr where usr.name = :username and usr.password = :password", User.class);
        query.setParameter("username", name);
        query.setParameter("password", password);

        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(String.format("[ERROR]: %s", e.getMessage()));
            return null;
        }
    }
}
