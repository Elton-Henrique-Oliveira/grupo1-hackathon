package projetoL.internationalization.user.infraestructure.repository.implementation

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.repository.UserRepository
import projetoL.internationalization.user.infraestructure.repository.database.UserDataBase
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class UserRepositoryImplementation : UserRepository {
    override fun create(user: User): User {
        user.uuid = UUID.randomUUID()

        return transaction {
            addLogger(StdOutSqlLogger)

            UserDataBase.insert {
                it[uuid] = user.uuid!!
                it[name] = user.name!!
                it[userType] = user.userType!!.uuid!!
                it[isActive] = user.isActive!!
                it[email] = user.email!!
                it[authenticationRecord] = user.authenticationRecord!!
                it[password] = user.password!!
                it[updatePassword] = false
                it[contact] = user.contact!!
            }.resultedValues

            user
        }

    }

    override fun update(user: User): User {
        user.modifiedAt = LocalDateTime.now()

        return transaction {
            addLogger(StdOutSqlLogger)
            UserDataBase.update({ UserDataBase.uuid eq user.uuid!! }) {
                it[name] = user.name!!
                it[userType] = user.userType!!.uuid!!
                it[isActive] = user.isActive!!
                it[email] = user.email!!
                it[authenticationRecord] = user.authenticationRecord!!
                it[password] = user.password!!
                it[updatePassword] = false
                it[contact] = user.contact!!
                it[modifiedAt] = user.modifiedAt!!
            }

            user
        }

    }

    override fun getByUUID(uuid: UUID): User? {
        TODO("Not yet implemented")
    }

    override fun getAllUser(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ): List<User>? {
        TODO("Not yet implemented")
    }

    override fun getCountAllUser(filters: List<BasicFilter>?): Int {
        TODO("Not yet implemented")
    }

}