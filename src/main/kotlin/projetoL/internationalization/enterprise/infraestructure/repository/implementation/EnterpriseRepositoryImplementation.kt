package projetoL.internationalization.enterprise.infraestructure.repository.implementation

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.entities.Server
import projetoL.internationalization.enterprise.domain.repository.EnterpriseRepository
import projetoL.internationalization.enterprise.infraestructure.repository.database.EnterpriseDataBase
import projetoL.internationalization.enterprise.infraestructure.repository.database.ServerDataBase
import projetoL.internationalization.enterprise.infraestructure.repository.database.withEnterpriseFilters
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
class EnterpriseRepositoryImplementation : EnterpriseRepository {
    override fun create(enterprise: Enterprise): Enterprise {
        enterprise.uuid = UUID.randomUUID()

        return transaction {
            addLogger(StdOutSqlLogger)
            EnterpriseDataBase.insert {
                it[uuid] = enterprise.uuid!!
                it[name] = enterprise.name!!
                it[serverUUID] = enterprise.server!!.uuid!!
            }.resultedValues
            enterprise
        }
    }

    override fun update(enterprise: Enterprise): Enterprise {
        enterprise.modifiedAt = LocalDateTime.now()

        return transaction {
            addLogger(StdOutSqlLogger)
            EnterpriseDataBase.update({
                EnterpriseDataBase.uuid eq enterprise.uuid!!
            }) {
                it[name] = enterprise.name!!
                it[serverUUID] = enterprise.server!!.uuid!!
                it[modifiedAt] = enterprise.modifiedAt!!
            }
            enterprise
        }
    }

    override fun getByUUID(uuid: UUID): Enterprise? {

        var enterprise: Enterprise? = null

        return transaction {
            addLogger(StdOutSqlLogger)

            (EnterpriseDataBase innerJoin ServerDataBase)
                .select { EnterpriseDataBase.uuid eq uuid }
                .map {
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
                    )
                }
            enterprise
        }
    }


    override fun getAllEnterprise(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ): List<Enterprise>? {
        val listEnterprise: MutableList<Enterprise> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            val actorsQuery = EnterpriseDataBase
                .innerJoin(ServerDataBase, { ServerDataBase.uuid }, { EnterpriseDataBase.serverUUID })
                .selectAll()
                .limit(size, offset = (((page - 1) * size).toLong()))
                .orderBy(
                    when (sortBy) {
                        "desc" -> when (orderBy) {
                            "name" -> EnterpriseDataBase.name to SortOrder.DESC
                            else -> EnterpriseDataBase.code to SortOrder.DESC
                        }

                        "asc" -> when (orderBy) {
                            "name" -> EnterpriseDataBase.name to SortOrder.ASC
                            else -> EnterpriseDataBase.code to SortOrder.ASC
                        }

                        else -> error("VALUE NOT FOUND")
                    }
                )
            actorsQuery.withEnterpriseFilters(filters).withDistinct(true).map {
                val enterprise = Enterprise(
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
                )
                listEnterprise.add(enterprise)
            }
        }
        return listEnterprise.toList()
    }

    override fun getCountAllEnterprise(filters: List<BasicFilter>?): Int {
        return transaction {
            EnterpriseDataBase.selectAll().withEnterpriseFilters(filters).count().toInt()
        }
    }

    override fun createServer(server: Server): Server {
        server.uuid = UUID.randomUUID()

        return transaction {
            addLogger(StdOutSqlLogger)
            ServerDataBase.insert {
                it[uuid] = server.uuid!!
                it[name] = server.name!!
                it[ip] = server.ip!!
            }.resultedValues
            server
        }
    }

    override fun updateServer(server: Server): Server {
        server.modifiedAt = LocalDateTime.now()

        return transaction {
            addLogger(StdOutSqlLogger)
            ServerDataBase.update({
                ServerDataBase.uuid eq server.uuid!!
            }) {
                it[name] = server.name!!
                it[ip] = server.ip!!
                it[modifiedAt] = server.modifiedAt!!
            }
            server
        }
    }

    override fun getServerByUUID(uuid: UUID): Server? {
        var server: Server? = null

        transaction {
            addLogger(StdOutSqlLogger)

            ServerDataBase.select(ServerDataBase.uuid eq uuid).map {
                server = Server(
                    uuid = it[ServerDataBase.uuid],
                    code = it[ServerDataBase.code],
                    name = it[ServerDataBase.name],
                    ip = it[ServerDataBase.ip],
                    createdAt = it[ServerDataBase.createdAt],
                    modifiedAt = it[ServerDataBase.modifiedAt]
                )
                server
            }
        }

        return server
    }
}