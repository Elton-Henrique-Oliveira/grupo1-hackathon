package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.StdOutSqlLogger
import org.jetbrains.exposed.sql.addLogger
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.CalledStatus
import singe.internationalization.called.domain.repository.CalledStatusRepository
import singe.internationalization.called.infraestructure.repository.database.CalledStatusDataBase

@Repository
class CalledStatusRepositoryImplementation : CalledStatusRepository {

    override fun getCalledStatus(): List<CalledStatus> {

        val listCalledStatus: MutableList<CalledStatus> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            CalledStatusDataBase.selectAll().map {
                val calledStatus = CalledStatus(
                    uuid = it[CalledStatusDataBase.uuid],
                    label = it[CalledStatusDataBase.label],
                    statusCode = it[CalledStatusDataBase.statusCode],
                    createdAt = it[CalledStatusDataBase.createdAt],
                    modifiedAt = it[CalledStatusDataBase.modifiedAt],
                )
                listCalledStatus.add(calledStatus)
            }
        }
        return listCalledStatus.toList()
    }
}