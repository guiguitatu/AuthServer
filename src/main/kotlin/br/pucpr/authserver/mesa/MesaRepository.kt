package br.pucpr.authserver.mesa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.awt.List

interface MesaRepository: JpaRepository<Mesa, Long> {

    fun findByNumeroMesa(numeroMesa: Int): Array<Mesa>

    @Query(value = "SELECT fechada FROM Mesa WHERE numeroMesa = :numeroMesa")
    fun findAbertaByNumeroMesa(@Param("numeroMesa") numeroMesa: Int): String?

    @Query(value = "SELECT MAX(id) FROM Mesa")
    fun findMaxId(): Long?

}