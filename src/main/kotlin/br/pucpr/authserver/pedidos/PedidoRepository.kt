package br.pucpr.authserver.pedidos

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PedidoRepository : JpaRepository<Pedido, Int> {
    fun findByNumeroPedido(numeroPedido: Int): List<Pedido>
}