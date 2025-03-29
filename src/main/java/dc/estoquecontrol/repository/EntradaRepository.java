package dc.estoquecontrol.repository;

import dc.estoquecontrol.entity.Entrada;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EntradaRepository extends JpaRepository<Entrada, UUID> {
}
