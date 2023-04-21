package singe.internationalization.called.infraestructure.webservice

import org.springframework.web.bind.annotation.RequestBody
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.usecases.response.CalledResponse

interface CalledService {
    fun createAndUpdate(@RequestBody called: Called): CalledResponse?

    fun getCalled(): List<Called>?
}