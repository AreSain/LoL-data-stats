package aresain.loldatastats.loldata.timeline;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.entity.BuildingKillEvent;
import aresain.loldatastats.entity.ChampionKillEvent;
import aresain.loldatastats.entity.ChampionLevelEvent;
import aresain.loldatastats.entity.EliteMonsterKillEvent;
import aresain.loldatastats.entity.ItemEvent;
import aresain.loldatastats.entity.SkillPointEvent;
import aresain.loldatastats.entity.WardEvent;
import aresain.loldatastats.loldata.timeline.dto.TimelineInfoDto;
import aresain.loldatastats.loldata.timeline.repository.BuildingKillEventRepository;
import aresain.loldatastats.loldata.timeline.repository.ChampionKillEventRepository;
import aresain.loldatastats.loldata.timeline.repository.ChampionLevelEventRepository;
import aresain.loldatastats.loldata.timeline.repository.EliteMonsterKillEventRepository;
import aresain.loldatastats.loldata.timeline.repository.ItemEventRepository;
import aresain.loldatastats.loldata.timeline.repository.SkillPointEventRepository;
import aresain.loldatastats.loldata.timeline.repository.WardEventRepository;
import aresain.loldatastats.riot.RiotService;
import aresain.loldatastats.riot.dto.timeline.BuildingKillEventDto;
import aresain.loldatastats.riot.dto.timeline.ChampionKillEventDto;
import aresain.loldatastats.riot.dto.timeline.EliteMonsterKillEventDto;
import aresain.loldatastats.riot.dto.timeline.EventsTimeLineDto;
import aresain.loldatastats.riot.dto.timeline.FramesTimeLineDto;
import aresain.loldatastats.riot.dto.timeline.ItemEventDto;
import aresain.loldatastats.riot.dto.timeline.LevelUpEventDto;
import aresain.loldatastats.riot.dto.timeline.SkillLevelUpEventDto;
import aresain.loldatastats.riot.dto.timeline.TimelineDto;
import aresain.loldatastats.riot.dto.timeline.WardEventDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TimelineService {

    private final RiotService riotService;
    private final TimelineMapper timelineMapper;
    private final ItemEventRepository itemEventRepository;
    private final WardEventRepository wardEventRepository;
    private final EliteMonsterKillEventRepository eliteMonsterKillEventRepository;
    private final SkillPointEventRepository skillPointEventRepository;
    private final ChampionKillEventRepository championKillEventRepository;
    private final BuildingKillEventRepository buildingKillEventRepository;
    private final ChampionLevelEventRepository championLevelEventRepository;

    @Transactional
    public TimelineInfoDto saveOrFindTimelineInfo(String matchId) {
        boolean timelineExists = existsByMatchId(matchId);

        if (!timelineExists) {
            TimelineDto timelineDto = riotService.getTimelineByMatchId(matchId);
            saveTimelineData(matchId, timelineDto);
        }
        return createTimelineInfoDto(matchId);
    }

    private void saveTimelineData(String matchId, TimelineDto timelineDto) {
        saveTimelineEvents(matchId, timelineDto);
    }

    private TimelineInfoDto createTimelineInfoDto(String matchId) {
        List<ItemEvent> itemEvents = itemEventRepository.findByMatchId(matchId);
        List<WardEvent> wardEvents = wardEventRepository.findByMatchId(matchId);
        List<EliteMonsterKillEvent> eliteMonsterKillEvents = eliteMonsterKillEventRepository.findByMatchId(matchId);
        List<ChampionKillEvent> killEvents = championKillEventRepository.findByMatchId(matchId);
        List<SkillPointEvent> skillPointEvents = skillPointEventRepository.findByMatchId(matchId);
        List<ChampionLevelEvent> levelEvents = championLevelEventRepository.findByMatchId(matchId);
        List<BuildingKillEvent> buildingEvents = buildingKillEventRepository.findByMatchId(matchId);

        return timelineMapper.toTimelineDto(matchId,
            itemEvents.stream().map(timelineMapper::toDto).toList(),
            skillPointEvents.stream().map(timelineMapper::toDto).toList(),
            levelEvents.stream().map(timelineMapper::toDto).toList(),
            killEvents.stream().map(timelineMapper::toDto).toList(),
            buildingEvents.stream().map(timelineMapper::toDto).toList(),
            eliteMonsterKillEvents.stream().map(timelineMapper::toDto).toList(),
            wardEvents.stream().map(timelineMapper::toDto).toList()
        );
    }

    public boolean existsByMatchId(String matchId) {
        return championLevelEventRepository.existsByMatchId(matchId) ||
            championKillEventRepository.existsByMatchId(matchId) ||
            itemEventRepository.existsByMatchId(matchId);
    }

    public void saveTimelineEvents(String matchId, TimelineDto timelineDto) {
        int frameInterval = (int)(timelineDto.getInfo().getFrameInterval() / 1000); // 밀리초를 초로 변환

        List<FramesTimeLineDto> frames = timelineDto.getInfo().getFrames();

        for (int frameIndex = 0; frameIndex < frames.size(); frameIndex++) {
            FramesTimeLineDto frame = frames.get(frameIndex);

            // 각 프레임의 이벤트 처리
            for (EventsTimeLineDto event : frame.getEvents()) {
                processEvent(matchId, event);
            }
        }
    }

    private void processEvent(String matchId, EventsTimeLineDto event) {
        String eventType = event.getType();

        switch (eventType) {
            case "CHAMPION_KILL":
                saveChampionKillEvent(matchId, event);
                break;
            case "LEVEL_UP":
                saveChampionLevelEvent(matchId, event);
                break;
            case "ITEM_PURCHASED":
            case "ITEM_SOLD":
            case "ITEM_DESTROYED":
            case "ITEM_UNDO":
                saveItemEvent(matchId, event);
                break;
            case "BUILDING_KILL":
                saveBuildingKillEvent(matchId, event);
                break;
            case "WARD_PLACED":
            case "WARD_KILL":
                saveWardEvent(matchId, event);
                break;
            case "ELITE_MONSTER_KILL":
                saveEliteMonsterKillEvent(matchId, event);
                break;
            case "SKILL_LEVEL_UP":
                saveSkillPointEvent(matchId, event);
                break;
        }
    }

    private void saveChampionKillEvent(String matchId, EventsTimeLineDto event) {
        ChampionKillEvent killEvent = timelineMapper.toChampionKillEntity(matchId, (ChampionKillEventDto) event);
        championKillEventRepository.save(killEvent);
    }

    private void saveChampionLevelEvent(String matchId, EventsTimeLineDto event) {
        ChampionLevelEvent levelEvent = timelineMapper.toChampionLevelEntity(matchId, (LevelUpEventDto) event);
        championLevelEventRepository.save(levelEvent);
    }

    private void saveItemEvent(String matchId, EventsTimeLineDto event) {
        ItemEvent itemEvent = timelineMapper.toItemEntity(matchId, (ItemEventDto) event);
        itemEventRepository.save(itemEvent);
    }

    private void saveBuildingKillEvent(String matchId, EventsTimeLineDto event) {
        BuildingKillEvent buildingEvent = timelineMapper.toBuildingKillEntity(matchId, (BuildingKillEventDto) event);
        buildingKillEventRepository.save(buildingEvent);
    }

    private void saveWardEvent(String matchId, EventsTimeLineDto event) {
        WardEvent wardEvent = timelineMapper.toWardEntity(matchId, (WardEventDto) event);
        wardEventRepository.save(wardEvent);
    }

    private void saveEliteMonsterKillEvent(String matchId, EventsTimeLineDto event) {
        EliteMonsterKillEvent eliteMonsterKillEvent = timelineMapper.toEliteKillEntity(matchId, (EliteMonsterKillEventDto) event);
        eliteMonsterKillEventRepository.save(eliteMonsterKillEvent);
    }

    private void saveSkillPointEvent(String matchId, EventsTimeLineDto event) {
        SkillPointEvent skillPointEvent = timelineMapper.toSkillPointEntity(matchId, (SkillLevelUpEventDto) event);
        skillPointEventRepository.save(skillPointEvent);
    }
}
