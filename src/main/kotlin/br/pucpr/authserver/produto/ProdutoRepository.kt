package br.pucpr.authserver.produto

import org.springframework.data.jpa.repository.JpaRepository

interface ProdutoRepository : JpaRepository<Produto, Long>{
    fun findByCodigoProduto(codigoProduto: Int): Produto?
}