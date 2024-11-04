package br.pucpr.authserver.produto

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProdutoRepository : JpaRepository<Produto, Long> {
    fun findByCodigoProduto(codigoProduto: Int): Produto?

    @Query("SELECT new br.pucpr.authserver.produto.ProdutoResponseBusca(p.codigoProduto, p.descricao, p.preco, c.descricao) FROM Produto p INNER JOIN CodGruEst c ON p.codGruEst = c.codigo")
    fun findAllProdutos(): List<ProdutoResponseBusca>
}