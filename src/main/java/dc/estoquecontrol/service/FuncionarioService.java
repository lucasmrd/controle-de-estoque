package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.CriarFuncionarioRequest;
import dc.estoquecontrol.dto.response.MostrarFuncionarioResponse;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.entity.Funcionario;
import dc.estoquecontrol.repository.FuncionarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

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
        var page = repository.findAll(pageable).map(MostrarFuncionarioResponse::new);

        if (page.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return ResponseEntity.ok(page);
    }
}
