package singe.internationalization.called.domain.repository

import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.DescriptionCalled
import java.util.*

@Repository
interface DescriptionCalledRepository {

   fun createDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled

   fun updateDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled

   fun getDescriptionCalled(): List<DescriptionCalled>?

   fun getDescriptionCalledByUUID(uuid: UUID): DescriptionCalled?

   fun getDescriptionCalledByCalledUUID(calledUUID: UUID): DescriptionCalled?
}