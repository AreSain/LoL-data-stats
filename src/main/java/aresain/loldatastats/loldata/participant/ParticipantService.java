package aresain.loldatastats.loldata.participant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aresain.loldatastats.common.type.PerkStyleType;
import aresain.loldatastats.entity.ParticipantAnalysis;
import aresain.loldatastats.entity.ParticipantPerk;
import aresain.loldatastats.entity.ParticipantSummary;
import aresain.loldatastats.loldata.participant.dto.ParticipantSummaryDto;
import aresain.loldatastats.riot.dto.match.ParticipantDto;
import aresain.loldatastats.riot.dto.match.PerkStyleDto;
import aresain.loldatastats.riot.dto.match.PerksDto;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ParticipantService {
    private final ParticipantSummaryRepository participantSummaryRepository;
    private final ParticipantAnalysisRepository participantAnalysisRepository;
    private final ParticipantPerkRepository participantPerkRepository;
    private final ParticipantSummaryMapper participantSummaryMapper;
    private final ParticipantAnalysisMapper participantAnalysisMapper;
    private final ParticipantPerkMapper participantPerkMapper;

    @Transactional
    public void saveParticipants(String matchId, List<ParticipantDto> participants) {
        List<ParticipantSummary> summaries = participants.stream()
            .map(participant -> participantSummaryMapper.toEntity(matchId, participant))
            .collect(Collectors.toList());

        List<ParticipantAnalysis> analyses = participants.stream()
            .map(participant -> participantAnalysisMapper.toEntity(matchId, participant))
            .collect(Collectors.toList());

        List<ParticipantPerk> perks = participants.stream()
            .flatMap(participant -> createParticipantPerks(matchId, participant).stream())
            .collect(Collectors.toList());

        participantSummaryRepository.saveAll(summaries);
        participantAnalysisRepository.saveAll(analyses);
        participantPerkRepository.saveAll(perks);
    }

    private List<ParticipantPerk> createParticipantPerks(String matchId, ParticipantDto participant) {
        List<ParticipantPerk> perks = new ArrayList<>();
        PerksDto perksDto = participant.getPerks();
        
        if (perksDto != null && perksDto.getStyles() != null) {
            List<PerkStyleDto> styles = perksDto.getStyles();
            for (int i = 0; i < styles.size(); i++) {
                PerkStyleDto style = styles.get(i);
                if (style != null && style.getSelections() != null) {
                    String styleType = i == 0 ? PerkStyleType.PRIMARY.name() : PerkStyleType.SUB.name();
                    perks.addAll(createPerksForStyle(matchId, participant.getParticipantId(), style, styleType));
                }
            }
        }
        return perks;
    }

    private List<ParticipantPerk> createPerksForStyle(String matchId, Long participantId, PerkStyleDto style, String styleType) {
        return style.getSelections().stream()
            .filter(selection -> selection != null)
            .map(selection -> participantPerkMapper.toEntity(participantId, matchId, styleType, style, selection))
            .collect(Collectors.toList());
    }

    public List<ParticipantSummaryDto> findParticipantsByMatchId(String matchId) {
        return participantSummaryRepository.findByMatchId(matchId).stream()
            .map(participantSummaryMapper::toDto)
            .collect(Collectors.toList());
    }
} 
