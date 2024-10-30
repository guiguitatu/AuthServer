package br.pucpr.authserver.pedidos

import br.pucpr.authserver.exceptions.NotFoundException
import br.pucpr.authserver.produto.ProdutoService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pedido")
class PedidoController(
    val service: PedidoService,
    private val produtoService: ProdutoService
) {

    @Operation(summary = "Lista todos os Pedidos")
    @GetMapping("/{pedido}")
    fun listaPedidos(@RequestParam("numeroPedido") pedido: Int ) =
        service.listaPedidos(pedido)

    @Operation(summary = "Cria um novo Pedido")
    @PostMapping()
    fun savePedido(@RequestBody req: PedidoRequest): ResponseEntity<PedidoResponse> {
        val produtos = req.codigoprodutos.map { codigo ->
            produtoService.getProdutoByCodigo(codigo) ?: throw NotFoundException("Produto com código $codigo não encontrado")
        }.toMutableSet()
        val pedido = Pedido(numeroPedido = req.numeropedido, numeromesa = req.numeromesa, codigoProduto = req.codigoprodutos.first(), quantidade = req.quantidade, codGruEst = req.codgruest, produtos = produtos)
        val salvar = service.save(pedido)
        return ResponseEntity.status(HttpStatus.CREATED).body(salvar.toResponse())
    }

}
