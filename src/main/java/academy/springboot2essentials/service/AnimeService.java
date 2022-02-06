package academy.springboot2essentials.service;

import academy.springboot2essentials.domain.Anime;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class AnimeService {

    private static final List<Anime> animes;

    static {
        animes = new ArrayList<>(List.of(new Anime(1L, "DBZ"), new Anime(2L, "Berserk")));
    }

    public Anime findById(long id) {
        return animes.stream().filter(anime -> anime.getId().equals(id)).findFirst().orElseThrow
                (() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Anime not found"));
    }

    public List<Anime> listAll() {
        return animes;
    }

    public Anime save(Anime anime) {
        anime.setId(ThreadLocalRandom.current().nextLong(3, 100000));
        animes.add(anime);
        return anime;
    }

    public void delete(long id) {
        animes.remove(findById(id));
    }

    public void replace(Anime anime, long id) {
        delete(id);
        anime.setId(id);
        animes.add(anime);
    }
}
