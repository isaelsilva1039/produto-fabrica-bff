package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.CupomItem;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CupomItemMapper {
    void insert(CupomItem item);
}
