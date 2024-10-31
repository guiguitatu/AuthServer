package br.pucpr.authserver.mesa

import jakarta.validation.constraints.NotNull

data class MesaRequest (
    @field:NotNull
    val numeroMesa:Int
)