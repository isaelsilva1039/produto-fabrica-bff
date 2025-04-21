package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.NotaFiscal;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface NotaFiscalMapper {
    void inserirNotaFiscal(NotaFiscal nota);

    List<NotaFiscal> buscarNotasPorUsuario(@Param("usuarioId") Long usuarioId);
    NotaFiscal buscarNotasPorChave(@Param("chave") String  chave);

    void atualizarStatusNota(@Param("id") Long id, @Param("status") Integer status);

    List<NotaFiscal> buscarNotasPendentes();
}
