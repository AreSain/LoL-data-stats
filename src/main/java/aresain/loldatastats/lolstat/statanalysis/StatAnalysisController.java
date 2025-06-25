package aresain.loldatastats.lolstat.statanalysis;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aresain.loldatastats.lolstat.statanalysis.dto.AnalysisListDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/stat")
@RequiredArgsConstructor
public class StatAnalysisController {
	private final MatchAnalysisService matchAnalysisService;

	@GetMapping("/info")
	public ResponseEntity<AnalysisListDto> getPlayerStats(
		@RequestParam String puuid,
		@RequestParam(defaultValue = "ranked")  String type,
		@RequestParam(defaultValue = "0")       Integer start,
		@RequestParam(defaultValue = "10")      Integer count) {
		return ResponseEntity.ok(matchAnalysisService.getStatAnalysis(puuid, type, start, count));
	}
}
