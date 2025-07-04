package dc.estoquecontrol.repository;

import dc.estoquecontrol.dto.response.GastoFuncionarioResponse;
import dc.estoquecontrol.entity.Venda;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface VendaRepository extends JpaRepository<Venda, UUID> {

    @Query("SELECT v FROM vendas v WHERE EXTRACT(MONTH FROM v.data) = :mes AND EXTRACT(YEAR FROM v.data) = :ano")
    Page<Venda> findByMesEAno(@Param("mes") int mes, @Param("ano") int ano, Pageable pageable);

    @Query("SELECT v FROM vendas v WHERE EXTRACT(MONTH FROM v.data) = :mes")
    Page<Venda> findByMes(@Param("mes") int mes, Pageable pageable);

    @Query("SELECT v FROM vendas v WHERE EXTRACT(YEAR FROM v.data) = :ano")
    Page<Venda> findByAno(@Param("ano") int ano, Pageable pageable);

    @Query(value = "SELECT f.id AS idFuncionario, f.nome AS nomeFuncionario, " +
            "SUM(vp.valor) AS totalGasto " +
            "FROM vendas v " +
            "JOIN funcionarios f ON f.id = v.id_funcionario " +
            "JOIN venda_produto vp ON vp.id_venda = v.id " +
            "WHERE f.ativo = true " +
            "AND EXTRACT(MONTH FROM v.data) = :mes " +
            "AND EXTRACT(YEAR FROM v.data) = :ano " +
            "GROUP BY f.id", nativeQuery = true)
    Page<GastoFuncionarioResponse> findGastosFuncionarios(
            @Param("mes") int mes,
            @Param("ano") int ano,
            Pageable pageable);

    @Query(value = "SELECT f.id AS idFuncionario, f.nome AS nomeFuncionario, " +
            "SUM(vp.valor) AS totalGasto " +
            "FROM vendas v " +
            "JOIN funcionarios f ON f.id = v.id_funcionario " +
            "JOIN venda_produto vp ON vp.id_venda = v.id " +
            "WHERE f.ativo = true " +
            "AND v.pagamento = 'Desconto em folha' " +
            "AND EXTRACT(MONTH FROM v.data) = :mes " +
            "AND EXTRACT(YEAR FROM v.data) = :ano " +
            "GROUP BY f.id", nativeQuery = true)
    Page<GastoFuncionarioResponse> findGastosFuncionariosDescontoEmFolha(
            @Param("mes") int mes,
            @Param("ano") int ano,
            Pageable pageable);

    @Query(value = """
    SELECT f.id AS idFuncionario, f.nome AS nomeFuncionario,
           SUM(vp.valor) AS totalGasto
      FROM vendas v
      JOIN funcionarios f ON f.id = v.id_funcionario
      JOIN venda_produto vp ON vp.id_venda = v.id
     WHERE f.ativo = true
       AND v.data BETWEEN :dataInicio AND :dataFim
     GROUP BY f.id
    """,
            nativeQuery = true)
    Page<GastoFuncionarioResponse> findGastosPorPeriodo(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable
    );

    @Query(value = """
    SELECT f.id AS idFuncionario, f.nome AS nomeFuncionario,
           SUM(vp.valor) AS totalGasto
      FROM vendas v
      JOIN funcionarios f ON f.id = v.id_funcionario
      JOIN venda_produto vp ON vp.id_venda = v.id
     WHERE f.ativo = true
       AND v.pagamento = 'Desconto em folha'
       AND v.data BETWEEN :dataInicio AND :dataFim
     GROUP BY f.id
    """,
            nativeQuery = true)
    Page<GastoFuncionarioResponse> findGastosPorPeriodoDescontoEmFolha(
            @Param("dataInicio") LocalDate dataInicio,
            @Param("dataFim") LocalDate dataFim,
            Pageable pageable
    );
}
