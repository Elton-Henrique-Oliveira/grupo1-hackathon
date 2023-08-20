package projetoL.internationalization.user.domain.usecases.implementation

import org.springframework.stereotype.Service
import projetoL.core.shared.utils.Utils
import projetoL.core.shared.webservice.BasicFilter
import projetoL.core.shared.webservice.TotalPages
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.exceptions.*
import projetoL.internationalization.user.domain.repository.UserRepository
import projetoL.internationalization.user.domain.usecases.UserUseCase
import projetoL.internationalization.user.domain.usecases.response.UserListAllResponse
import projetoL.internationalization.user.domain.usecases.response.UserResponse
import java.util.*
import kotlin.math.log

@Service
class UserUseCaseImplementation(
    val userRepository: UserRepository
) : UserUseCase {
    override fun createAndUpdate(user: User): UserResponse? {

        if (user.name == "" || user.name == null) {
            return UserResponse(error = NOME_DO_USUARIO_NAO_INFORMADO)
        }

        if (userRepository.getTypeByUUID(user.userType!!.uuid) == null) {
            return UserResponse(error = TIPO_DE_USUARIO_NAO_EXISTE)
        }

        if (user.isActive == null) {
            return UserResponse(error = IS_ACTIVE_NAO_INFORMADO)
        }

        if (user.email == "" || user.email == null) {
            return UserResponse(error = EMAIL_NAO_INFORMADO)
        }

        if (user.authenticationRecord == "" || user.authenticationRecord == null) {
            return UserResponse(error = AUTHENTICATOR_RECORD_NAO_INFORMADO)
        }

        if (user.password == "" || user.password == null) {
            return UserResponse(error = PASSWORD_NAO_INFORMADO)
        }

        if (user.contact == "" || user.contact == null) {
            return UserResponse(error = CONTATO_NAO_INFORMADO)
        }

        if (user.uuid == null) {
            return UserResponse(userRepository.create(user))
        }

        if (userRepository.getByUUID(user.uuid!!) == null) {
            return UserResponse(error = USUARIO_NAO_EXISTE)
        }

        return UserResponse(userRepository.update(user))
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
            UserListAllResponse(error = UM_ERRO_OCORREU_NO_USUARIO)
        }
    }

    override fun getUserByUUID(uuid: UUID): User? {
        return userRepository.getByUUID(uuid)
    }

    override fun requestLogin(loginRequest: LoginRequest): UserResponse? {

        if (loginRequest.password == "") {
            return UserResponse(error = PASSWORD_NAO_INFORMADO)
        }

        if (loginRequest.username == "") {
            return UserResponse(error = USUARIO_NAO_INFORMADO)
        }

        val loginUser: User = userRepository.getByLoginRequest(loginRequest)
            ?: return UserResponse(error = USUARIO_E_SENHA_INVALIDOS)

        return UserResponse(user = loginUser)
    }

    override fun passwordChangeRequest(passwordChangeRequest: PasswordChangeRequest): UserResponse? {
        println(passwordChangeRequest.currentPassword)
        val user: User = userRepository.getByUUID(passwordChangeRequest.userUUID)
            ?: return UserResponse(error = USUARIO_NAO_EXISTE)

        if(user.password != passwordChangeRequest.currentPassword){
            return UserResponse(error = SENHA_ANTIGA_NAO_CONFERE)
        }

        if(passwordChangeRequest.currentPassword == ""){
            return UserResponse(error = SENHA_ANTIGA_NAO_INFORMADA)
        }

        if(passwordChangeRequest.newPassword == ""){
            return UserResponse(error = SENHA_NOVA_NAO_INFORMADA)
        }

        if(passwordChangeRequest.newPasswordConfirmation == ""){
            return UserResponse(error = SENHA_DE_CONFIRMACAO_NAO_INFORMADA)
        }

        if(passwordChangeRequest.newPassword != passwordChangeRequest.newPasswordConfirmation){
            return UserResponse(error = SENHAS_NAO_CONFERE)
        }

        if(passwordChangeRequest.currentPassword == passwordChangeRequest.newPassword){
            return UserResponse(error = SENHA_ANTIGA_IGUAL_ATUAL)
        }

        userRepository.updatePassword(passwordChangeRequest)

        return UserResponse(user = userRepository.getByUUID(passwordChangeRequest.userUUID))
    }
}