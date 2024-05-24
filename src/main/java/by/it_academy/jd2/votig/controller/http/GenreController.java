package by.it_academy.jd2.votig.controller.http;


import by.it_academy.jd2.votig.dao.entity.GenreEntity;
import by.it_academy.jd2.votig.service.api.IGenreService;
import by.it_academy.jd2.votig.service.api.dto.GenreCUDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping
public class GenreController {

    private final IGenreService genreService;

    public GenreController(IGenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping
    public List<GenreEntity> get() {
        return genreService.get();
    }

    @GetMapping("/{uuid}")
    public GenreEntity getById(@PathVariable(value = "uuid") Long id)
            throws IOException {
        return genreService.get(id).get();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody GenreCUDTO genre) {
        this.genreService.create(genre);
    }

    @PutMapping
    public void update(@RequestParam("id") Long superPuperId, @RequestBody GenreCUDTO genre) {
        this.genreService.update(superPuperId, genre);
    }

    @DeleteMapping
    public void delete(@RequestParam("id") Long superPuperId) {
        this.genreService.delete(superPuperId);
    }
}
