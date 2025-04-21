package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.ProdutoItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ProdutoItemMapper {
    ProdutoItem findByCodigoBarras(@Param("codigoBarras") String codigoBarras);
    void insert(ProdutoItem item);
}
