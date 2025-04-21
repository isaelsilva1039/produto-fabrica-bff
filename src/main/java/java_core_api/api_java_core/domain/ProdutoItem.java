package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;

@Getter
@Setter
public class ProdutoItem {
    private Long id;
    private Produto produto;
    private String descricao;
    private String tipoCodigo;
    private Marca marca;
    private BigDecimal peso;
    private String hashImagem;
    private String codigoBarras;
}
