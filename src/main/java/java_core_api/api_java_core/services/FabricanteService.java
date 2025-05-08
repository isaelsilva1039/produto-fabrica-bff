package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.Fabricante;
import java_core_api.api_java_core.mapper.FabricanteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FabricanteService {

    @Autowired
    private FabricanteMapper fabricanteMapper;

    public void salvar(Fabricante fabricante) throws Exception {

        fabricanteMapper.salvar(fabricante);
    }

    public List<Fabricante> listarTodos() {
        return fabricanteMapper.listarTodas();
    }


    public void atualizar(Fabricante fabricante) throws Exception {
        fabricanteMapper.atualizar(fabricante);
    }

    public void deletar(Long id) throws Exception {
        fabricanteMapper.deletar(id);
    }

    public Fabricante buscarPorId(Long id) {
        return fabricanteMapper.buscarPorId(id);
    }




}
