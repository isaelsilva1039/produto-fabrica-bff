package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Bandeira;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BandeiraMapper {
    List<Bandeira> listarTodas();
    void salvar(Bandeira bandeira);
}
