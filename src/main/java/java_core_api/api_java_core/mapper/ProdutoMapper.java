package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Produto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProdutoMapper {

    List<Produto> listarTodas();

    void salvar(Produto produto); // Método para salvar o produto

    void atualizar(Produto produto); // Método para atualizar o produto

    void deletar(Long id); // Método para deletar o produto

    Produto buscarPorId(Long id); // Método para buscar produto por id
}
