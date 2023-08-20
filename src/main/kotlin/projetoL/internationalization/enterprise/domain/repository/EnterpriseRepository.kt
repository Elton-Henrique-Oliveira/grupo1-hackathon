package projetoL.internationalization.enterprise.domain.repository

import projetoL.core.shared.webservice.BasicFilter
import projetoL.internationalization.enterprise.domain.entities.Enterprise
import projetoL.internationalization.enterprise.domain.entities.Server
import java.util.*

interface EnterpriseRepository {
    fun create(enterprise: Enterprise) : Enterprise

    fun update(enterprise: Enterprise) : Enterprise

    fun getByUUID(uuid: UUID) : Enterprise?

    fun getAllEnterprise(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?
    ) : List<Enterprise>?

    fun getCountAllEnterprise(
        filters: List<BasicFilter>?
    ) : Int
    fun createServer(server: Server) : Server

    fun updateServer(server: Server) : Server

    fun getServerByUUID(uuid: UUID) : Server?

}