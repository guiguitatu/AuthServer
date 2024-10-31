package br.pucpr.authserver.produto

import br.pucpr.authserver.pedidos.Pedido
import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
@Table(name = "tblProdutos")
open class Produto(
    @Id @GeneratedValue
    var id: Long? = null,

    @Column(nullable = false)
    var codigoProduto: Int,

    @Column(nullable = false)
    var descricao: String,

    @Column(nullable = false)
    var preco: Double,

) {
    fun toResponse() = ProdutoResponse(codigoProduto, descricao, preco)
}