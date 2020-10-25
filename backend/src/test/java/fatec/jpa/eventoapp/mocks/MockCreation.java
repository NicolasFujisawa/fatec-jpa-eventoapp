package fatec.jpa.eventoapp.mocks;

import fatec.jpa.eventoapp.entity.Event;
import org.hibernate.Transaction;

import java.sql.Date;

public class MockCreation {

    public static Event eventMock() {
        return Event.builder()
                .name("someEvent")
                .eventDate(new Date(System.currentTimeMillis()))
                .build();
    }
}
