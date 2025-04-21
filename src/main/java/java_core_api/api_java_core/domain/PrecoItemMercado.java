package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class PrecoItemMercado {
    private Long id;
    private ProdutoItem produtoItem;
    private Mercado mercado;
    private BigDecimal preco;
    private LocalDateTime dataColeta;
}
