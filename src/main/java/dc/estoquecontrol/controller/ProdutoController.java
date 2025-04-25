package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.AtualizarProdutoRequest;
import dc.estoquecontrol.dto.request.CriarProdutoRequest;
import dc.estoquecontrol.dto.response.MostrarProdutoResponse;
import dc.estoquecontrol.service.ProdutoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid CriarProdutoRequest dto) {
        return service.criar(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarProdutoResponse>> listarTodosOsProdutos(@PageableDefault(size = 20) Pageable pageable) {
        return service.listarTodosOsProdutos(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity listarProdutoPorId(@PathVariable UUID id) {
        return service.listarProdutoPorId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody AtualizarProdutoRequest dto, @PathVariable UUID id) {
        return service.atualizar(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable UUID id) {
        return service.deletar(id);
    }

}
