package fatec.jpa.eventoapp.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "events")
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "event_date", columnDefinition = "DATETIME")
    private Date eventDate;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
    private List<User> participants;
//
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Notice> notices;
}
