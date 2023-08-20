package projetoL.internationalization.user.domain.usecases.implementation

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.UserUseCase
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import org.springframework.stereotype.Service
import projetoL.core.shared.utils.Utils
import projetoL.core.shared.webservice.TotalPages
import projetoL.internationalization.user.domain.repository.UserRepository
import java.util.*

@Service
class UserUseCaseImplementation(
    val userRepository: UserRepository
) : UserUseCase {
    override fun createAndUpdate(user: User): UserResponse? {
        return if (user.uuid == null) {
            UserResponse(userRepository.create(user))
        } else {
            UserResponse(userRepository.update(user))
        }
    }

    override fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ): UserListAllResponse? {
        return try {
            val totalPages: TotalPages? = Utils.calculateTotalPages(userRepository.getCountAllUser(filters), size)

            UserListAllResponse(
                user = userRepository.getAllUser(page, size, orderBy, sortBy, filters),
                page = page,
                size = size,
                numberPages = totalPages!!.totalPages,
                error = null
            )
        } catch (e: Exception) {
            UserListAllResponse(error = null)
        }
    }

    override fun getUserByUUID(uuid: UUID): User? {
        return userRepository.getByUUID(uuid)
    }


}