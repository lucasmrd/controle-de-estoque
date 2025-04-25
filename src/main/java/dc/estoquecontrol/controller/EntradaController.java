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

    @GetMapping("/filtrar")
    public ResponseEntity<Page<MostrarEntradaResponse>> filtrarPorMesEAno(
            @RequestParam Integer mes,
            @RequestParam Integer ano,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorMesEAno(mes, ano, pageable);
    }

    // Filtragem por Mês
    @GetMapping("/filtrar/mes")
    public ResponseEntity<Page<MostrarEntradaResponse>> filtrarPorMes(
            @RequestParam Integer mes,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorMes(mes, pageable);
    }

    // Filtragem por Ano
    @GetMapping("/filtrar/ano")
    public ResponseEntity<Page<MostrarEntradaResponse>> filtrarPorAno(
            @RequestParam Integer ano,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorAno(ano, pageable);
    }
}
