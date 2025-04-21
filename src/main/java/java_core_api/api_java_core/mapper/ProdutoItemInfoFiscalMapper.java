package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.ProdutoItemInfoFiscal;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProdutoItemInfoFiscalMapper {
    void upsertInfoFiscal(ProdutoItemInfoFiscal info);
}
