package dc.estoquecontrol.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record ItemVendaResponseDTO(
        UUID idProduto,
        String nomeProduto,
        Integer quantidade,
        BigDecimal valor) {
}
