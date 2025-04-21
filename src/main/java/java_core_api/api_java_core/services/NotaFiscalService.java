package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.NotaFiscal;
import java_core_api.api_java_core.dtos.ColetaNotaDTO;
import java_core_api.api_java_core.enums.StatusNotaFiscal;
import java_core_api.api_java_core.mapper.NotaFiscalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalMapper notaFiscalMapper;

    public void coletarNota(ColetaNotaDTO dto, Long usuarioId) {
        NotaFiscal nota = new NotaFiscal();
        nota.setUsuarioId(usuarioId);
        nota.setChaveNf(dto.getChaveNf());
        nota.setUrlNf(dto.getUrlNf());
        nota.setDataHoraColeta(dto.getDataHoraColeta());
        nota.setStatus(StatusNotaFiscal.PENDENTE);

        notaFiscalMapper.inserirNotaFiscal(nota);
    }


    public List<NotaFiscal> buscarNotasPorUsuario(Long usuarioId) {
        return notaFiscalMapper.buscarNotasPorUsuario(usuarioId);
    }



    public List<NotaFiscal> buscarNotasPendentes() {
        return notaFiscalMapper.buscarNotasPendentes();
    }

}
