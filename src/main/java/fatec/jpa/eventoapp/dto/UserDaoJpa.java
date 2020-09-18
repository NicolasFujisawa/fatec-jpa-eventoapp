package fatec.jpa.eventoapp.dto;

import fatec.jpa.eventoapp.entity.PersistenceManager;
import fatec.jpa.eventoapp.entity.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class UserDaoJpa implements UserDao {
    private EntityManager em;

    public UserDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public UserDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public User commit(User user) {
        if (user.getId() == null) {
            em.persist(user);
        } else {
            em.merge(user);
        }
        return user;
    }

    @Override
    public User save(User user) {
        try {
            em.getTransaction().begin();
            commit(user);
            em.getTransaction().commit();
            return user;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar Aluno!", e);
        }
    }

    @Override
    public User create(String username) {
        User user = User.builder()
                .name(username)
                .build();
        return save(user);
    }
}
