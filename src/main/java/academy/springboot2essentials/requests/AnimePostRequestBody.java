package academy.springboot2essentials.requests;

import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.NotEmpty;

@Data
public class AnimePostRequestBody {
    @NotEmpty(message = "The anime name cannot be empty")
    private String name;
    @URL
    @NotEmpty(message = "The URL is not valid")
    private String url;
}
