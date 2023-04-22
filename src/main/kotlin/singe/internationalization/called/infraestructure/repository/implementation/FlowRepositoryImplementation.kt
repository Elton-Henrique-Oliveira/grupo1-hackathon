package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.repository.FlowRepository
import singe.internationalization.called.domain.repository.FlowTypeRepository
import singe.internationalization.called.infraestructure.repository.database.FlowDataBase
import java.util.*

@Repository
class FlowRepositoryImplementation(
    var flowTypeRepository: FlowTypeRepository,
) : FlowRepository {

    override fun getFlow(): List<Flow> {

        val listFlow: MutableList<Flow> = mutableListOf()

        transaction {
            FlowDataBase.selectAll().map {
                val flow = Flow(
                    uuid = it[FlowDataBase.uuid],
                    label = it[FlowDataBase.label],
                    statusCode = it[FlowDataBase.statusCode],
                    flowType = flowTypeRepository.getFlowTypeByFlowUUID(it[FlowDataBase.uuid]),
                    isInfrastructure = it[FlowDataBase.isInfrastructure],
                    needValidating = it[FlowDataBase.needValidating],
                    createdAt = it[FlowDataBase.createdAt],
                    modifiedAt = it[FlowDataBase.modifiedAt],
                )
                listFlow.add(flow)
            }
        }
        return listFlow.toList()
    }

    override fun getFlowByUUID(flowUUID: UUID): Flow? {
        var flow: Flow? = null
        transaction {
            FlowDataBase.select(FlowDataBase.uuid eq flowUUID).map {
                flow = Flow(
                    uuid = it[FlowDataBase.uuid],
                    label = it[FlowDataBase.label],
                    statusCode = it[FlowDataBase.statusCode],
                    isInfrastructure = it[FlowDataBase.isInfrastructure],
                    needValidating = it[FlowDataBase.needValidating],
                    createdAt = it[FlowDataBase.createdAt],
                    modifiedAt = it[FlowDataBase.modifiedAt],
                )
            }
        }
        return flow
    }
}