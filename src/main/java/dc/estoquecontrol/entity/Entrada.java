package dc.estoquecontrol.entity;

import dc.estoquecontrol.dto.request.CriarEntradaRequest;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "entradas")
public class Entrada {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_produto")
    private Produto produto;

    private Integer quantidade;
    private LocalDate data;

    public Entrada(CriarEntradaRequest dto, Produto produto) {
        this.produto = produto;
        this.quantidade = dto.quantidade();
        this.data = dto.data();
    }

}
