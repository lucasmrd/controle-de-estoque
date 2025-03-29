package dc.estoquecontrol.dto.response;

import dc.estoquecontrol.entity.Entrada;
import dc.estoquecontrol.entity.Produto;

import java.time.LocalDate;
import java.util.UUID;

public record MostrarEntradaResponse(
        UUID id,
        Integer quantidade,
        LocalDate data) {

    public MostrarEntradaResponse(Entrada entrada) {
        this(entrada.getId(), entrada.getQuantidade(), entrada.getData());
    }
}
