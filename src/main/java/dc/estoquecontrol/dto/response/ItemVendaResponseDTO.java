package dc.estoquecontrol.dto.response;

import java.math.BigDecimal;

public record ItemVendaResponseDTO(
        String nomeProduto,
        Integer quantidade,
        BigDecimal valor) {
}
