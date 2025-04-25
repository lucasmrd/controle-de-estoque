package dc.estoquecontrol.dto.response;

import dc.estoquecontrol.entity.Funcionario;
import dc.estoquecontrol.entity.Venda;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record MostrarVendaResponse(
        UUID id,
        LocalDate data,
        String funcionario,
        String pagamento,
        List<ItemVendaResponseDTO> itens) {
    public MostrarVendaResponse(Venda venda, Funcionario funcionario, List<ItemVendaResponseDTO> itens) {
        this(venda.getId(), venda.getData(), funcionario.getNome(), venda.getPagamento(), itens);
    }
}
