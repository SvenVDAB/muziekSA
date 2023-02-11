package be.vdab.muzieksa.restcontrollers;

import be.vdab.muzieksa.domain.Album;
import be.vdab.muzieksa.exceptions.AlbumNietGevondenException;
import be.vdab.muzieksa.services.AlbumService;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
@ExposesResourceFor(Album.class)
public class AlbumController {
    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping("{id}") // alternative version showing the (json) structure of the album
    Album get(@PathVariable long id) {
        return albumService.findById(id).orElseThrow(AlbumNietGevondenException::new);
    }

/*
    @GetMapping("{id}") // version showing the structure for the exercise
    AlbumInfo getAlbumInfo(@PathVariable long id) {
        return new AlbumInfo();
    }
*/




    @ExceptionHandler(AlbumNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void albumNietGevonden() {
    }
}
