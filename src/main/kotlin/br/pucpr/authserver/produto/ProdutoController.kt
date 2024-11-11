package br.pucpr.authserver.produto

import br.pucpr.authserver.codgruest.CodGruEstService
import br.pucpr.authserver.exceptions.BadRequestException
import br.pucpr.authserver.exceptions.NotFoundException
import io.swagger.v3.oas.annotations.Operation
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
class ProdutoController(
    private val service: ProdutoService,
    private val codservice: CodGruEstService,
    private val repository: ProdutoRepository
) {

    @CrossOrigin(origins = ["http://192.168.1.38:3030"])
    @Operation(summary = "Cria um ou mais Produtos")
    @PostMapping()
    fun createProducts(@RequestBody @Validated reqList: List<ProdutoRequest>): List<ProdutoResponse> {
        val produtos = reqList.map { req ->

            val codGruEst = codservice.codGruEstGetById(req.codGruEst) ?: throw BadRequestException("Código de Grupo de Estoque não encontrado")

            val id = repository.findMax()?.plus(1) ?: 1
            log.info("id={}", id)
            val produto = Produto(
                id = id,
                codigoProduto = req.codigoProduto,
                descricao = req.descricao,
                preco = req.preco,
                codGruEst = req.codGruEst
            )
            service.saveProdut(produto)
            produto.toResponse()
        }
        return produtos
    }

    @Operation(summary = "Pega o Produto atravéz do ID")
    @GetMapping("/{IdProduto}")
    fun getProduto(@PathVariable("IdProduto") codigo: Long): ResponseEntity<ProdutoResponse> {
        val produto = service.productGetById(codigo).orElseThrow { NotFoundException("Produto não encontrado") }
        return ResponseEntity.ok(produto.toResponse())
    }

    @Operation(summary = "Lista todos os Produtos")
    @GetMapping
    fun getAllProdutos(): ResponseEntity<List<ProdutoResponseBusca>> {
        val produtos = service.getAllProdutos()
        return ResponseEntity.ok(produtos)
    }

    @Operation(summary = "Pega o Produto atravéz do Código")
    @GetMapping("/codigo/{codigoProduto}")
    fun getProdutoByCodigo(@PathVariable("codigoProduto") codigoProduto: Int): ResponseEntity<ProdutoResponse> {
    return service.getProdutoByCodigo(codigoProduto)
        ?.let { ResponseEntity.ok(it.toResponse()) }
        ?: throw NotFoundException("Produto não encontrado")
    }

    @GetMapping("/cod/{codigoproduto}")
    fun getCodProbyCodPro(@PathVariable("codigoproduto") codigoproduto: Int): Int  =
        service.getCodByCod(codigoproduto) ?: throw NotFoundException("Produto não encontrado")


    @CrossOrigin(origins = ["http://192.168.1.38:3030"])
    @Operation(summary = "Atualiza um Produto existente")
    @PutMapping("/{id}")
    fun updateProduct(
        @PathVariable id: Long,
        @RequestBody @Validated req: ProdutoRequest
    ): ResponseEntity<ProdutoResponse> {
        val updatedProduto = Produto(
            codigoProduto = req.codigoProduto,
            descricao = req.descricao,
            preco = req.preco,
            codGruEst = req.codGruEst
        )
        val savedProduto = service.updateProduct(id, updatedProduto)
        return ResponseEntity.ok(savedProduto.toResponse())
    }

    @CrossOrigin(origins = ["http://192.168.1.38:3030"])
    @Operation(summary = "Deleta um Produto existente")
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Unit> {
        if (!service.productGetById(id).isPresent) {
            throw NotFoundException("Produto não encontrado")
        }
        service.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

    companion object {
        val log = LoggerFactory.getLogger(ProdutoService::class.java)
    }

}