package java_core_api.api_java_core.dtos;

public class FabricanteDTO {
    private Integer id;
    private String nome;
    private String cnpj;

    // Construtores
    public FabricanteDTO(String nome, String cnpj, Integer  id) {
        this.id = id;
        this.nome = nome;
        this.cnpj = cnpj;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }


    public Integer getId() {
        return id;
    }

        public void setId(Integer id) {
        this.id = id;
    }
}
