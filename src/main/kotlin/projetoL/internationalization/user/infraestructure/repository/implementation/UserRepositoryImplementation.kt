package projetoL.internationalization.user.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Repository
import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.entities.Server
import projetoL.internationalization.enterprise.infraestructure.repository.database.EnterpriseDataBase
import projetoL.internationalization.enterprise.infraestructure.repository.database.ServerDataBase
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
        user.hash = BCrypt.gensalt()

        user.password = BCrypt.hashpw(user.password, user.hash)

        return transaction {
            addLogger(StdOutSqlLogger)

            UserDataBase.insert {
                it[uuid] = user.uuid!!
                it[name] = user.name!!
                it[userType] = user.userType!!.uuid!!
                it[isActive] = user.isActive!!
                it[email] = user.email!!
                it[hash] = user.hash!!
                it[authenticationRecord] = user.authenticationRecord!!
                it[enterpriseUUID] = user.enterprise!!.uuid!!
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
            UserDataBase.update({
                UserDataBase.uuid eq user.uuid!! and
                        (UserDataBase.enterpriseUUID eq user.enterprise!!.uuid!!)
            }) {
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
            UserDataBase
                .update({
                    UserDataBase.uuid eq passwordChangeRequest.userUUID and
                            (UserDataBase.enterpriseUUID eq passwordChangeRequest.enterpriseUUID)
                }) {
                    it[password] = passwordChangeRequest.newPassword
                    it[lastPasswordModified] = LocalDateTime.now()
                }
        }
    }

    override fun getByUUID(uuid: UUID, enterpriseUUID: UUID): User? {
        var user: User? = null

        return transaction {

            addLogger(StdOutSqlLogger)
            UserDataBase
                .innerJoin(UserTypeDataBase, { UserTypeDataBase.uuid }, { UserDataBase.userType })
                .innerJoin(EnterpriseDataBase, { EnterpriseDataBase.uuid }, { UserDataBase.enterpriseUUID })
                .innerJoin(ServerDataBase, { ServerDataBase.uuid }, { EnterpriseDataBase.serverUUID })
                .select {
                    UserDataBase.uuid eq uuid and
                            (UserDataBase.enterpriseUUID eq enterpriseUUID)
                }.map {
                    user = User(
                        uuid = it[UserDataBase.uuid],
                        name = it[UserDataBase.name],
                        userType = UserType(
                            uuid = it[UserTypeDataBase.uuid],
                            code = it[UserTypeDataBase.code],
                            label = it[UserTypeDataBase.label]
                        ),
                        enterprise = Enterprise(
                            uuid = it[EnterpriseDataBase.uuid],
                            code = it[EnterpriseDataBase.code],
                            name = it[EnterpriseDataBase.name],
                            server = Server(
                                uuid = it[ServerDataBase.uuid],
                                code = it[ServerDataBase.code],
                                name = it[ServerDataBase.name],
                                ip = it[ServerDataBase.ip],
                                modifiedAt = it[ServerDataBase.modifiedAt],
                                createdAt = it[ServerDataBase.createdAt]
                            ),
                            createdAt = it[EnterpriseDataBase.createdAt],
                            modifiedAt = it[EnterpriseDataBase.modifiedAt]
                        ),
                        hash = it[UserDataBase.hash],
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
                .select {
                    UserDataBase.authenticationRecord eq loginRequest.username and
                            (UserDataBase.password eq loginRequest.password
                                    and (UserDataBase.enterpriseUUID eq loginRequest.enterpriseUUID))
                }
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
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
        enterpriseUUID: UUID
    ): List<User>? {
        val listUser: MutableList<User> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            val actorsQuery =
                UserDataBase
                    .innerJoin(UserTypeDataBase, { UserTypeDataBase.uuid }, { UserDataBase.userType })
                    .innerJoin(EnterpriseDataBase, { EnterpriseDataBase.uuid }, { UserDataBase.enterpriseUUID })
                    .innerJoin(ServerDataBase, { ServerDataBase.uuid }, { EnterpriseDataBase.serverUUID })
                    .select { UserDataBase.enterpriseUUID eq enterpriseUUID }
                    .limit(size, offset = (((page - 1) * size).toLong()))
                    .orderBy(
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
                    enterprise = Enterprise(
                        uuid = it[EnterpriseDataBase.uuid],
                        code = it[EnterpriseDataBase.code],
                        name = it[EnterpriseDataBase.name],
                        server = Server(
                            uuid = it[ServerDataBase.uuid],
                            code = it[ServerDataBase.code],
                            name = it[ServerDataBase.name],
                            ip = it[ServerDataBase.ip],
                            modifiedAt = it[ServerDataBase.modifiedAt],
                            createdAt = it[ServerDataBase.createdAt]
                        ),
                        createdAt = it[EnterpriseDataBase.createdAt],
                        modifiedAt = it[EnterpriseDataBase.modifiedAt]
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

    override fun getCountAllUser(filters: List<BasicFilter>?, enterpriseUUID: UUID): Int {
        return transaction {
            UserDataBase.select { UserDataBase.enterpriseUUID eq enterpriseUUID }.withUserFilters(filters).count()
                .toInt()
        }
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