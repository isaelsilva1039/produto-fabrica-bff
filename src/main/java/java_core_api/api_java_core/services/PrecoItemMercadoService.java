package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.*;
import java_core_api.api_java_core.dtos.CupomImportacaoDTO;
import java_core_api.api_java_core.dtos.EmitenteDTO;
import java_core_api.api_java_core.dtos.PrecoItemMercadoDTO;
import java_core_api.api_java_core.dtos.ProdutoImportadoDTO;
import java_core_api.api_java_core.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PrecoItemMercadoService {

    @Autowired
    private PrecoItemMercadoMapper precoItemMercadoMapper;

    public List<PrecoItemMercadoDTO> buscarTodos() {
        return precoItemMercadoMapper.buscarTodos();
    }


    public List<PrecoItemMercadoDTO> buscarPaginado(int page, int size, Double latitude, Double longitude, Double raioKm) {
        int offset = page * size;
        return precoItemMercadoMapper.buscarPaginado(offset, size, latitude, longitude, raioKm);
    }


    public List<PrecoItemMercadoDTO> buscarPorProdutoOrdenadoPorPreco(Long idProdutoItem, Double latitude, Double longitude, Double raioKm) {
        return precoItemMercadoMapper.buscarPorProdutoOrdenadoPorPreco(idProdutoItem, latitude, longitude, raioKm);
    }


    public List<PrecoItemMercadoDTO> buscarPorProdutosPorCidade(String cidade) {
        return precoItemMercadoMapper.buscarPorProdutosPorCidade(cidade);
    }

}
