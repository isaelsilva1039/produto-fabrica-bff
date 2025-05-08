package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Fabricante;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FabricanteMapper {
    List<Fabricante> listarTodas();
    void salvar(Fabricante bandeira);

    void atualizar(Fabricante fabricante);
    void deletar(Long id);
    Fabricante buscarPorId(Long id);
}
