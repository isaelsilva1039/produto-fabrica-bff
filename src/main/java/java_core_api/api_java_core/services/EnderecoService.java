package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.Endereco;
import java_core_api.api_java_core.mapper.EnderecoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class EnderecoService {

    @Autowired
    private EnderecoMapper enderecoMapper;

    public void salvarEndereco(Endereco endereco) {

        enderecoMapper.inserirEndereco(endereco);

    }
}
