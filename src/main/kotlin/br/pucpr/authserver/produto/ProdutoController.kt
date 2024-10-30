package br.pucpr.authserver.produto

import br.pucpr.authserver.exceptions.NotFoundException
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/produto")
class ProdutoController(private val service: ProdutoService) {

    @Operation(summary = "Cria um novo Produto")
    @PostMapping()
    fun createProduct(@RequestBody @Validated req: ProdutoResponse): ResponseEntity<ProdutoResponse> {
        val produto = Produto(codigoProduto = req.codigoProduto, descricao = req.descricao, preco = req.preco)
        val saved = service.saveProdut(produto)
        return ResponseEntity.status(CREATED).body(saved.toResponse())
    }

    @Operation(summary = "Pega o Produto atravéz do ID")
    @GetMapping("/{Id_do_Produto}")
    fun getProduto(@PathVariable("codigoProduto") codigo: Long): ResponseEntity<ProdutoResponse> {
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

}