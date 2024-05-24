package by.it_academy.jd2.votig.controller.http;

import by.it_academy.jd2.votig.dao.entity.ArtistEntity;
import by.it_academy.jd2.votig.service.api.IArtistService;
import by.it_academy.jd2.votig.service.api.dto.ArtistCUDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/artist")
public class ArtistController {

    private final IArtistService artistService;

    public ArtistController(IArtistService artistService){
        this.artistService = artistService;
    }
    @GetMapping()
    public List<ArtistEntity> get(@RequestParam(value = "id", defaultValue = "1") Long page)
            throws IOException {
        List<ArtistEntity> data = artistService.get();

        return data;
    }
    @GetMapping("/{uuid}")
    public ArtistEntity getById(@PathVariable(value = "uuid") Long id)
            throws IOException {
        return artistService.get(id).get();
    }
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody ArtistCUDTO vote) {
        this.artistService.create(vote);
    }
    @PutMapping
    public void update(@RequestParam("id") Long superPuperId, @RequestBody ArtistCUDTO vote) {
        this.artistService.update(superPuperId, vote);
    }
    @DeleteMapping
    public void delete(@RequestParam("id") Long superPuperId){
        this.artistService.delete(superPuperId);
    }
}
