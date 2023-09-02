package projetoL.internationalization.user.infraestructure.webservice.implementation

import org.mindrot.jbcrypt.BCrypt
import org.springframework.web.bind.annotation.*
import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.UserUseCase
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import projetoL.internationalization.user.infraestructure.webservice.UserService
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
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

    @PostMapping
    override fun createAndUpdate(
        @RequestBody user: User
    ): UserResponse? {
        return userUseCase.createAndUpdate(user)
    }

    @GetMapping("/{enterpriseUUID}")
    override fun getAllUser(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?,
        @PathVariable("enterpriseUUID") enterpriseUUID: UUID
    ): UserListAllResponse? {
        return userUseCase.getAllUser(
            page = page,
            size = size,
            orderBy = orderBy,
            sortBy = sortBy,
            filters = filter,
            enterpriseUUID
        )
    }
    @GetMapping("/{uuid}/{enterpriseUUID}")
    override fun getUserByUUID(
        @PathVariable("uuid") uuid: UUID,
        @PathVariable("enterpriseUUID") enterpriseUUID: UUID
    ): User? {
        return userUseCase.getUserByUUID(uuid, enterpriseUUID)
    }

    @PostMapping("/login")
    override fun requestLogin(
        @RequestBody loginRequest: LoginRequest
    ) : UserResponse? {
        return userUseCase.requestLogin(loginRequest)
    }

    @PostMapping("/alterPassword")
    override fun passwordChangeRequest(
        @RequestBody passwordChangeRequest: PasswordChangeRequest
    ) : UserResponse? {
        return userUseCase.passwordChangeRequest(passwordChangeRequest)
    }
}