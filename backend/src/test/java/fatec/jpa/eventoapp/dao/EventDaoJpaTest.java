package fatec.jpa.eventoapp.dao;

import fatec.jpa.eventoapp.entity.Event;
import fatec.jpa.eventoapp.mocks.MockCreation;
import org.hibernate.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class EventDaoJpaTest {
    @InjectMocks
    EventDaoJpa eventDaoJpa;

    @Mock
    private EntityManager em;
    @Mock
    private Transaction transaction;

    @DisplayName("Testing saving valid Post without ID")
    @Test
    void save_success() {
        Event event = MockCreation.eventMock();
        when(eventDaoJpa.em.getTransaction()).thenReturn(transaction);

        doNothing().when(transaction).begin();
        doNothing().when(eventDaoJpa.em).persist(any());
        doNothing().when(transaction).commit();

        eventDaoJpa.save(event);

        verify(eventDaoJpa.em).persist(any());
        verify(transaction).begin();
        verify(transaction).commit();
    }

    @DisplayName("Testing PersistenceException in transaction.begin()")
    @Test
    void save_fail_on_begin() {
        Event event = MockCreation.eventMock();
        when(eventDaoJpa.em.getTransaction()).thenReturn(transaction);

        doThrow(PersistenceException.class).when(transaction).begin();

        Assertions.assertThrows(RuntimeException.class,() -> eventDaoJpa.save(event));
    }

    @DisplayName("Testing PersistenceException in em.persist()")
    @Test
    void save_fail_on_persist() {
        Event event = MockCreation.eventMock();

        when(eventDaoJpa.em.getTransaction()).thenReturn(transaction);
        doThrow(PersistenceException.class).when(eventDaoJpa.em).persist(any());

        Assertions.assertThrows(RuntimeException.class,() -> eventDaoJpa.save(event));
    }

    @DisplayName("Testing PersistenceException in transaction.begin()")
    @Test
    void save_fail_on_commit() {
        Event event = MockCreation.eventMock();
        when(eventDaoJpa.em.getTransaction()).thenReturn(transaction);

        doThrow(PersistenceException.class).when(transaction).commit();

        Assertions.assertThrows(RuntimeException.class,() -> eventDaoJpa.save(event));
    }
}
