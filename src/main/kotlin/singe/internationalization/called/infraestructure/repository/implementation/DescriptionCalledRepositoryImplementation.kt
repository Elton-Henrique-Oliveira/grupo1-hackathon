package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.repository.DescriptionCalledRepository
import singe.internationalization.called.domain.repository.FlowTypeRepository
import singe.internationalization.called.domain.repository.PriorityRepository
import singe.internationalization.called.domain.repository.ReturnedCalledRepository
import singe.internationalization.called.infraestructure.repository.database.DescriptionCalledDataBase
import singe.internationalization.called.infraestructure.repository.database.DescriptionCalledDataBase.uuid
import java.time.LocalDateTime
import java.util.*

@Repository
class DescriptionCalledRepositoryImplementation(
    val priorityRepository: PriorityRepository,
    val flowTypeRepository: FlowTypeRepository,
    val returnedCalledRepository: ReturnedCalledRepository,
) : DescriptionCalledRepository {

    override fun createDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled {

        val idGeneratedVendor = UUID.randomUUID()

        descriptionCalled.uuid = idGeneratedVendor

        return transaction {
            val returned: DescriptionCalled = transaction {
                addLogger(StdOutSqlLogger)
                DescriptionCalledDataBase.insert {
                    it[uuid] = descriptionCalled.uuid!!
                    it[calledUUID] = descriptionCalled.calledUUID!!
                    it[title] = descriptionCalled.title!!
                    it[description] = descriptionCalled.description!!
                    it[priorityUUID] = descriptionCalled.priority!!.uuid!!
                    it[flowTypeUUID] = descriptionCalled.flowType!!.uuid!!
                }.resultedValues!!
                descriptionCalled
            }

            returned
        }

    }

    override fun updateDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled {
        return transaction {

            descriptionCalled.modifiedAt = LocalDateTime.now()

            val returned: DescriptionCalled = transaction {
                DescriptionCalledDataBase.update({
                    uuid eq descriptionCalled.uuid!!
                }) {
                    it[uuid] = descriptionCalled.uuid!!
                    it[calledUUID] = descriptionCalled.calledUUID!!
                    it[title] = descriptionCalled.title!!
                    it[description] = descriptionCalled.description!!
                    it[priorityUUID] = descriptionCalled.priority!!.uuid!!
                    it[flowTypeUUID] = descriptionCalled.flowType!!.uuid!!
                    it[modifiedAt] = descriptionCalled.modifiedAt!!
                }
                descriptionCalled
            }
            returned
        }
    }

    override fun getDescriptionCalled(): List<DescriptionCalled>? {
        val listDescriptionCalled: MutableList<DescriptionCalled> = mutableListOf()

        transaction {
            DescriptionCalledDataBase.selectAll().map {
                val descriptionCalled = DescriptionCalled(
                    uuid = it[uuid],
                    calledUUID = it[DescriptionCalledDataBase.calledUUID],
                    title = it[DescriptionCalledDataBase.title],
                    description = it[DescriptionCalledDataBase.description],
                    returnedCalled = returnedCalledRepository.getReturnedCalledByDescriptionCalledUUID(it[uuid]),
                    priority = priorityRepository.getPriorityByUUID(it[DescriptionCalledDataBase.priorityUUID]),
                    flowType = flowTypeRepository.getFlowTypeByUUID(it[DescriptionCalledDataBase.flowTypeUUID]),
                )
                listDescriptionCalled.add(descriptionCalled)
            }
        }
        return listDescriptionCalled.toList()
    }

    override fun getDescriptionCalledByUUID(uuid: UUID): DescriptionCalled? {

        var descriptionCalled: DescriptionCalled? = null
        transaction {
            DescriptionCalledDataBase.select(DescriptionCalledDataBase.uuid eq uuid).map {
                descriptionCalled = DescriptionCalled(
                    uuid = it[DescriptionCalledDataBase.uuid],
                    calledUUID = it[DescriptionCalledDataBase.calledUUID],
                    title = it[DescriptionCalledDataBase.title],
                    description = it[DescriptionCalledDataBase.description],
                    returnedCalled = returnedCalledRepository.getReturnedCalledByDescriptionCalledUUID(it[DescriptionCalledDataBase.uuid]),
                    priority = priorityRepository.getPriorityByUUID(it[DescriptionCalledDataBase.priorityUUID]),
                    flowType = flowTypeRepository.getFlowTypeByUUID(it[DescriptionCalledDataBase.flowTypeUUID]),
                )
            }
        }
        return descriptionCalled
    }

    override fun getDescriptionCalledByCalledUUID(calledUUID: UUID): DescriptionCalled? {
        var descriptionCalled: DescriptionCalled? = null

        transaction {
            DescriptionCalledDataBase.select(DescriptionCalledDataBase.calledUUID eq calledUUID).map {
                descriptionCalled = DescriptionCalled(
                    uuid = it[uuid],
                    calledUUID = it[DescriptionCalledDataBase.calledUUID],
                    title = it[DescriptionCalledDataBase.title],
                    description = it[DescriptionCalledDataBase.description],
                    returnedCalled = returnedCalledRepository.getReturnedCalledByDescriptionCalledUUID(it[uuid]),
                    priority = priorityRepository.getPriorityByUUID(it[DescriptionCalledDataBase.priorityUUID]),
                    flowType = flowTypeRepository.getFlowTypeByUUID(it[DescriptionCalledDataBase.flowTypeUUID]),
                )
                descriptionCalled
            }
        }
        return descriptionCalled
    }
}