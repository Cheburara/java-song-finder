package ee.taltech.recordstore.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class
Song {

    public Song(String title, LocalDate releaseYear, Album album, Artist artist, Genre genre, Lyric lyric){
        this.title = title;
        this.releaseYear = releaseYear;
        this.album = album;
        this.artist = artist;
        this.genre = genre;
        this.lyric = lyric;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String title;
    private LocalDate releaseYear;

    @ManyToOne
    @JoinColumn(name="genre_id")
    private Genre genre;

    @ManyToOne
    @JoinColumn(name="artist_id")
    private Artist artist;

    @ManyToOne
    @JoinColumn(name="album_id")
    private Album album;

    @OneToOne
    @JoinColumn (name="lyric_id")
    private Lyric lyric;


}
