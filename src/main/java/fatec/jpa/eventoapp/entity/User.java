package fatec.jpa.eventoapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username")
    private String name;

//    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "participants")
//    @JoinTable(name = "event_participants",
//            joinColumns = {@JoinColumn(name = "event_id")},
//            inverseJoinColumns = {@JoinColumn(name = "user_id")})
//    private List<Event> events;
}
