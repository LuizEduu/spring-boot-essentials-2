package academy.springboot2essentials.service;

import academy.springboot2essentials.domain.Anime;
import academy.springboot2essentials.execptions.BadRequestExecption;
import academy.springboot2essentials.mapper.AnimeMapper;
import academy.springboot2essentials.repository.AnimeRepository;
import academy.springboot2essentials.requests.AnimePostRequestBody;
import academy.springboot2essentials.requests.AnimePutRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnimeService {

    private final AnimeRepository animeRepository;

    public Anime findByIdOrThrowsBadRequestExecption(long id) {
        return animeRepository.findById(id).orElseThrow(() -> new BadRequestExecption("Anime not found"));
    }

    public List<Anime> listAll() {
        return animeRepository.findAll();
    }

    public List<Anime> findByName(String name) {
        return animeRepository.findByName(name);
    }

    public Anime save(AnimePostRequestBody animePostRequestBody) {
        return animeRepository.save(AnimeMapper.INSTANCE.toAnime(animePostRequestBody));
    }

    public void delete(long id) {
        animeRepository.delete(findByIdOrThrowsBadRequestExecption(id));
    }

    public void replace(AnimePutRequestBody animePutRequestBody) {
        Anime savedAnime = findByIdOrThrowsBadRequestExecption(animePutRequestBody.getId());
        Anime anime = AnimeMapper.INSTANCE.toAnime(animePutRequestBody);
        anime.setId(savedAnime.getId());
        animeRepository.save(anime);
    }

}
