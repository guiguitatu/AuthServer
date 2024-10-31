package br.pucpr.authserver.produto

import br.pucpr.authserver.exceptions.BadRequestException
import br.pucpr.authserver.exceptions.NotFoundException
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/produto")
class ProdutoController(private val service: ProdutoService) {

    @Operation(summary = "Cria um novo Produto")
    @PostMapping()
    fun createProduct(@RequestBody @Validated req: ProdutoResponse): BadRequestException {
        val produto = Produto(codigoProduto = req.codigoProduto, descricao = req.descricao, preco = req.preco)
        service.saveProdut(produto)
        return BadRequestException("Produto salvo com sucesso")
    }

    @Operation(summary = "Pega o Produto atravéz do ID")
    @GetMapping("/{IdProduto}")
    fun getProduto(@PathVariable("IdProduto") codigo: Long): ResponseEntity<ProdutoResponse> {
        val produto = service.productGetById(codigo).orElseThrow { NotFoundException("Produto não encontrado") }
        return ResponseEntity.ok(produto.toResponse())
    }

    @Operation(summary = "Lista todos os Produtos")
    @GetMapping
    fun getAllProdutos(): ResponseEntity<List<ProdutoResponse>> {
        val produtos = service.getAllProdutos().map { it.toResponse() }
        return ResponseEntity.ok(produtos)
    }

    @Operation(summary = "Pega o Produto atravéz do Código")
    @GetMapping("/codigo/{codigoProduto}")
    fun getProdutoByCodigo(@PathVariable("codigoProduto") codigoProduto: Int): ResponseEntity<ProdutoResponse> {
        val produto = service.getProdutoByCodigo(codigoProduto) ?: throw NotFoundException("Produto não encontrado")
        return ResponseEntity.ok(produto.toResponse())
    }

    @Operation(summary = "Atualiza um Produto existente")
    @PutMapping("/{id}")
    fun updateProduct(@PathVariable id: Long, @RequestBody @Validated req: ProdutoResponse): ResponseEntity<ProdutoResponse> {
        val updatedProduto = Produto(codigoProduto = req.codigoProduto, descricao = req.descricao, preco = req.preco)
        val savedProduto = service.updateProduct(id, updatedProduto)
        return ResponseEntity.ok(savedProduto.toResponse())
    }

    @Operation(summary = "Deleta um Produto existente")
    @DeleteMapping("/{id}")
    fun deleteProduct(@PathVariable id: Long): ResponseEntity<Unit> {
        service.deleteProduct(id)
        return ResponseEntity.noContent().build()
    }

}