package academy.springboot2essentials.handler;

import academy.springboot2essentials.execptions.BadRequestExecption;
import academy.springboot2essentials.execptions.BadRequestExecptionDetails;
import academy.springboot2essentials.execptions.ExceptionDetails;
import academy.springboot2essentials.execptions.ValidationExecptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExecptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(BadRequestExecption.class)

    public ResponseEntity<BadRequestExecptionDetails> handleBadRequestExecption(BadRequestExecption badRequestExecption) {
        return new ResponseEntity<>(BadRequestExecptionDetails
                .builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Execption, check the Documentation")
                .details(badRequestExecption.getMessage())
                .developerMessage(badRequestExecption.getClass().getName())
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid
            (MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();

        String fields = fieldErrors.stream().map(FieldError::getField).collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream().map(FieldError::getDefaultMessage).collect(Collectors.joining(", "));


        return new ResponseEntity<>(ValidationExecptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(HttpStatus.BAD_REQUEST.value())
                .title("Bad Request Execption, Invalid fields")
                .details("Check the fields errors")
                .developerMessage(exception.getClass().getName())
                .fields(fields).fieldsMessage(fieldsMessage)
                .build(), HttpStatus.BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal
            (Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ExceptionDetails exceptionDetails = ExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .title(ex.getCause().getMessage())
                .details("Check the fields errors")
                .developerMessage(ex.getClass().getName())
                .build();

        return new ResponseEntity<>(exceptionDetails, headers, status);
    }
}
