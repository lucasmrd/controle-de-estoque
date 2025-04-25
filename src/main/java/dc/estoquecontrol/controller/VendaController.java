package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.CriarVendaRequest;
import dc.estoquecontrol.dto.response.MostrarEntradaResponse;
import dc.estoquecontrol.dto.response.MostrarVendaResponse;
import dc.estoquecontrol.service.VendaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/vendas")
public class VendaController {

    @Autowired
    private VendaService service;

    @PostMapping
    public ResponseEntity criarVenda(@RequestBody @Valid CriarVendaRequest dto){
        return service.criarVenda(dto);
    }

    @GetMapping
    public ResponseEntity<Page<MostrarVendaResponse>> listarTodasAsVendas(@PageableDefault Pageable pageable) {
        return service.listarTodasAsvendas(pageable);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorMesEAno(
            @RequestParam Integer mes,
            @RequestParam Integer ano,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorMesEAno(mes, ano, pageable);
    }

    // Filtragem por Mês
    @GetMapping("/filtrar/mes")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorMes(
            @RequestParam Integer mes,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorMes(mes, pageable);
    }

    // Filtragem por Ano
    @GetMapping("/filtrar/ano")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorAno(
            @RequestParam Integer ano,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorAno(ano, pageable);
    }
}
