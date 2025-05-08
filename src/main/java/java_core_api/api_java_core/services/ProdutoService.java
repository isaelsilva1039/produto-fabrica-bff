package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.Produto;
import java_core_api.api_java_core.mapper.ProdutoMapper; // Alterar para ProdutoMapper
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoMapper produtoMapper; // Alterar para ProdutoMapper

    public void salvar(Produto produto) throws Exception {
        produtoMapper.salvar(produto);
    }

    public List<Produto> listarTodos() {
        return produtoMapper.listarTodas();
    }

    public void atualizar(Produto produto) throws Exception {
        produtoMapper.atualizar(produto);
    }

    public void deletar(Long id) throws Exception {
        produtoMapper.deletar(id);
    }

    public Produto buscarPorId(Long id) {
        return produtoMapper.buscarPorId(id);
    }
}
