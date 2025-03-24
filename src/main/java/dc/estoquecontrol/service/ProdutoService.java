package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.CriarProdutoRequest;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.entity.Produto;
import dc.estoquecontrol.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

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
}
