package ee.taltech.recordstore.service;

import ee.taltech.recordstore.dto.LyricDto;
import ee.taltech.recordstore.dto.SongDto;
import ee.taltech.recordstore.entity.Song;
import ee.taltech.recordstore.mapper.RecordStoreMapper;
import ee.taltech.recordstore.repository.ArtistRepository;
import ee.taltech.recordstore.repository.GenreRepository;
import ee.taltech.recordstore.repository.LyricRepository;
import ee.taltech.recordstore.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor


public class SongService {

    private final SongRepository songRepository;
    private final GenreRepository genreRepository;
    private  final ArtistRepository artistRepository;
    private final LyricRepository lyricRepository;
    private final RecordStoreMapper mapper;

    public List<SongDto> getSongsByGenre(String genreStr){
        var GenreEntity = genreRepository.findGenreByName(genreStr);
        if(GenreEntity == null){
            System.out.println("Incorrect music genre given!");
            return Collections.emptyList();
        }
//    var songEntities = songRepository.findAllByGenre(GenreEntity);
        return mapper.mapSongEntitiesToDtos(songRepository.findAllByGenre(GenreEntity));
    }

    public List<SongDto> getAllSongs(){
        return mapper.mapSongEntitiesToDtos(songRepository.findAll());
    }

    public List<SongDto> getSongsByArtist(String artistName) {
        var artistEntity = artistRepository.findArtistByName(artistName);
        if(artistEntity == null){
            System.out.println("Incorrect artist name given!");
            return Collections.emptyList();
        }
        return mapper.mapSongEntitiesToDtos(songRepository.findAllByArtistOrderByAlbumTitleAscTitleAsc(artistEntity));
    }

    public List<LyricDto> findAllBySongTitle (String title) {
        var lyricEntity = lyricRepository.findAllBySongTitle(title);
        if(lyricEntity == null){
            System.out.println("Incorrect Title given!");
            return Collections.emptyList();
        }
        return mapper.mapLyricEntitiesToDtos(lyricRepository.findAllBySongTitle(title));
    }
}
