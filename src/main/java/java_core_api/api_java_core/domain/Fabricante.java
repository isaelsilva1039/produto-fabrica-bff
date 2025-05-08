package java_core_api.api_java_core.domain;

public class Fabricante {
    private Long id;
    private String nome;
    private String cnpj;

    // Getter para id
    public Long getId() {
        return id;
    }

    // Setter para id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter para nome
    public String getNome() {
        return nome;
    }

    // Setter para nome
    public void setNome(String nome) {
        this.nome = nome;
    }

    // Getter para cnpj
    public String getCnpj() {
        return cnpj;
    }

    // Setter para cnpj
    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
