package academy.springboot2essentials.execptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExecptionDetails {
    private String title;
    private int status;
    private String details;
    private String developerMessage;
    private LocalDateTime timestamp;
}
