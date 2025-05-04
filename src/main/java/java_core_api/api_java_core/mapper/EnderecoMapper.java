package java_core_api.api_java_core.mapper;

import java_core_api.api_java_core.domain.Endereco;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EnderecoMapper {

    // Método para inserir um endereço
    void inserirEndereco(Endereco endereco);
}
