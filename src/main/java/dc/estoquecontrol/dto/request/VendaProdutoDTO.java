package dc.estoquecontrol.dto.request;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record VendaProdutoDTO(
        @NotNull
        UUID idProduto,
        @NotNull
        Integer quantidade) {
}
