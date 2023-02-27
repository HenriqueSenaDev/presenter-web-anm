package gov.edu.anm.presenter.api.participation;

import gov.edu.anm.presenter.api.participation.dtos.AddJurorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.AddSpectatorRequestDto;
import gov.edu.anm.presenter.api.participation.dtos.ParticipationOutputDto;
import gov.edu.anm.presenter.api.participation.dtos.UserParticipationOutputDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/participations")
public class ParticipationController {
    private final ParticipationService participationService;

    @GetMapping()
    public ResponseEntity<ParticipationOutputDto> findById(@RequestParam Long userId, @RequestParam Long eventId) {
        return ResponseEntity.ok(participationService.findById(userId, eventId));
    }

    @PostMapping("/juror")
    public ResponseEntity<UserParticipationOutputDto> addJurorParticipation(@RequestBody AddJurorRequestDto addJurorRequestDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/participations").toUriString());
        return ResponseEntity.created(uri).body(participationService.addJurorParticipation(addJurorRequestDto));
    }

    @PostMapping("/spectator")
    public ResponseEntity<UserParticipationOutputDto> addSpectatorParticipation(@RequestBody AddSpectatorRequestDto addSpectatorRequestDto) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/participations").toUriString());
        return ResponseEntity.created(uri).body(participationService.addSpectatorParticipation(addSpectatorRequestDto));
    }

    @DeleteMapping
    public ResponseEntity<?> removeParticipation(@RequestParam Long userId, @RequestParam Long eventId) {
        participationService.removeParticipation(userId, eventId);
        return ResponseEntity.noContent().build();
    }

}
