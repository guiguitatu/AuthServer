package br.pucpr.authserver.pedidos

import br.pucpr.authserver.users.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository

interface PedidoRepository : JpaRepository<Pedido, Long> {

    fun findAllByNumeroPedido(numeroPedido: Int): List<Pedido>

}