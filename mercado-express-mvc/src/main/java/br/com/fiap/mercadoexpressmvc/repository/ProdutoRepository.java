package br.com.fiap.mercadoexpressmvc.repository;

import br.com.fiap.mercadoexpressmvc.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Camada de acesso a dados. Herda o CRUD completo do JpaRepository.
 */
@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
