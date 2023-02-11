package be.vdab.muzieksa.repositories;

import be.vdab.muzieksa.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository<Album, Long> {
}
