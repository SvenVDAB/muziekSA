package be.vdab.muzieksa.domain;

import javax.persistence.*;
import java.sql.Time;

@Embeddable
@Access(AccessType.FIELD)
public class Track {
    private String naam;
    private Time tijd;


    public String getNaam() {
        return naam;
    }

    public Time getTijd() {
        return tijd;
    }

    @Override
    public boolean equals(Object object) {
        return object instanceof Track track && naam.equalsIgnoreCase(track.naam);
    }

    @Override
    public int hashCode() {
        return naam.toUpperCase().hashCode();
    }
}