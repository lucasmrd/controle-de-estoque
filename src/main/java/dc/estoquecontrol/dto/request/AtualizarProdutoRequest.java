package dc.estoquecontrol.dto.request;

import dc.estoquecontrol.entity.Categoria;

import java.math.BigDecimal;

public record AtualizarProdutoRequest(
        String nome,
        Categoria categoria,
        Integer quantidade,
        BigDecimal preco) {
}
