package fatec.jpa.eventoapp.entity;

import fatec.jpa.eventoapp.enums.ModeratorLevelEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "moderators")
@PrimaryKeyJoinColumn(name = "user_id")
@Data
@EqualsAndHashCode(callSuper = false)
public class Moderator extends User {
    @Column(name = "mod_level", columnDefinition = "integer")
    private ModeratorLevelEnum level;

    @Column(name = "enabled")
    protected boolean enabled;
}
