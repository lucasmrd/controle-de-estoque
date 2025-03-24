package dc.estoquecontrol.dto.request;

import dc.estoquecontrol.entity.Categoria;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record AtualizarProdutoRequest(
        @NotNull UUID id,
        String nome,
        Categoria categoria,
        Integer quantidade,
        BigDecimal preco,
        LocalDateTime data) {
}
