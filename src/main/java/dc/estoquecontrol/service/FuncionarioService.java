package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.AtualizarFuncionarioRequest;
import dc.estoquecontrol.dto.request.CriarFuncionarioRequest;
import dc.estoquecontrol.dto.response.GastoFuncionarioResponse;
import dc.estoquecontrol.dto.response.MostrarFuncionarioResponse;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.entity.Funcionario;
import dc.estoquecontrol.repository.FuncionarioRepository;
import dc.estoquecontrol.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.time.LocalDate;
import java.util.UUID;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    @Autowired
    private VendaRepository vendaRepository;

    @Transactional
    public ResponseEntity criar(CriarFuncionarioRequest dto) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        var funcionario = new Funcionario(dto);
        repository.save(funcionario);
        var dtoResponse = new MostrarFuncionarioResponse(funcionario);

        var uri = uriBuilder.path("/api/funcinarios/{id}")
                .buildAndExpand(funcionario.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }

    public ResponseEntity<Page<MostrarFuncionarioResponse>> listarTodosOsFuncionarios(Pageable pageable) {
        var page = repository.findAllByAtivoTrue(pageable).map(MostrarFuncionarioResponse::new);

        return ResponseEntity.ok(page);
    }

    public ResponseEntity<Page<MostrarFuncionarioResponse>> listarFuncionariosPeloNome(Pageable pageable, String nome) {
        Page<Funcionario> page = repository.findAllByNomeContainingIgnoreCaseAndAtivoTrue(nome, pageable);
        Page<MostrarFuncionarioResponse> responsePage = page.map(MostrarFuncionarioResponse::new);

        return ResponseEntity.ok(responsePage);
    }

    @Transactional
    public ResponseEntity atualizar(AtualizarFuncionarioRequest dto, UUID id) {
        var funcionario = repository.getReferenceById(id);
        funcionario.atualizar(dto);

        return ResponseEntity.ok(new MostrarFuncionarioResponse(funcionario));
    }

    @Transactional
    public ResponseEntity deletar(UUID id) {
        var funcionario = repository.getReferenceById(id);
        funcionario.setAtivo(false);

        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionarios(int mes, int ano, Pageable pageable) {
        Page<GastoFuncionarioResponse> gastos = vendaRepository.findGastosFuncionarios(mes, ano, pageable);

        return ResponseEntity.ok(gastos);
    }

    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionariosDescontoEmFolha(int mes, int ano, Pageable pageable) {
        Page<GastoFuncionarioResponse> gastos = vendaRepository.findGastosFuncionariosDescontoEmFolha(mes, ano, pageable);
        return ResponseEntity.ok(gastos);
    }

    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionariosPorPeriodo(
            LocalDate dataInicio,
            LocalDate dataFim,
            Pageable pageable) {

        Page<GastoFuncionarioResponse> gastos = vendaRepository.findGastosPorPeriodo(dataInicio, dataFim, pageable);

        return ResponseEntity.ok(gastos);
    }

    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionariosDescontoEmFolhaPorPeriodo(
            LocalDate dataInicio,
            LocalDate dataFim,
            Pageable pageable) {

        Page<GastoFuncionarioResponse> gastos = vendaRepository.findGastosPorPeriodoDescontoEmFolha(dataInicio, dataFim, pageable);

        return ResponseEntity.ok(gastos);
    }
}
