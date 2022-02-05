package academy.springboot2essentials.repository;

import academy.springboot2essentials.domain.Anime;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimeRepository {
    List<Anime> listAll();
}
