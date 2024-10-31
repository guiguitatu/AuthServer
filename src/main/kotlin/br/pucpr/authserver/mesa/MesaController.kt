package br.pucpr.authserver.mesa

import br.pucpr.authserver.exceptions.BadRequestException
import br.pucpr.authserver.produto.ProdutoRepository
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/mesa")
class MesaController(val repository: MesaRepository) {

    @PostMapping()
    fun saveMesa(@RequestBody mesa: MesaRequest): ResponseEntity<MesaResponse> {
        val mesaAberta = repository.findAbertaByNumeroMesa(mesa.numeroMesa) ?: "N"
        val id = repository.findMaxId()?.plus(1)
        val bacias = repository.findByNumeroMesa(mesa.numeroMesa)
        var batata: Boolean = true
        bacias.map {
            if (it.numeroMesa == mesa.numeroMesa) batata = false
        }
        if (mesaAberta != "S" && batata) {
            val lindonjohnson = Mesa(id = id, numeroMesa = mesa.numeroMesa, fechada = "N")
            val sim = repository.save(lindonjohnson)
            return ResponseEntity.status(CREATED).body(sim.toResponse())
        } else throw BadRequestException("Mesa já está aberta")
    }

    @GetMapping()
    fun findAll(): List<Mesa> = repository.findAll()

    @GetMapping("/{numeroMesa}")
    fun findByNumeroMesa(@PathVariable numeroMesa: Int): Array<Mesa> = repository.findByNumeroMesa(numeroMesa)

    @DeleteMapping()
    fun deleteMesa(mesa: Mesa) = repository.delete(mesa)

    @GetMapping("/pedidos/{numeroMesa}")
    fun findPedidosPorMesa(@PathVariable("numeroMesa") numeroMesa: Int) = repository.findPedidosPorMesa(numeroMesa)

}
