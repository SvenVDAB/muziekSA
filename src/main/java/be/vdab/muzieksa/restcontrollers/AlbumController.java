package be.vdab.muzieksa.restcontrollers;

import be.vdab.muzieksa.domain.Album;
import be.vdab.muzieksa.exceptions.AlbumNietGevondenException;
import be.vdab.muzieksa.services.AlbumService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.hateoas.server.TypedEntityLinks;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/albums")
@ExposesResourceFor(Album.class)
public class AlbumController {
    private final AlbumService albumService;
    private final TypedEntityLinks.ExtendedTypedEntityLinks<Album> links;

    public AlbumController(AlbumService albumService, EntityLinks links) {
        this.albumService = albumService;
        this.links = links.forType(Album.class, Album::getId);
    }

/*    @GetMapping("{id}") // alternative version showing the (json) structure of the album
    Album get(@PathVariable long id) {
        return albumService.findById(id).orElseThrow(AlbumNietGevondenException::new);
    }*/


    @GetMapping("{id}")
        // version showing the structure for the exercise
    EntityModel<AlbumEnArtiest> get(@PathVariable long id) {
        return albumService.findById(id)
                .map(album -> EntityModel.of(new AlbumEnArtiest(album),
                                links.linkToItemResource(album),
                                links.linkForItemResource(album)
                                        .slash("tracks")
                                        .withRel("tracks")
                        )
                )
                .orElseThrow(AlbumNietGevondenException::new);
    }

    private class AlbumEnArtiest {
        private String album;
        private String artiest;
        protected AlbumEnArtiest() { ///!!!!!
        }
        public AlbumEnArtiest(Album album) {
            this.album = album.getNaam();
            this.artiest = album.getArtiest().getNaam();
        }

        public String getAlbum() {
            return album;
        }

        public String getArtiest() {
            return artiest;
        }
    }


    @ExceptionHandler(AlbumNietGevondenException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    void albumNietGevonden() {
    }
}
