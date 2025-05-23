package dc.estoquecontrol.repository;

import dc.estoquecontrol.entity.Categoria;
import dc.estoquecontrol.entity.Produto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
    Page<Produto> findAllByAtivoTrue(Pageable pageable);

    Page<Produto> findAllByAtivoTrueAndCategoria(Categoria categoria, Pageable pageable);
}
