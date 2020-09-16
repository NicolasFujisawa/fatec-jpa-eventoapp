package fatec.jpa.eventoapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "notices")
public class Notice {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "notice_id")
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "event_id")
    private Event event;
}
