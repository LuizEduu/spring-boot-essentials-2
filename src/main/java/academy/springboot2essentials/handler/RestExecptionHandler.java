package academy.springboot2essentials.handler;

import academy.springboot2essentials.execptions.BadRequestExecption;
import academy.springboot2essentials.execptions.BadRequestExecptionDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice //informar para o spring que será um bean geral, usado por todos controllers
public class RestExecptionHandler {
    @ExceptionHandler(BadRequestExecption.class)
    //informar para o spring caso a exception lançada seja do tipo badRequest execute o método
    public ResponseEntity<BadRequestExecptionDetails> handlerBadRequestExecption(BadRequestExecption badRequestExecption) {
        return new ResponseEntity<>(BadRequestExecptionDetails.builder().
                timestamp(LocalDateTime.now()).status(HttpStatus.BAD_REQUEST.value()).
                title("Bad Request Execption, check the Documentation").
                details(badRequestExecption.getMessage())
                .developerMessage(badRequestExecption.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
