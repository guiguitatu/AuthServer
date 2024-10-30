package br.pucpr.authserver.pedidos

import jakarta.validation.constraints.NotBlank


data class PedidoRequest(
    @field:NotBlank
    val numeropedido: Int,
    @field:NotBlank
    val codigoprodutos: MutableList<Int>,
    @field:NotBlank
    val numeromesa: Int,
    @field:NotBlank
    val quantidade: Int,
    @field:NotBlank
    val codgruest: Int,
)