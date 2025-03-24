package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.AtualizarProdutoRequest;
import dc.estoquecontrol.dto.request.CriarProdutoRequest;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.entity.Produto;
import dc.estoquecontrol.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.UUID;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Transactional
    public ResponseEntity criar(CriarProdutoRequest dto) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        var produto = new Produto(dto);
        var dtoResponse = new MostrarProdutoResponse(produto);
        repository.save(produto);

        var uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }

    public ResponseEntity<Page<MostrarProdutoResponse>> listarTodosOsProdutos(Pageable pageable) {
        var page = repository.findAll(pageable).map(MostrarProdutoResponse::new);

        if (page.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return ResponseEntity.ok(page);
    }

    public ResponseEntity listarProdutoPorId(UUID id) {
        var dtoResponse = new MostrarProdutoResponse(repository.getReferenceById(id));

        return ResponseEntity.ok(dtoResponse);
    }

    @Transactional
    public ResponseEntity atualizar(AtualizarProdutoRequest dto) {
        var produto = repository.getReferenceById(dto.id());
        produto.atualizar(dto);

        return ResponseEntity.ok(new MostrarProdutoResponse(produto));
    }
}
