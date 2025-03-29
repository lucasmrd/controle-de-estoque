package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.CriarEntradaRequest;
import dc.estoquecontrol.dto.response.MostrarEntradaResponse;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.entity.Entrada;
import dc.estoquecontrol.repository.EntradaRepository;
import dc.estoquecontrol.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class EntradaService {

    @Autowired
    private EntradaRepository entradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional
    public ResponseEntity criar(CriarEntradaRequest dto) {
        UriComponentsBuilder uriBuilder = UriComponentsBuilder.newInstance();

        var produto = produtoRepository.getReferenceById(dto.idProduto());

        var entrada = new Entrada(dto, produto);
        entradaRepository.save(entrada);
        var dtoResponse = new MostrarEntradaResponse(entrada);

        produto.somarQuantidade(entrada.getQuantidade());

        var uri = uriBuilder.path("/api/entradas/{id}").buildAndExpand(entrada.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }

    public ResponseEntity<Page<MostrarEntradaResponse>> listarTodasAsEntradas(Pageable pageable) {
        var page = entradaRepository.findAll(pageable).map(MostrarEntradaResponse::new);

        if (page.isEmpty()) {
            throw new EntityNotFoundException();
        }

        return ResponseEntity.ok(page);
    }
}
