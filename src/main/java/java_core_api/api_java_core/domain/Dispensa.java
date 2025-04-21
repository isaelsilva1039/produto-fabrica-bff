package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Dispensa {
    private Long id;
    private Long idUsuario;
    private LocalDateTime dataCriacao;
}
