package fatec.jpa.eventoapp.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("1")
public class PrivateEvent extends Event {
}
