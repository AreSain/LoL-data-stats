package aresain.loldatastats.loldata.timeline.dto;

import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TimelineInfoDto {
    private String matchId;
    private List<ItemEventInfoDto> itemPurchaseEvents;
    private Map<Integer, List<SkillPointEventInfoDto>> SkillPointEvents;
    private List<ChampionLevelEventInfoDto> championLevelEvents;
    private List<ChampionKillEventInfoDto> championKillEvents;
    private List<BuildingKillEventInfoDto> buildingKillEvents;
    private List<EliteMonsterKillEventInfoDto> eliteMonsterKillEvents;
    private List<WardEventInfoDto> wardEvents;
}
