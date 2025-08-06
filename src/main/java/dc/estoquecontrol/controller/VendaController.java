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
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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

    @GetMapping("/filtrar/mes")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorMes(
            @RequestParam Integer mes,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorMes(mes, pageable);
    }

    @GetMapping("/filtrar/ano")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorAno(
            @RequestParam Integer ano,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorAno(ano, pageable);
    }

    @GetMapping("/filtrar/nome")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorNome(
            @RequestParam String nome,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorNome(nome, pageable);
    }

    @GetMapping("/filtrar/periodo")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorPeriodo(dataInicio, dataFim, pageable);
    }

    @GetMapping("/filtrar/periodo-nome")
    public ResponseEntity<Page<MostrarVendaResponse>> filtrarPorPeriodoENome(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @RequestParam String nome,
            @PageableDefault Pageable pageable
    ) {
        return service.filtrarPorPeriodoENome(dataInicio, dataFim, nome, pageable);
    }
}
