package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Mercado;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MercadoMapper {
    Mercado findByCnpj(@Param("cnpj") String cnpj);
    void insert(Mercado mercado);
}
