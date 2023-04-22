package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.SituationCalled
import singe.internationalization.called.domain.repository.SituationCalledRepository
import singe.internationalization.called.infraestructure.repository.database.SituationCalledDataBase
import java.util.UUID

@Repository
class SituationCalledRepositoryImplementation : SituationCalledRepository {

    override fun getSituationCalled(): List<SituationCalled> {

        val listSituationCalled: MutableList<SituationCalled> = mutableListOf()

        transaction {
            SituationCalledDataBase.selectAll().map {
                val situationCalled = SituationCalled(
                        uuid = it[SituationCalledDataBase.uuid],
                        label = it[SituationCalledDataBase.label],
                        statusCode = it[SituationCalledDataBase.statusCode],
                        createdAt = it[SituationCalledDataBase.createdAt],
                        modifiedAt = it[SituationCalledDataBase.modifiedAt],
                )
                listSituationCalled.add(situationCalled)
            }
        }
        return listSituationCalled.toList()
    }

    override fun getSituationCalledByUUID(situationUUID: UUID): SituationCalled? {

        var situationCalled: SituationCalled? = null

        transaction {
            SituationCalledDataBase.select(SituationCalledDataBase.uuid eq situationUUID).map {
                situationCalled = SituationCalled(
                    uuid = it[SituationCalledDataBase.uuid],
                    label = it[SituationCalledDataBase.label],
                    statusCode = it[SituationCalledDataBase.statusCode],
                    createdAt = it[SituationCalledDataBase.createdAt],
                    modifiedAt = it[SituationCalledDataBase.modifiedAt],
                )
                situationCalled
            }
        }
        return situationCalled
    }

}
