package java_core_api.api_java_core.mapper;


import java_core_api.api_java_core.domain.Produto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProdutoMapper {
    Produto findByDescricao(@Param("descricao") String descricao);
    void insert(Produto produto);
}

