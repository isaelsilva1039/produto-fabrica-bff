package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.PrecoItemMercado;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PrecoItemMercadoMapper {
    void upsertPreco(PrecoItemMercado preco);
}
