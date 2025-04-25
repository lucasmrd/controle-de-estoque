package dc.estoquecontrol.dto.request;

import dc.estoquecontrol.entity.Produto;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CriarEntradaRequest(@NotNull UUID idProduto, @NotNull Integer quantidade, LocalDate data) {
}
