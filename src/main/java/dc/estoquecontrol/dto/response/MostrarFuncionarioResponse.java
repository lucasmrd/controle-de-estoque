package dc.estoquecontrol.dto.response;

import dc.estoquecontrol.entity.Funcionario;

import java.util.UUID;

public record MostrarFuncionarioResponse(UUID id, String nome) {

    public MostrarFuncionarioResponse(Funcionario funcionario) {
        this(funcionario.getId(), funcionario.getNome());
    }
}
