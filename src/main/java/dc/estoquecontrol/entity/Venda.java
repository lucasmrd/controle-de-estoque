package dc.estoquecontrol.entity;

import dc.estoquecontrol.dto.request.CriarVendaRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vendas")
public class Venda {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_funcionario")
    private Funcionario funcionario;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<VendaProduto> vendaProdutos;

    private String pagamento;

    private LocalDate data;

    public Venda(CriarVendaRequest dto, Funcionario funcionario) {
        this.funcionario = funcionario;
        this.pagamento = dto.pagamento();
        this.data =  dto.data();
    }
}
