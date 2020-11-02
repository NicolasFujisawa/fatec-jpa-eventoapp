package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.*;
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
                .enabled(true)
                .build();
        return save(user);
    }

    public Moderator create(String username, String password, ModeratorLevelEnum level) {
        Moderator mod = new Moderator();
        mod.setName(username);
        mod.setLevel(level);
        mod.setPassword(password);
        mod.setEnabled(true);
        return (Moderator) save(mod);
    }

    public List<Notice> getNoticesFromFutureUserEvents(User user) {
        String queryText = "select ntc from User usr\n" +
                "join usr.events eve\n" +
                "join eve.notices ntc\n" +
                "where usr = :user and\n" +
                "eve.eventDate >= :date and\n" +
                "eve.enabled = true";

        Query query = em.createQuery(queryText);
        query.setParameter("user", user);
        query.setParameter("date", new Date(System.currentTimeMillis()));

        return query.getResultList();
    }

    public List<PublicEvent> getUserPublicEvents(User user) {
        String queryText = "select eve from User usr \n" +
            "join usr.events eve \n" +
            "where usr = :user \n" +
            "and eve.enabled = true \n" +
            "and TYPE(eve) = :type";

        Query query = em.createQuery(queryText);
        query.setParameter("user", user);
        query.setParameter("type", PublicEvent.class);

        try {
            return query.getResultList();
        } catch (Exception e) {
            System.out.println(String.format("[ERROR]: %s", e.getMessage()));
            return null;
        }
    }

    public User findByNameAndPassword(String name, String password) {
        Query query = em.createQuery("select usr from User usr where usr.enabled = true and usr.name = :username and usr.password = :password", User.class);
        query.setParameter("username", name);
        query.setParameter("password", password);

        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(String.format("[ERROR]: %s", e.getMessage()));
            return null;
        }
    }

    public User findByName(String name) {
        String queryText = "select usr from User usr \n" +
                "where usr.enabled = true \n" +
                "and usr.name = :username";

        Query query = em.createQuery(queryText, User.class);
        query.setParameter("username", name);

        try {
            return (User) query.getSingleResult();
        } catch (Exception e) {
            System.out.println(String.format("[ERROR]: %s", e.getMessage()));
            return null;
        }
    }

    @Override
    public void delete(User user) {
        user.setEnabled(false);
        save(user);
    }
}
