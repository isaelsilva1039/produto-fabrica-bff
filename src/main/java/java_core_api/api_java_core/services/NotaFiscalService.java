package java_core_api.api_java_core.services;

import java_core_api.api_java_core.domain.NotaFiscal;
import java_core_api.api_java_core.dtos.ColetaNotaDTO;
import java_core_api.api_java_core.enums.StatusNotaFiscal;
import java_core_api.api_java_core.mapper.NotaFiscalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;


@Service
public class NotaFiscalService {

    @Autowired
    private NotaFiscalMapper notaFiscalMapper;

    @Transactional
    public ResponseEntity<?> coletarNota(ColetaNotaDTO dto, Long usuarioId) {
        System.out.println("[INFO] Iniciando criação da NotaFiscal..." + dto.getChaveNf());

        NotaFiscal notaFiscalExistente = notaFiscalMapper.buscarNotasPorChave(dto.getChaveNf());

        if (notaFiscalExistente != null) {
            String mensagem = "Nota fiscal já foi coletada para a chave: " + dto.getChaveNf();
            System.out.println("[INFO] " + mensagem);
            return ResponseEntity.ok(mensagem);
        }

        NotaFiscal nota = new NotaFiscal();
        nota.setUsuarioId(usuarioId);
        nota.setChaveNf(dto.getChaveNf());
        nota.setUrlNf(dto.getUrlNf());
        nota.setDataHoraColeta(dto.getDataHoraColeta());
        nota.setStatus(StatusNotaFiscal.PENDENTE);

        System.out.println("[INFO] NotaFiscal montada: " + nota);

        notaFiscalMapper.inserirNotaFiscal(nota);

        System.out.println("[INFO] NotaFiscal inserida no banco de dados com sucesso.");

        return ResponseEntity.ok(nota);
    }




    public List<NotaFiscal> buscarNotasPorUsuario(Long usuarioId) {
        return notaFiscalMapper.buscarNotasPorUsuario(usuarioId);
    }



    public List<NotaFiscal> buscarNotasPendentes() {
        return notaFiscalMapper.buscarNotasPendentes();
    }

}
