package br.pucpr.authserver.pedidos

import br.pucpr.authserver.produto.Produto
import jakarta.persistence.*

@Entity
@Table(name = "tblPedidos")
open class Pedido(
    @Id @GeneratedValue
    var id: Int? = null,

    @Column(nullable = false)
    var numeroPedido: Int,

    @Column(nullable = false)
    var numeromesa: Int,

    @Column(nullable = false)
    var quantidade: Int,

    @Column(nullable = false)
    var codGruEst: Int,

    @ManyToMany
    @JoinTable(
        name = "pedido_produto",
        joinColumns = [JoinColumn(name = "pedido_id")],
        inverseJoinColumns = [JoinColumn(name = "produto_id")]
    )
    var produto: MutableList<Produto> = mutableListOf()
) {
    fun toResponse() = PedidoResponse(id!!, numeroPedido, codGruEst, numeromesa, quantidade, produto.map { it.codigoProduto })
}