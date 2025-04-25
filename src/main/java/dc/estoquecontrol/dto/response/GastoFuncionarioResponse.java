package dc.estoquecontrol.dto.response;

import java.math.BigDecimal;
import java.util.UUID;

public record GastoFuncionarioResponse(
        UUID idFuncionario,
        String nomeFuncionario,
        BigDecimal valorTotalGasto) {
}
