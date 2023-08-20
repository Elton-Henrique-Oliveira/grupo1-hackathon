package projetoL.internationalization.user.domain.usecases.implementation

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.UserUseCase
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserUseCaseImplementation (

) : UserUseCase{
    override fun createAndUpdate(user: User): UserResponse? {
        TODO("Not yet implemented")
    }

    override fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ): UserListAllResponse? {
        TODO("Not yet implemented")
    }

    override fun getUserByUUID(uuid: UUID): User? {
        TODO("Not yet implemented")
    }


}