package projetoL.internationalization.user.domain.repository

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import java.util.UUID

interface UserRepository {

    fun create(user: User) : User

    fun update(user: User) : User

    fun getByUUID(uuid: UUID) : User?

    fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ) : List<User>?

    fun getCountAllUser(
        filters: List<BasicFilter>?
    ) : Int
}