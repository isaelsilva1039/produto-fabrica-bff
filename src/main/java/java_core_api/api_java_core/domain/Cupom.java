package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Cupom {
    private Long id;
    private Mercado mercado;
    private Long idUsuario;
    private LocalDateTime dataColeta;
    private LocalDateTime dataCompra;
    private BigDecimal valorCompras;
}
