package dc.estoquecontrol.dto.response;

import dc.estoquecontrol.entity.Entrada;
import dc.estoquecontrol.entity.Produto;

import java.time.LocalDate;
import java.util.UUID;

public record MostrarEntradaResponse(
        UUID id,
        UUID idProduto,
        String nomeProduto,
        Integer quantidade,
        LocalDate data) {

    public MostrarEntradaResponse(Entrada entrada) {
        this(entrada.getId(), entrada.getProduto().getId(),
                entrada.getProduto().getNome(), entrada.getQuantidade(), entrada.getData());
    }
}
