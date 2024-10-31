package br.pucpr.authserver.mesa

import jakarta.validation.constraints.NotNull

class MesaResponse (
    val numeroMesa:Int,
    val fechada: String?
    )