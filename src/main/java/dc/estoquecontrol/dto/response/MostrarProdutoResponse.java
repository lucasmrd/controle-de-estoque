package dc.estoquecontrol.dto.response;

import dc.estoquecontrol.entity.Categoria;
import dc.estoquecontrol.entity.Produto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record MostrarProdutoResponse(
        UUID id,
        String nome,
        Categoria categoria,
        Integer quantidade,
        BigDecimal preco,
        LocalDateTime data) {

    public MostrarProdutoResponse(Produto produto) {
        this(produto.getId(), produto.getNome(), produto.getCategoria(), produto.getQuantidade(),
                produto.getPreco(), produto.getData());
    }
}
