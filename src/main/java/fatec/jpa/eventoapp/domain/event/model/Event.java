package fatec.jpa.eventoapp.domain.event.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import fatec.jpa.eventoapp.domain.user.model.User;
import lombok.Data;

@Entity
@Table(name = "event")
@Data
public class Event implements Serializable {

    private static final long serialVersionUID = 1165753720446608199L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "eve_id")
    private Long id;

    @Column(name = "eve_tittle")
    private String tittle;

    @Column(name = "eve_about")
    private String about;

    @Column(name = "eve_date", columnDefinition = "DATE")
    private LocalDate date;

    @Column(name = "eve_start", columnDefinition = "TIME")
    private LocalTime start;

    @Column(name = "eve_end", columnDefinition = "TIME")
    private LocalTime end;

    @Column(name = "eve_description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "use_id", nullable = false)
    private User author;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "event_contributors",
            joinColumns = {
                    @JoinColumn(name = "eve_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "use_id")
            })
    private List<User> contributors;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "event_participants",
            joinColumns = {
                    @JoinColumn(name = "eve_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "use_id")
            })
    private List<User> participants;

}
