package br.pucpr.authserver.produto

import br.pucpr.authserver.pedidos.Pedido
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.ManyToMany
import jakarta.persistence.Table


@Entity
@Table(name = "tblProduto")
open class Produto(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false)
    var codigoProduto: Int,

    @Column(nullable = false)
    var descricao: String,

    @Column(nullable = false)
    var preco: Double,

    @ManyToMany(mappedBy = "produtos")
    var pedidos: MutableSet<Pedido> = mutableSetOf()

) {
    fun toResponse() = ProdutoResponse(codigoProduto, descricao, preco)
}