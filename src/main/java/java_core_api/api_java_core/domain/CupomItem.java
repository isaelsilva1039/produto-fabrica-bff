package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class CupomItem {
    private Long id;
    private Cupom cupom;
    private ProdutoItem produtoItem;
    private BigDecimal qtde;
    private BigDecimal preco;
}
