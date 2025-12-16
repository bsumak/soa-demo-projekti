package si.um.feri.rest.racuni;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/api/racuni")
public class RacunController {

    private final RacunRepository racunRepository;

    public RacunController(RacunRepository racunRepository) {
        this.racunRepository = racunRepository;
    }

    @GetMapping
    public ResponseEntity<List<Racun>> getAll() {
        List<Racun> racuni = racunRepository.findAll();
        return ResponseEntity.ok(racuni);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Racun> getRacun(@PathVariable String id) {
        try {
            if (id == null || id.isBlank())
                return ResponseEntity.badRequest().build();

            return racunRepository.findById(id)
                    .map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());

        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping
    public ResponseEntity<Racun> createRacun(@RequestBody Racun racun) {

        if (racun == null || racun.getDavcnaSt() == null || racun.getDavcnaSt().isBlank())
            return ResponseEntity.badRequest().build();

        if (racun.getId() != null && !racun.getId().isBlank())
            return ResponseEntity.badRequest().build();

        racun.setId(UUID.randomUUID().toString());
        Racun saved = racunRepository.save(racun);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saved.getId())
                .toUri();

        return ResponseEntity.created(location).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Racun> updateRacun(@PathVariable String id, @RequestBody Racun racun) {

        if (id == null || id.isBlank() || racun == null)
            return ResponseEntity.badRequest().build();

        if (racun.getId() != null && !id.equals(racun.getId()))
            return ResponseEntity.badRequest().build();

        if (!racunRepository.existsById(id))
            return ResponseEntity.notFound().build();

        racun.setId(id);
        racunRepository.save(racun);
        return ResponseEntity.noContent().build(); // 204
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Racun> deleteRacun(@PathVariable String id) {

        if (id == null || id.isBlank())
            return ResponseEntity.badRequest().build();

        if (!racunRepository.existsById(id))
            return ResponseEntity.notFound().build();

        racunRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
