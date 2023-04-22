package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Priority
import singe.internationalization.called.domain.repository.PriorityRepository
import singe.internationalization.called.infraestructure.repository.database.PriorityDataBase
import java.util.*

@Repository
class PriorityRepositoryImplementation : PriorityRepository {
    override fun getPriority(): List<Priority> {
        val listPriority: MutableList<Priority> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            PriorityDataBase.selectAll().map {
                val priority = Priority(
                    uuid = it[PriorityDataBase.uuid],
                    label = it[PriorityDataBase.label],
                    statusCode = it[PriorityDataBase.statusCode],
                    createdAt = it[PriorityDataBase.createdAt],
                    modifiedAt = it[PriorityDataBase.modifiedAt],
                )
                listPriority.add(priority)
            }
        }
        return listPriority.toList()
    }

    override fun getPriorityByUUID(priorityUUID: UUID): Priority? {
        var priority: Priority? = null

        transaction {
            addLogger(StdOutSqlLogger)
            PriorityDataBase.select(
                PriorityDataBase.uuid eq priorityUUID
            ).map {
                priority = Priority(
                    uuid = it[PriorityDataBase.uuid],
                    label = it[PriorityDataBase.label],
                    statusCode = it[PriorityDataBase.statusCode],
                    createdAt = it[PriorityDataBase.createdAt],
                    modifiedAt = it[PriorityDataBase.modifiedAt],
                )
                priority
            }
        }
        return priority
    }
}