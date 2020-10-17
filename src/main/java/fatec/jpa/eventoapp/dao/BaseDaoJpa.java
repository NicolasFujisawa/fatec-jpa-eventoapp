package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.BaseEntity;
import fatec.jpa.eventoapp.entity.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import java.util.Optional;

public class BaseDaoJpa<T extends BaseEntity> implements BaseDao<T> {
    protected EntityManager em;

    public BaseDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public BaseDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public T commit(T dado) {
        if (dado.getId() == null) {
            em.persist(dado);
        } else {
            em.merge(dado);
        }
        return dado;
    }

    @Override
    public T save(T dado) {
        try {
            em.getTransaction().begin();
            commit(dado);
            em.getTransaction().commit();
            return dado;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Erro ao salvar " + dado.getClass().getSimpleName(), e);
        }
    }
}
