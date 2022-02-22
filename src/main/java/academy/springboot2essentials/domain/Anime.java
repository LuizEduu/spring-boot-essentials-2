package academy.springboot2essentials.domain;

import lombok.*;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import java.util.Objects;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
@Builder
public class Anime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Anime anime = (Anime) o;
        return id.equals(anime.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
