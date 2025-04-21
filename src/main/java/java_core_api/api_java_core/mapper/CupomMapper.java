package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Cupom;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CupomMapper {
    void insert(Cupom cupom);
    void updateValorTotal(Cupom cupom);
}
