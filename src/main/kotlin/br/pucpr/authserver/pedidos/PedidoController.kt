package br.pucpr.authserver.pedidos

import br.pucpr.authserver.produto.ProdutoService
import br.pucpr.authserver.exceptions.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
class PedidoController(private val service: PedidoService, private val produtoService: ProdutoService) {

    @GetMapping("/{pedido}")
    fun listaPedidos(@RequestParam("numeroPedido") pedido: Int) =
        service.listaPedidos(pedido)

    @PostMapping()
    fun savePedido(@RequestBody req: PedidoRequest): ResponseEntity<PedidoResponse> {

        val pedido = Pedido(numeroPedido = req.numeropedido, numeromesa = req.numeromesa, quantidade = req.quantidade, codGruEst = req.codgruest, codigoProduto = req.codigoprodutos)
        val salvar = service.save(pedido)
        return ResponseEntity.status(HttpStatus.CREATED).body(salvar.toResponse())
    }
}