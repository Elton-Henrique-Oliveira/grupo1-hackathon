package projetoL.internationalization.user.infraestructure.webservice.implementation

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.UserUseCase
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import projetoL.internationalization.user.infraestructure.webservice.UserService
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*


@RestController
@CrossOrigin(
    origins = ["http://10.0.0.149:3000"],
    allowCredentials = "true"
)
@RequestMapping("/user")
class UserServiceImplementation (
    val userUseCase: UserUseCase
) : UserService{
    override fun createAndUpdate(user: User): UserResponse? {
        return userUseCase.createAndUpdate(user)
    }

    override fun getAllUser(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
    ): UserListAllResponse? {
        return userUseCase.getAllUser(
            page = page,
            size = size,
            orderBy = orderBy,
            sortBy = sortBy,
            filters = filter
        )
    }

    override fun getUserByUUID(uuid: UUID): User? {
        return userUseCase.getUserByUUID(uuid)
    }
}