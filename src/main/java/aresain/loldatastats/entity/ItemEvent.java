package aresain.loldatastats.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ItemEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id", length = 20, nullable = false)
    private String matchId;

    @Column(name = "item_id", nullable = false)
    private Short itemId;

    @Column(name = "after_id", nullable = false)
    private Short afterId;

    @Column(name = "before_id")
    private Short beforeId;

    @Column(name = "gold_gain")
    private Short goldGain;

    @Column(name = "participant_id", nullable = false)
    private Byte participantId;

    @Column(name = "timestamp", nullable = false)
    private Integer timestamp;

    @Column(name = "type", length = 20, nullable = false)
    private String type;
}
