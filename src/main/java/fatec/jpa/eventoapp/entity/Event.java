package fatec.jpa.eventoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id", column = @Column(name = "event_id"))
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
        name = "event_type",
        discriminatorType = DiscriminatorType.INTEGER)
public class Event extends BaseEntity {
    @Column(name = "name")
    private String name;

    @Column(name = "event_date", columnDefinition = "DATETIME")
    private Date eventDate;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "events")
    private List<User> participants;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "event")
    private List<Notice> notices;
}
