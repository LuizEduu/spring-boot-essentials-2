package academy.springboot2essentials.execptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExecption extends RuntimeException {
    public BadRequestExecption(String message) {
        super(message);
    }
}
