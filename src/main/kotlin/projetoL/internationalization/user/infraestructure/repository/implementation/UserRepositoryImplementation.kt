package projetoL.internationalization.user.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.user.domain.entities.LoginRequest
import projetoL.internationalization.user.domain.entities.PasswordChangeRequest
import projetoL.internationalization.user.domain.entities.User
import projetoL.internationalization.user.domain.entities.UserType
import projetoL.internationalization.user.domain.repository.UserRepository
import projetoL.internationalization.user.infraestructure.repository.database.UserDataBase
import projetoL.internationalization.user.infraestructure.repository.database.UserTypeDataBase
import projetoL.internationalization.user.infraestructure.repository.database.withUserFilters
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

    override fun updatePassword(passwordChangeRequest: PasswordChangeRequest) {
        return transaction {
            addLogger(StdOutSqlLogger)
            UserDataBase.update({ UserDataBase.uuid eq passwordChangeRequest.userUUID }) {
                it[password] = passwordChangeRequest.newPassword
                it[lastPasswordModified] = LocalDateTime.now()
            }
        }
    }

    override fun getByUUID(uuid: UUID): User? {
        var user: User? = null

        return transaction {

            addLogger(StdOutSqlLogger)
            UserDataBase
                .innerJoin(UserTypeDataBase, { UserTypeDataBase.uuid }, { UserDataBase.userType })
                .select { UserDataBase.uuid eq uuid }.map {
                    user = User(
                        uuid = it[UserDataBase.uuid],
                        name = it[UserDataBase.name],
                        userType = UserType(
                            uuid = it[UserTypeDataBase.uuid],
                            code = it[UserTypeDataBase.code],
                            label = it[UserTypeDataBase.label]
                        ),
                        isActive = it[UserDataBase.isActive],
                        email = it[UserDataBase.email],
                        authenticationRecord = it[UserDataBase.authenticationRecord],
                        password = it[UserDataBase.password],
                        contact = it[UserDataBase.contact],
                        modifiedAt = it[UserDataBase.modifiedAt],
                        createdAt = it[UserDataBase.createAt]
                    )
                }
            user
        }
    }

    override fun getByLoginRequest(loginRequest: LoginRequest): User? {
        var user: User? = null

        return transaction {

            addLogger(StdOutSqlLogger)
            UserDataBase
                .innerJoin(UserTypeDataBase, { UserTypeDataBase.uuid }, { UserDataBase.userType })
                .select { UserDataBase.authenticationRecord eq loginRequest.username and (UserDataBase.password eq loginRequest.password) }
                .map {
                    user = User(
                        uuid = it[UserDataBase.uuid],
                        name = it[UserDataBase.name],
                        userType = UserType(
                            uuid = it[UserTypeDataBase.uuid],
                            code = it[UserTypeDataBase.code],
                            label = it[UserTypeDataBase.label]
                        ),
                        isActive = it[UserDataBase.isActive],
                        email = it[UserDataBase.email],
                        authenticationRecord = it[UserDataBase.authenticationRecord],
                        password = it[UserDataBase.password],
                        contact = it[UserDataBase.contact],
                        modifiedAt = it[UserDataBase.modifiedAt],
                        createdAt = it[UserDataBase.createAt]
                    )
                }
            user
        }
    }

    override fun getAllUser(
        page: Int, size: Int, orderBy: String, sortBy: String, filters: List<BasicFilter>?
    ): List<User>? {
        val listUser: MutableList<User> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            val actorsQuery =
                UserDataBase.innerJoin(UserTypeDataBase, { UserTypeDataBase.uuid }, { UserDataBase.userType })
                    .selectAll().orderBy(
                        when (sortBy) {
                            "desc" -> when (orderBy) {
                                "name" -> UserDataBase.name to SortOrder.DESC
                                else -> UserDataBase.name to SortOrder.DESC
                            }

                            "asc" -> when (orderBy) {
                                "name" -> UserDataBase.name to SortOrder.ASC
                                else -> UserDataBase.name to SortOrder.ASC
                            }

                            else -> error("VALUE NOT FOUND")
                        }
                    )
            actorsQuery.withUserFilters(filters).withDistinct(true).map {
                val user = User(
                    uuid = it[UserDataBase.uuid],
                    name = it[UserDataBase.name],
                    userType = UserType(
                        uuid = it[UserTypeDataBase.uuid],
                        code = it[UserTypeDataBase.code],
                        label = it[UserTypeDataBase.label]
                    ),
                    isActive = it[UserDataBase.isActive],
                    email = it[UserDataBase.email],
                    authenticationRecord = it[UserDataBase.authenticationRecord],
                    password = it[UserDataBase.password],
                    contact = it[UserDataBase.contact],
                    modifiedAt = it[UserDataBase.modifiedAt],
                    createdAt = it[UserDataBase.createAt]
                )

                listUser.add(user)
            }
        }

        return listUser.toList()
    }

    override fun getCountAllUser(filters: List<BasicFilter>?): Int {
        return UserDataBase.selectAll().withUserFilters(filters).count().toInt()
    }

    override fun getTypeByUUID(uuid: UUID): UserType? {
        var userType: UserType? = null

        return transaction {

            addLogger(StdOutSqlLogger)
            UserTypeDataBase.select { UserTypeDataBase.uuid eq uuid }.map {
                userType = UserType(
                    uuid = it[UserTypeDataBase.uuid],
                    code = it[UserTypeDataBase.code],
                    label = it[UserTypeDataBase.label]
                )
            }
            userType
        }
    }

}