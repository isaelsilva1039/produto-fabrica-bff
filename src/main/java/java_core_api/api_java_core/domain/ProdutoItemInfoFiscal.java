package java_core_api.api_java_core.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class ProdutoItemInfoFiscal {
    private Long id;
    private ProdutoItem produtoItem;
    private Mercado mercado;
    private String cstIcms;
    private BigDecimal valorTotalIcms;
    private String cstPis;
    private BigDecimal valorPis;
    private String cstCofins;
    private BigDecimal valorCofins;
    private LocalDateTime dataAtualizacao;
}
