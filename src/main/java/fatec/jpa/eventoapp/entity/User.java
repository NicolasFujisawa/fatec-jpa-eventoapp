package fatec.jpa.eventoapp.entity;

import fatec.jpa.eventoapp.dao.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "username")
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "event_participants",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "event_id")})
    private List<Event> events;
}
