package br.pucpr.authserver.pedidos

class PedidoResponse (
    val id: Int,
    val numeroPedido: Int,
    val numeromesa: Int,
    val quantidade: Int,
    val codGruEst: Int,
    val codigoProduto: List<Int>
)