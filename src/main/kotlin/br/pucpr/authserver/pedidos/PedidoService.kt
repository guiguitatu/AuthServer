package br.pucpr.authserver.pedidos

import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Service

@Service
class PedidoService(private val repository: PedidoRepository) {

    fun save(pedido: Pedido) : Pedido {
        return repository.save(pedido)
    }

    fun listaPedidos(numeroPedido: Int) : List<Pedido> {
        return repository.findAllByNumeroPedido(numeroPedido)
    }

}