package java_core_api.api_java_core.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoDTO {

    @NotBlank(message = "Nome é obrigatório")
    private String nome;

    @NotBlank(message = "Código de barras é obrigatório")
    private String codigoBarras;

    @NotNull(message = "Fabricante é obrigatório")
    private Long fabricanteId;

    // Getters and Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public Long getFabricanteId() {
        return fabricanteId;
    }

    public void setFabricanteId(Long fabricanteId) {
        this.fabricanteId = fabricanteId;
    }
}
