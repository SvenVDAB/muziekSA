package be.vdab.muzieksa.domain;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;

    @ManyToOne(/*fetch = FetchType.LAZY, */optional = false)
    @JoinColumn(name = "artiestId")
    private Artiest artiest;

    @ElementCollection
    @CollectionTable(name = "tracks",
            joinColumns = @JoinColumn(name = "albumId"))
    private Set<Track> tracks = new LinkedHashSet<>();

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public Artiest getArtiest() {
        return artiest;
    }

    public Set<Track> getTracks() {
        return tracks;
    }
}
