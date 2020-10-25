package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.BaseEntity;
import fatec.jpa.eventoapp.entity.PersistenceManager;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

public class BaseDaoJpa<T extends BaseEntity> implements BaseDao<T> {
    protected EntityManager em;

    public BaseDaoJpa() {
        this(PersistenceManager.getInstance().getEntityManager());
    }

    public BaseDaoJpa(EntityManager em) {
        this.em = em;
    }

    @Override
    public T commit(T data) {
        if (data.getId() == null) {
            em.persist(data);
        } else {
            em.merge(data);
        }
        return data;
    }

    @Override
    public T save(T data) {
        try {
            em.getTransaction().begin();
            commit(data);
            em.getTransaction().commit();
            return data;
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Error to save " + data.getClass().getSimpleName(), e);
        }
    }

    @Override
    public void delete(T data) {
        try {
            em.getTransaction().begin();
            em.remove(data);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace();
            em.getTransaction().rollback();
            throw new RuntimeException("Error to delete " + data.getClass().getSimpleName(), e);
        }
    }
}
