package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.usecases.response.DescriptionCalledResponse
import java.util.*

interface DescriptionCalledUseCase {

    fun createAndUpdate(descriptionCalled: DescriptionCalled): DescriptionCalledResponse?

    fun getDescriptionCalled(): List<DescriptionCalled>?

    fun getDescriptionCalledByUUID(uuid: UUID): DescriptionCalled?
}