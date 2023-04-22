package singe.internationalization.called.infraestructure.webservice

import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.usecases.response.DescriptionCalledResponse
import java.util.*

interface DescriptionCalledService {

    fun createAndUpdate(
        @RequestBody descriptionCalled: DescriptionCalled
    ): DescriptionCalledResponse?

    fun getDescriptionCalled(): List<DescriptionCalled>?

    fun getDescriptionCalledByUUID(
        @PathVariable("uuid") uuid: UUID
    ): DescriptionCalled?
}