package dc.estoquecontrol.entity;

import dc.estoquecontrol.dto.request.AtualizarFuncionarioRequest;
import dc.estoquecontrol.dto.request.CriarFuncionarioRequest;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity(name = "funcionarios")
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String nome;

    private boolean ativo;

    public Funcionario(CriarFuncionarioRequest dto) {
        this.nome = dto.nome();
        this.ativo = true;
    }

    public void atualizar(AtualizarFuncionarioRequest dto) {
        if (dto.nome() != null && !dto.nome().isBlank()) {
            this.nome = dto.nome();
        }
    }
}
