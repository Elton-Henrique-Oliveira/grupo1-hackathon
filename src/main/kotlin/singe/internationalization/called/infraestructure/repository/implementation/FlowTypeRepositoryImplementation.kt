package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.entities.FlowType
import singe.internationalization.called.domain.repository.FlowRepository
import singe.internationalization.called.domain.repository.FlowTypeRepository
import singe.internationalization.called.infraestructure.repository.database.FlowDataBase
import singe.internationalization.called.infraestructure.repository.database.FlowTypeDataBase

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
                    createdAt = it[FlowTypeDataBase.createAt],
                    modifiedAt = it[FlowTypeDataBase.modifiedAt],
                )
                listFlowType.add(flowType)
            }
        }
        return listFlowType.toList()
    }
}