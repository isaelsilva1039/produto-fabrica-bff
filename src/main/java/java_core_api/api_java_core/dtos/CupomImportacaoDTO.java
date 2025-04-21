package java_core_api.api_java_core.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class CupomImportacaoDTO {
    private EmitenteDTO emitente;
    private List<ProdutoImportadoDTO> produtos;
}

