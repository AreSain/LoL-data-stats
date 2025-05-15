package aresain.loldatastats.loldata.participant;

import java.util.ArrayList;
import java.util.List;
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

    @Transactional
    public void saveParticipants(String matchId, List<ParticipantDto> participants) {
        List<ParticipantSummary> summaries = participants.stream()
            .map(participant -> createParticipantSummary(matchId, participant))
            .collect(Collectors.toList());

        List<ParticipantAnalysis> analyses = participants.stream()
            .map(participant -> createParticipantAnalysis(matchId, participant))
            .collect(Collectors.toList());

        List<ParticipantPerk> perks = participants.stream()
            .flatMap(participant -> createParticipantPerks(matchId, participant).stream())
            .collect(Collectors.toList());

        participantSummaryRepository.saveAll(summaries);
        participantAnalysisRepository.saveAll(analyses);
        participantPerkRepository.saveAll(perks);
    }

    private ParticipantSummary createParticipantSummary(String matchId, ParticipantDto participant) {
        return ParticipantSummary.builder()
            .participantId((long) participant.getParticipantId())
            .matchId(matchId)
            .puuid(participant.getPuuid())
            .riotIdGameName(participant.getRiotIdGameName())
            .riotIdTagline(participant.getRiotIdTagline())
            .profileIcon(participant.getProfileIcon())
            .teamId(participant.getTeamId())
            .championId(participant.getChampionId())
            .championName(participant.getChampionName())
            .champLevel(participant.getChampLevel())
            .kills((short) participant.getKills())
            .deaths((short) participant.getDeaths())
            .assists((short) participant.getAssists())
            .totalMinionsKilled(participant.getTotalMinionsKilled())
            .goldEarned(participant.getGoldEarned())
            .item0(participant.getItem0())
            .item1(participant.getItem1())
            .item2(participant.getItem2())
            .item3(participant.getItem3())
            .item4(participant.getItem4())
            .item5(participant.getItem5())
            .item6(participant.getItem6())
            .summoner1Id(participant.getSummoner1Id())
            .summoner2Id(participant.getSummoner2Id())
            .visionScore(participant.getVisionScore())
            .wardsPlaced(participant.getWardsPlaced())
            .wardsKilled(participant.getWardsKilled())
            .stealthWardsPlaced(participant.getSightWardsBoughtInGame()) // 수정해야함
            .detectorWardsPlaced(participant.getDetectorWardsPlaced())
            .visionWardsBought(participant.getVisionWardsBoughtInGame())
            .teamPosition(participant.getTeamPosition())
            .individualPosition(participant.getIndividualPosition())
            .physicalDamageDealtToChampions(participant.getPhysicalDamageDealtToChampions())
            .magicDamageDealtToChampions(participant.getMagicDamageDealtToChampions())
            .trueDamageDealtToChampions(participant.getTrueDamageDealtToChampions())
            .totalDamageTaken(participant.getTotalDamageTaken())
            .doubleKills(participant.getDoubleKills())
            .tripleKills(participant.getTripleKills())
            .quadraKills(participant.getQuadraKills())
            .pentaKills(participant.getPentaKills())
            .win(participant.isWin())
            .build();
    }

    private ParticipantAnalysis createParticipantAnalysis(String matchId, ParticipantDto participant) {
        PerksDto perksDto = participant.getPerks();
        return ParticipantAnalysis.builder()
            .participantId((long) participant.getParticipantId())
            .matchId(matchId)
            .damageDealtToChampions(participant.getTotalDamageDealtToChampions())
            .damageDealtToBuildings(participant.getDamageDealtToBuildings())
            .damageDealtToObjectives(participant.getDamageDealtToObjectives())
            .damageDealtToTurrets(participant.getDamageDealtToTurrets())
            .totalTimeCCDealt(participant.getTotalTimeCCDealt())
            .jungleCSBefore10Min(participant.getChallenges() != null ? participant.getChallenges().getJungleCsBefore10Minutes() : null)
            .laneMinionFirst10Min(participant.getChallenges() != null ? participant.getChallenges().getLaneMinionsFirst10Minutes() : null)
            .scuttleCrabKills(participant.getChallenges() != null ? participant.getChallenges().getScuttleCrabKills() : null)
            .quickSoloKills(participant.getChallenges() != null ? participant.getChallenges().getQuickSoloKills() : null)
            .killsOnLanersEarlyJungle(participant.getChallenges() != null ? participant.getChallenges().getKillsOnLanersEarlyJungleAsJungler() : null)
            .statPerksDefense(perksDto != null && perksDto.getPerkStats() != null ? perksDto.getPerkStats().getDefense() : null)
            .statPerksFlex(perksDto != null && perksDto.getPerkStats() != null ? perksDto.getPerkStats().getFlex() : null)
            .statPerksOffense(perksDto != null && perksDto.getPerkStats() != null ? perksDto.getPerkStats().getOffense() : null)
            .build();
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

    private List<ParticipantPerk> createPerksForStyle(String matchId, int participantId, PerkStyleDto style, String styleType) {
        return style.getSelections().stream()
            .filter(selection -> selection != null)
            .map(selection -> ParticipantPerk.builder()
                .participantId((long) participantId)
                .matchId(matchId)
                .styleType(styleType)
                .style(style.getStyle())
                .perkId(selection.getPerk())
                .var1(selection.getVar1())
                .var2(selection.getVar2())
                .var3(selection.getVar3())
                .build())
            .collect(Collectors.toList());
    }

    public List<ParticipantSummaryDto> findParticipantsByMatchId(String matchId) {
        return participantSummaryRepository.findByMatchId(matchId).stream()
            .map(participantSummaryMapper::toDto)
            .collect(Collectors.toList());
    }
} 
