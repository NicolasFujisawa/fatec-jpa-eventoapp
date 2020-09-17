package fatec.jpa.eventoapp.domain.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import java.util.List;

import com.sun.istack.NotNull;
import fatec.jpa.eventoapp.domain.event.model.Event;
import lombok.Data;

@Entity
@Table(name = "user")
@Data
public class User {

    private static final long serialVersionUID = 2880245626626997404L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "use_id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "use_name", nullable = false)
    private String username;

    @OneToMany(mappedBy = "author")
    private List<Event> events;

    @ManyToMany(mappedBy = "contributors")
    private List<Event> contributions;

    @ManyToMany(mappedBy = "participants")
    private List<Event> participations;
}
