package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.*;
import java_core_api.api_java_core.dtos.*;
import java_core_api.api_java_core.mapper.*;
import java_core_api.api_java_core.utils.EnderecoUtil;
import java_core_api.api_java_core.utils.GeolocalizacaoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Service
public class CupomImportacaoService {

    @Autowired
    private MercadoMapper mercadoMapper;

    @Autowired
    private ProdutoMapper produtoMapper;

    @Autowired
    private ProdutoItemMapper produtoItemMapper;

    @Autowired
    private CupomMapper cupomMapper;

    @Autowired
    private CupomItemMapper cupomItemMapper;

    @Autowired
    private NotaFiscalMapper notaFiscalMapper;

    @Autowired
    private PrecoItemMercadoMapper precoItemMercadoMapper;

    @Autowired
    private ProdutoItemInfoFiscalMapper produtoItemInfoFiscalMapper;

    @Autowired
    private GeolocalizacaoUtils geolocalizacaoUtils;

    @Autowired
    private EnderecoMapper enderecoMapper;

    public void processar(CupomImportacaoDTO dto) {
        EmitenteDTO emitente = dto.getEmitente();

        NotaFiscal notaFiscal = notaFiscalMapper.buscarNotasPorChave(emitente.getChaveAcesso());

        if (notaFiscal == null) {
            throw new IllegalStateException("Nota fiscal não encontrada para chave : " + emitente.getChaveAcesso());
        }

        EnderecoDTO enderecoDTO = EnderecoUtil.buscarEnderecoPorCnpj(emitente.getCnpj());

        Endereco endereco = null;

        if (enderecoDTO != null) {
            endereco = new Endereco();
            endereco.setCep(enderecoDTO.getCep());
            endereco.setTipoLogradouro(enderecoDTO.getTipoLogradouro());
            endereco.setLogradouro(enderecoDTO.getLogradouro());
            endereco.setNumero(enderecoDTO.getNumero());
            endereco.setComplemento(enderecoDTO.getComplemento());
            endereco.setBairro(enderecoDTO.getBairro());
            endereco.setCidade(enderecoDTO.getCidade());
            endereco.setEstado(enderecoDTO.getEstado());

            enderecoMapper.inserirEndereco(endereco);
        }

        Mercado mercado = mercadoMapper.findByCnpj(emitente.getCnpj());
        if (mercado == null) {
            mercado = new Mercado();

            String razao = (enderecoDTO != null && enderecoDTO.getRazaoSocial() != null && !enderecoDTO.getRazaoSocial().isEmpty())
                    ? enderecoDTO.getRazaoSocial()
                    : emitente.getRazaoSocial();

            String fantasia = (enderecoDTO != null && enderecoDTO.getNomeFantasia() != null && !enderecoDTO.getNomeFantasia().isEmpty())
                    ? enderecoDTO.getNomeFantasia()
                    : emitente.getNomeFantasia();

            mercado.setRazaoSocial(razao);
            mercado.setFantasia(fantasia);
            mercado.setEndereco(endereco);
            mercado.setCnpj(emitente.getCnpj());

            CoordenadasDTO coordenadas = geolocalizacaoUtils.obterCoordenadasPorCNPJ(emitente.getCnpj());
            if (coordenadas != null) {
                mercado.setLongitude(coordenadas.getLongitude());
                mercado.setLatitude(coordenadas.getLatitude());
            }

            mercadoMapper.insert(mercado);
        }

        Cupom cupom = new Cupom();
        cupom.setMercado(mercado);
        cupom.setDataColeta(LocalDateTime.now());
        cupom.setDataCompra(LocalDateTime.now());
        cupom.setValorCompras(BigDecimal.ZERO);
        cupom.setIdUsuario(notaFiscal.getUsuarioId());
        cupomMapper.insert(cupom);

        for (ProdutoImportadoDTO prod : dto.getProdutos()) {
            ProdutoItem item = produtoItemMapper.findByCodigoBarras(prod.getCodigoBarras());

            Produto produto;
            if (item != null) {
                produto = item.getProduto();
            } else {
                produto = produtoMapper.findByDescricao(prod.getDescricao());
                if (produto == null) {
                    produto = new Produto();
                    produto.setDescricao(prod.getDescricao());
                    Categoria categoria = new Categoria();
                    // categoria.setId(1L);
                    produto.setCategoria(categoria);
                    produtoMapper.insert(produto);
                }

                item = new ProdutoItem();
                item.setProduto(produto);
                item.setDescricao(prod.getDescricao());
                item.setCodigoBarras(prod.getCodigoBarras());
                item.setTipoCodigo("EAN");

                Marca marca = new Marca();
                // marca.setId(1L);
                item.setMarca(marca);

                item.setPeso(BigDecimal.ZERO);
                item.setHashImagem(null);

                produtoItemMapper.insert(item);
            }

            CupomItem cupomItem = new CupomItem();
            cupomItem.setCupom(cupom);
            cupomItem.setProdutoItem(item);
            cupomItem.setQtde(parseDecimalBr(prod.getQtde()));
            cupomItem.setPreco(parseDecimalBr(prod.getValorTotal()));
            cupomItemMapper.insert(cupomItem);

            cupom.setValorCompras(cupom.getValorCompras().add(cupomItem.getPreco()));

            // 🛠️ Novo ajuste AQUI:
            BigDecimal precoUnitario = cupomItem.getPreco();
            if (cupomItem.getQtde() != null && cupomItem.getQtde().compareTo(BigDecimal.ZERO) > 0) {
                precoUnitario = precoUnitario.divide(cupomItem.getQtde(), 2, BigDecimal.ROUND_HALF_UP);
            }

            PrecoItemMercado precoAtual = new PrecoItemMercado();
            precoAtual.setProdutoItem(item);
            precoAtual.setMercado(mercado);
            precoAtual.setPreco(precoUnitario);
            precoAtual.setDataColeta(cupom.getDataColeta());
            precoItemMercadoMapper.upsertPreco(precoAtual);

            ProdutoItemInfoFiscal fiscal = new ProdutoItemInfoFiscal();
            fiscal.setProdutoItem(item);
            fiscal.setMercado(mercado);
            fiscal.setCstIcms(prod.getCstICMS());
            fiscal.setValorTotalIcms(parseDecimalBr(prod.getValorTotalICMS()));
            fiscal.setCstPis(prod.getCstPIS());
            fiscal.setValorPis(parseDecimalBr(prod.getValorPIS()));
            fiscal.setCstCofins(prod.getCstCOFINS());
            fiscal.setValorCofins(parseDecimalBr(prod.getValorCOFINS()));
            fiscal.setDataAtualizacao(LocalDateTime.now());
            produtoItemInfoFiscalMapper.upsertInfoFiscal(fiscal);
        }

        cupomMapper.updateValorTotal(cupom);
        notaFiscalMapper.atualizarStatusNota(notaFiscal.getId(), 1);
    }

    private BigDecimal parseDecimalBr(String valor) {
        if (valor == null || valor.trim().isEmpty()) return BigDecimal.ZERO;

        try {
            // Debug
            System.out.println("🧪 Valor fiscal recebido: [" + valor + "]");

            // Remove espaços invisíveis, quebra espaços, substitui vírgula por ponto
            String sanitized = valor
                    .replaceAll("[\\u00A0\\u2007\\u202F]", "") // NBSP etc
                    .replace(" ", "")
                    .trim()
                    .replace(",", ".");

            System.out.println("🔍 Valor sanitizado: [" + sanitized + "]");
            return new BigDecimal(sanitized);

        } catch (Exception e) {
            System.err.println("❌ Erro ao converter valor fiscal: [" + valor + "]");
            e.printStackTrace();
            return BigDecimal.ZERO;
        }
    }
}
