package dc.estoquecontrol.entity;

import dc.estoquecontrol.dto.request.AtualizarProdutoRequest;
import dc.estoquecontrol.dto.request.CriarProdutoRequest;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "produtos")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;

    private Integer quantidade;

    private BigDecimal preco;

    private LocalDateTime data;

    public Produto(CriarProdutoRequest dto) {
        this.nome = dto.nome();
        this.categoria = dto.categoria();
        this.quantidade = dto.quantidade();
        this.preco = dto.preco();
        this.data = LocalDateTime.now();
    }

    public void atualizar(AtualizarProdutoRequest dto) {
        if (dto.nome() != null && !dto.nome().isBlank())
            this.nome = dto.nome();

        if (dto.categoria() != null)
            this.categoria = dto.categoria();

        if (dto.quantidade() != null)
            this.quantidade = dto.quantidade();

        if (dto.preco() != null)
            this.preco = dto.preco();

        if (dto.data() != null)
            this.data = dto.data();
    }
}
