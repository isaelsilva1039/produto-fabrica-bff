package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DispensaItem {
    private Long id;
    private Dispensa dispensa;
    private Produto produto;
    private BigDecimal pesoConsumo;
    private BigDecimal pesoEstoque;
    private Integer diasReposicao;
    private LocalDateTime dataUltimaRep;
}
