package be.vdab.muzieksa.services;

import be.vdab.muzieksa.domain.Album;
import be.vdab.muzieksa.domain.Track;
import be.vdab.muzieksa.repositories.AlbumRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;


@Service
@Transactional(readOnly = true)
public class AlbumService {
    private final AlbumRepository albumRepository;

    public AlbumService(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    public Optional<Album> findById(long id) {
        return albumRepository.findById(id);
    }
    
    public Set<Track> findTracksById(long id) {
        return findById(id).get().getTracks();
    }
}
