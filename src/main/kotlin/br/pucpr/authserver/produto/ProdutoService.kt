package br.pucpr.authserver.produto

import org.springframework.stereotype.Service

@Service
class ProdutoService (val repository: ProdutoRepository) {

    fun saveProdut(produto: Produto) = repository.save(produto)

    fun productGetById(id: Long) = repository.findById(id)

    fun getAllProdutos() = repository.findAll()

    fun getProdutoByCodigo(codigoProduto: Int): Produto? {
        return repository.findByCodigoProduto(codigoProduto)
    }

}