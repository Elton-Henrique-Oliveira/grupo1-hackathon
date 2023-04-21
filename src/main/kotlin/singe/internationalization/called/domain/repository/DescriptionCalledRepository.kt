package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.DescriptionCalled
import java.util.*

interface DescriptionCalledRepository {

   fun createDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled

   fun updateDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled

   fun getDescriptionCalled(): List<DescriptionCalled>?

   fun getDescriptionCalledByUUID(uuid: UUID): DescriptionCalled?

   fun getDescriptionCalledByDCalledUUID(calledUUID: UUID): List<DescriptionCalled>?
}