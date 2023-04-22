package singe.internationalization.called.domain.usecases.implementation

import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.exceptions.DESCRIPTION_CALLED_CREATE_ERROR
import singe.internationalization.called.domain.repository.DescriptionCalledRepository
import singe.internationalization.called.domain.usecases.DescriptionCalledUseCase
import singe.internationalization.called.domain.usecases.response.DescriptionCalledResponse
import java.util.*

@Service
class DescriptionCalledUseCaseImplementation(
    val repository: DescriptionCalledRepository,
) : DescriptionCalledUseCase {
    override fun createAndUpdate(descriptionCalled: DescriptionCalled): DescriptionCalledResponse? {
        return try {
            if (descriptionCalled.uuid == null) {
                return DescriptionCalledResponse(descriptionCalled = repository.createDescriptionCalled(descriptionCalled))
            }

            DescriptionCalledResponse(descriptionCalled = repository.updateDescriptionCalled(descriptionCalled))
        } catch (e: Exception) {
            DescriptionCalledResponse(error = DESCRIPTION_CALLED_CREATE_ERROR)
        }
    }

    override fun getDescriptionCalled(): List<DescriptionCalled>? {
        return repository.getDescriptionCalled()
    }

    override fun getDescriptionCalledByUUID(uuid: UUID): DescriptionCalled? {
        return repository.getDescriptionCalledByUUID(uuid)
    }
}