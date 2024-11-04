package br.pucpr.authserver.codgruest

import br.pucpr.authserver.mesa.Mesa
import org.springframework.data.jpa.repository.JpaRepository


interface CodGruEstRepository: JpaRepository<CodGruEst, Long> {

    fun save(codGruEst: CodGruEst): CodGruEst

    fun findCodGruEstByCodigo(id: Int): CodGruEst?

}