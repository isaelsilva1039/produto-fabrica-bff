package java_core_api.api_java_core.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
public class ColetaNotaDTO {
    private String chaveNf;
    private String urlNf;
    private LocalDateTime dataHoraColeta;
}
