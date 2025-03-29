package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.CriarEntradaRequest;
import dc.estoquecontrol.dto.response.MostrarEntradaResponse;
import dc.estoquecontrol.service.EntradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

    @Autowired
    private EntradaService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid CriarEntradaRequest dto) {
        return service.criar(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarEntradaResponse>> listarTodasAsEntradas(@PageableDefault Pageable pageable) {
        return service.listarTodasAsEntradas(pageable);
    }

}
