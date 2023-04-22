package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.entities.FlowType
import singe.internationalization.called.domain.repository.FlowRepository
import singe.internationalization.called.domain.repository.FlowTypeRepository
import singe.internationalization.called.infraestructure.repository.database.FlowDataBase
import singe.internationalization.called.infraestructure.repository.database.FlowTypeDataBase
import java.util.*

@Repository
class FlowTypeRepositoryImplementation : FlowTypeRepository {

    override fun getFlowType(): List<FlowType> {

        val listFlowType: MutableList<FlowType> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            FlowTypeDataBase.selectAll().map {
                val flowType = FlowType(
                    uuid = it[FlowTypeDataBase.uuid],
                    label = it[FlowTypeDataBase.label],
                    statusCode = it[FlowTypeDataBase.statusCode],
                    flowUUID = it[FlowTypeDataBase.flowUUID],
                    createdAt = it[FlowTypeDataBase.createdAt],
                    modifiedAt = it[FlowTypeDataBase.modifiedAt],
                )
                listFlowType.add(flowType)
            }
        }
        return listFlowType.toList()
    }
    override fun getFlowTypeByFlowUUID(flowUUID: UUID): List<FlowType> {

        val listFlowType: MutableList<FlowType> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            FlowTypeDataBase.select(
                FlowTypeDataBase.flowUUID eq flowUUID
            ).map {
                val flowType = FlowType(
                    uuid = it[FlowTypeDataBase.uuid],
                    label = it[FlowTypeDataBase.label],
                    statusCode = it[FlowTypeDataBase.statusCode],
                    flowUUID = it[FlowTypeDataBase.flowUUID],
                    createdAt = it[FlowTypeDataBase.createdAt],
                    modifiedAt = it[FlowTypeDataBase.modifiedAt],
                )
                listFlowType.add(flowType)
            }
        }
        return listFlowType.toList()
    }

}