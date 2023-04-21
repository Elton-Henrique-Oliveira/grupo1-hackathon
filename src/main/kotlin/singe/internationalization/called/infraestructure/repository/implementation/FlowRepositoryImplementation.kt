package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.repository.FlowRepository
import singe.internationalization.called.infraestructure.repository.database.FlowDataBase

@Repository
class FlowRepositoryImplementation : FlowRepository {

    override fun getFlow(): List<Flow> {

        val listFlow: MutableList<Flow> = mutableListOf()

        transaction {
            FlowDataBase.selectAll().map {
                val flow = Flow(
                        uuid = it[FlowDataBase.uuid],
                        label = it[FlowDataBase.label],
                        statusCode = it[FlowDataBase.statusCode],
                        isInfrastructure = it[FlowDataBase.isInfrastructure],
                        createdAt = it[FlowDataBase.createdAt],
                        modifiedAt = it[FlowDataBase.modifiedAt],
                )
                listFlow.add(flow)
            }
        }
        return listFlow.toList()
    }
}