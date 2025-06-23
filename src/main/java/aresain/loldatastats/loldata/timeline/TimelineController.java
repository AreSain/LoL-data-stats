package aresain.loldatastats.loldata.timeline;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import aresain.loldatastats.loldata.timeline.dto.TimelineInfoDto;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/match")
@RequiredArgsConstructor
public class TimelineController {
	private final TimelineService timelineService;

	@PostMapping("/timeline")
	public ResponseEntity<TimelineInfoDto> saveOrFindTimelineInfo(@RequestParam String matchId) {
		return ResponseEntity.ok(timelineService.saveOrFindTimelineInfo(matchId));
	}
}
