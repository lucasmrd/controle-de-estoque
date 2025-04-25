package dc.estoquecontrol.repository;

import dc.estoquecontrol.entity.Entrada;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.UUID;

public interface EntradaRepository extends JpaRepository<Entrada, UUID> {
    @Query("SELECT e FROM entradas e WHERE EXTRACT(MONTH FROM e.data) = :mes AND EXTRACT(YEAR FROM e.data) = :ano")
    Page<Entrada> findByMesEAno(@Param("mes") int mes, @Param("ano") int ano, Pageable pageable);

    @Query("SELECT e FROM entradas e WHERE EXTRACT(MONTH FROM e.data) = :mes")
    Page<Entrada> findByMes(@Param("mes") int mes, Pageable pageable);

    @Query("SELECT e FROM entradas e WHERE EXTRACT(YEAR FROM e.data) = :ano")
    Page<Entrada> findByAno(@Param("ano") int ano, Pageable pageable);

}
