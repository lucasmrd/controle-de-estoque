package dc.estoquecontrol.dto.request;

import dc.estoquecontrol.entity.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CriarProdutoRequest(
        @NotBlank
        String nome,
        @NotNull
        Categoria categoria,
        @NotNull
        Integer quantidade,
        @NotNull
        BigDecimal preco) {
}
