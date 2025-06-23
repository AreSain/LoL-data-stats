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
public class WardEvent extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "match_id", length = 20, nullable = false)
    private String matchId;

    @Column(name = "timestamp", nullable = false)
    private Integer timestamp;

    @Column(name = "creator_id")
    private Byte creatorId;

    @Column(name = "killer_id")
    private Byte killerId;

    @Column(name = "type", length = 20, nullable = false)
    private String type;

    @Column(name = "ward_type", length = 20, nullable = false)
    private String wardType;
}
