package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@IdClass(ParticipantPerkId.class)
public class ParticipantPerk extends BaseEntity {
    @Id
    @Column(name = "participant_id")
    private Long participantId;

    @Id
    @Column(name = "match_id", length = 20)
    private String matchId;

    @Id
    @Column(name = "style_type", length = 10)
    private String styleType;

    @Column(name = "style", nullable = true)
    private Integer style;

    @Column(name = "perk_id", nullable = true)
    private Integer perkId;

    @Column(name = "var1", nullable = true)
    private Integer var1;

    @Column(name = "var2", nullable = true)
    private Integer var2;

    @Column(name = "var3", nullable = true)
    private Integer var3;
} 