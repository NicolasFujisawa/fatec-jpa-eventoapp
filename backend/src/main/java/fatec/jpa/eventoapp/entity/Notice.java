package fatec.jpa.eventoapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "notices")
@Data
@EqualsAndHashCode(callSuper = false)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@AttributeOverride(name = "id",column = @Column(name = "notice_id"))
public class Notice extends BaseEntity {
    @Column(name = "title")
    private String title;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "event_id")
    private Event event;
}
