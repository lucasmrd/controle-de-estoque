package dc.estoquecontrol.dto.request;

import dc.estoquecontrol.entity.VendaProduto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record CriarVendaRequest(
        @NotNull
        UUID idFuncionario,

        @NotEmpty
        List<VendaProdutoDTO> produtos,

        @NotBlank
        String pagamento,

        @NotNull
        LocalDate data) {
}
