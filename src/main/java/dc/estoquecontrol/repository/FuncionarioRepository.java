package dc.estoquecontrol.repository;

import dc.estoquecontrol.entity.Funcionario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FuncionarioRepository extends JpaRepository<Funcionario, UUID> {
    Page<Funcionario> findAllByNomeContainingIgnoreCaseAndAtivoTrue(String nome, Pageable pageable);

    Page<Funcionario> findAllByAtivoTrue(Pageable pageable);
}
