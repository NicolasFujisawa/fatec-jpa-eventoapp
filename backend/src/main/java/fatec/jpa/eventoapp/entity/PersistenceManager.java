package fatec.jpa.eventoapp.entity;

import lombok.NoArgsConstructor;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

@NoArgsConstructor
public class PersistenceManager {
    private static PersistenceManager instance;

    protected EntityManagerFactory emf;

    public static PersistenceManager getInstance() {
        if (instance == null) {
            instance = new PersistenceManager();
        }
        return instance;
    }

    public EntityManagerFactory getEntityManagerFactory() {
        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf;
    }

    public EntityManager getEntityManager() {
        if (emf == null) {
            createEntityManagerFactory();
        }
        return emf.createEntityManager();
    }

    private void createEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("defaultEntityManagerFactory");
    }
}
