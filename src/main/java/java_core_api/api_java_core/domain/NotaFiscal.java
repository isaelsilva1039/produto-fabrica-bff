package java_core_api.api_java_core.domain;

import java_core_api.api_java_core.enums.StatusNotaFiscal;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class NotaFiscal {
    private Long id;
    private Long usuarioId;
    private String chaveNf;
    private String urlNf;
    private LocalDateTime dataHoraColeta;
    private StatusNotaFiscal status = StatusNotaFiscal.PENDENTE;

}
