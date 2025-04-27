package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.PrecoItemMercado;
import java_core_api.api_java_core.dtos.PrecoItemMercadoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PrecoItemMercadoMapper {
    void upsertPreco(PrecoItemMercado preco);

    List<PrecoItemMercadoDTO>  buscarTodos();
}
