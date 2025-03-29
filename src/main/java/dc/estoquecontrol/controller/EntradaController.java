package dc.estoquecontrol.controller;

import dc.estoquecontrol.dto.request.CriarEntradaRequest;
import dc.estoquecontrol.service.EntradaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/entradas")
public class EntradaController {

    @Autowired
    private EntradaService service;

    @PostMapping
    public ResponseEntity criar(@RequestBody @Valid CriarEntradaRequest dto) {
        return service.criar(dto);
    }


}
