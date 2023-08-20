package projetoL.internationalization.user.infraestructure.webservice

import org.springframework.web.bind.annotation.PathVariable
import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
import java.util.UUID

interface UserService {

    fun createAndUpdate(
        @RequestBody user : User
    ) : UserResponse?

    fun getAllUser(
        @RequestParam("page", required = false, defaultValue = "1") page: Int,
        @RequestParam("size", required = false, defaultValue = "30") size: Int,
        @RequestParam("orderBy", required = false, defaultValue = "") orderBy: String,
        @RequestParam("sortBy", required = false, defaultValue = "asc") sortBy: String,
        @RequestParam("filter", required = false) filter: List<BasicFilter>?
    ) : UserListAllResponse?

    fun getUserByUUID(
        @PathVariable("uuid") uuid: UUID
    ) : User?

    fun requestLogin(
        @RequestBody loginRequest: LoginRequest
    ) : UserResponse?

    fun passwordChangeRequest(
        @RequestBody passwordChangeRequest: PasswordChangeRequest
    ) : UserResponse?
}