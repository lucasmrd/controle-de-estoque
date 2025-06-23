package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.AtualizarFuncionarioRequest;
import dc.estoquecontrol.dto.request.CriarFuncionarioRequest;
import dc.estoquecontrol.dto.response.GastoFuncionarioResponse;
import dc.estoquecontrol.dto.response.MostrarFuncionarioResponse;
import dc.estoquecontrol.service.FuncionarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/api/funcionarios")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid CriarFuncionarioRequest dto) {
        return(service.criar(dto));
    }

    @GetMapping
    public ResponseEntity<Page<MostrarFuncionarioResponse>> listarTodosOsFuncionarios(@PageableDefault
                                                                                           Pageable pageable) {
        return service.listarTodosOsFuncionarios(pageable);
    }

    @GetMapping("/buscar")
    public ResponseEntity<Page<MostrarFuncionarioResponse>> listarFuncionariosPeloNome(@PageableDefault
                                                                                       Pageable pageable,
                                                                                       @RequestParam String nome) {
        return service.listarFuncionariosPeloNome(pageable, nome);
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@RequestBody AtualizarFuncionarioRequest dto, @PathVariable UUID id) {
        return service.atualizar(dto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable UUID id) {
        return service.deletar(id);
    }

    @GetMapping("/gastos-funcionarios")
    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionarios(
            @RequestParam int mes,
            @RequestParam int ano,
            @PageableDefault Pageable pageable) {

        return service.gastosFuncionarios(mes, ano, pageable);
    }

    @GetMapping("/gastos-funcionarios/folha")
    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionariosDescontoEmFolha(
            @RequestParam int mes,
            @RequestParam int ano,
            @PageableDefault Pageable pageable) {

        return service.gastosFuncionariosDescontoEmFolha(mes, ano, pageable);
    }

    @GetMapping("/gastos-funcionarios/periodo")
    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionariosPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @PageableDefault Pageable pageable) {

        return service.gastosFuncionariosPorPeriodo(dataInicio, dataFim, pageable);
    }

    @GetMapping("/gastos-funcionarios/folha/periodo")
    public ResponseEntity<Page<GastoFuncionarioResponse>> gastosFuncionariosDescontoEmFolhaPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim,
            @PageableDefault Pageable pageable) {

        return service.gastosFuncionariosDescontoEmFolhaPorPeriodo(dataInicio, dataFim, pageable);
    }
}
