package dc.estoquecontrol.service;

import dc.estoquecontrol.dto.request.CriarEntradaRequest;
import dc.estoquecontrol.dto.response.MostrarEntradaResponse;
import dc.estoquecontrol.entity.Entrada;
import dc.estoquecontrol.repository.EntradaRepository;
import dc.estoquecontrol.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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
        var dtoResponse = new MostrarEntradaResponse(entrada);
        entradaRepository.save(entrada);
        produto.somarQuantidade(entrada.getQuantidade());

        var uri = uriBuilder.path("/api/entradas/{id}").buildAndExpand(entrada.getId()).toUri();

        return ResponseEntity.created(uri).body(dtoResponse);
    }
}
