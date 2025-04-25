package dc.estoquecontrol.dto.request;

import jakarta.validation.constraints.NotNull;

public record CriarFuncionarioRequest(@NotNull String nome) {
}
