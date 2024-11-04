package br.pucpr.authserver.mesa

import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.justRun
import io.mockk.mockk
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity
import org.springframework.http.HttpStatus.CREATED
import br.pucpr.authserver.Stubs.mesaStub
import kotlin.random.Random


internal class MesaControllerTest {

    private val mesaServiceRepositymockk = mockk<MesaRepository>()
    private val controller = MesaController(mesaServiceRepositymockk)

}