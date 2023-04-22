package singe.internationalization.called.domain.usecases.implementation

import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Service
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.exceptions.*
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.domain.usecases.CalledUseCase
import singe.internationalization.called.domain.usecases.response.CalledListAllResponse
import singe.internationalization.called.domain.usecases.response.CalledResponse
import singe.internationalization.called.infraestructure.repository.database.CalledDataBase
import java.util.*

@Service
class CalledUseCaseImplementation(
    val repository: CalledRepository,
) : CalledUseCase {
    override fun createAndUpdate(called: Called): CalledResponse {
        return try {
            if (called.uuid == null) {
                return CalledResponse(called = repository.createCalled(called))
            }
            if (repository.getCalledByUUID(called.uuid!!) == null) {
                return CalledResponse(error = CALL_DOES_NOT_EXIST)
            }
            CalledResponse(called = repository.updateCalled(called))
        } catch (e: Exception) {
            CalledResponse(error = CALLED_CREATE_ERROR)
        }
    }

    override fun getCalled(): List<Called>? {
        return repository.getCalled()
    }

    override fun updateCalledSituation(calledUUID: UUID, situationUUID: UUID): Boolean? {
        if (repository.getCalledByUUID(calledUUID) == null) {
            return false
        }

        return repository.updateCalledSituation(calledUUID, situationUUID)
    }

    override fun getCalledByUUID(calledUUID: UUID): Called? {
        return repository.getCalledByUUID(calledUUID)
    }

    override fun listAllCalled(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
    ): CalledListAllResponse {
        return try {
            val numberOfRegister: Int = repository.getCountAllCalled(filters)
            var numberPages: Int = 0

            if (numberOfRegister != 0) {
                numberPages = numberOfRegister / size

                if (numberOfRegister % size != 0) {
                    numberPages += 1
                }
            }
            CalledListAllResponse(
                called = repository.listAllCalled(page, size, orderBy, sortBy, filters),
                page = page,
                size = size,
                numberPages = numberPages,
                error = null
            )
        } catch (e: Exception) {
            CalledListAllResponse(error = CALLED_ERROR)
        }
    }
}

//.withVendorFilters(filters).withDistinct(true).count().toInt()
