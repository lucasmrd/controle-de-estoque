package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.AtualizarProdutoRequest;
import dc.estoquecontrol.dto.request.CriarProdutoRequest;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.entity.Categoria;
import dc.estoquecontrol.entity.Produto;
import dc.estoquecontrol.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
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
        repository.save(produto);
        var dtoResponse = new MostrarProdutoResponse(produto);

        var uri = uriBuilder.path("/api/produtos/{id}").buildAndExpand(produto.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }

    public ResponseEntity<Page<MostrarProdutoResponse>> listarTodosOsProdutos(
            @RequestParam(required = false) Categoria categoria,
            Pageable pageable) {

        Page<Produto> page;
        if (categoria != null) {
            page = repository.
                    findAllByAtivoTrueAndCategoria(categoria, pageable);
        } else {
            page = repository.findAllByAtivoTrue(pageable);
        }

        Page<MostrarProdutoResponse> dtoPage = page.map(MostrarProdutoResponse::new);

        return ResponseEntity.ok(dtoPage);
    }

    public ResponseEntity listarProdutoPorId(UUID id) {
        var dtoResponse = new MostrarProdutoResponse(repository.getReferenceById(id));

        return ResponseEntity.ok(dtoResponse);
    }

    @Transactional
    public ResponseEntity atualizar(AtualizarProdutoRequest dto, UUID id) {
        var produto = repository.getReferenceById(id);
        produto.atualizar(dto);

        return ResponseEntity.ok(new MostrarProdutoResponse(produto));
    }

    @Transactional
    public ResponseEntity deletar(UUID id) {
        var produto = repository.getReferenceById(id);
        produto.setAtivo(false);

        return ResponseEntity.noContent().build();
    }
}
