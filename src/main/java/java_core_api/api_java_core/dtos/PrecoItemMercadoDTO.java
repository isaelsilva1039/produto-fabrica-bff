package java_core_api.api_java_core.dtos;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class PrecoItemMercadoDTO {
    private Long idProdutoItem;
    private String nomeProduto;
    private String codigoBarras;
    private Long idMercado;
    private String nomeMercado;
    private Double preco;
    private LocalDateTime dataColeta;

    // Novos campos adicionados
    private String endereco;
    private String cidade;
    private String estado;
}
