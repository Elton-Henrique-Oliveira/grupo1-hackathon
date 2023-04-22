package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.ReturnFlow
import singe.internationalization.called.domain.repository.ReturnFlowRepository
import singe.internationalization.called.domain.repository.DescriptionCalledRepository
import singe.internationalization.called.domain.repository.SituationCalledRepository
import singe.internationalization.called.infraestructure.repository.database.CalledDataBase
import singe.internationalization.called.infraestructure.repository.database.ReturnFlowDataBase
import java.time.LocalDateTime
import java.util.*

@Repository
class ReturnFlowRepositoryImplementation(
        var descriptionCalledRepository: DescriptionCalledRepository,
        var returnFlowRepository: ReturnFlowRepository,
        var situationCalledRepository: SituationCalledRepository,
) : ReturnFlowRepository {

    override fun createReturnFlow(returnFlow: ReturnFlow): ReturnFlow {

        val idGeneratedVendor = UUID.randomUUID()
        returnFlow.uuid = idGeneratedVendor

        println()
        val returned: returnFlow = transaction {
            addLogger(StdOutSqlLogger)

            ReturnFlowDataBase.insert {
                it[uuid] = returnFlow.uuid!!
                it[userName] = returnFlow.userName!!
                it[calledUUID] = returnFlow.called!!.uuid!!
                it[description] = returnFlow.description!!
                it[presentFlowUUID] = returnFlow.presentFlow!!.uuid!!
                it[previousFlowUUID] = returnFlow.previousFlow!!.uuid!!
            }.resultedValues
            returnFlow
        }
        return returned
    }

    override fun updateReturnFlow(returnFlow: ReturnFlow): ReturnFlow {
        return transaction {

            addLogger(StdOutSqlLogger)

            returnFlow.modifiedAt = LocalDateTime.now()

            val returned: ReturnFlow = transaction {
                ReturnFlowDataBase.update({
                    ReturnFlowDataBase.uuid eq returnFlow.uuid!!
                }) {
                    it[uuid] = returnFlow.uuid!!
                    it[userName] = returnFlow.userName!!
                    it[calledUUID] = returnFlow.called!!.uuid!!
                    it[description] = returnFlow.description!!
                    it[presentFlowUUID] = returnFlow.presentFlow!!.uuid!!
                    it[previousFlowUUID] = returnFlow.previousFlow!!.uuid!!
                    it[modifiedAt] = returnFlow.modifiedAt!!
                }
                returnFlow
            }
            returned
        }
    }

    override fun getReturnFlow(): List<ReturnFlow> {

        val listReturnFlow: MutableList<ReturnFlow> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            ReturnFlowDataBase.selectAll().map {
                val returnFlow = ReturnFlow(
                        uuid = it[ReturnFlowDataBase.uuid],
                        userName = it[ReturnFlowDataBase.userName],
                        presentFlow = it[ReturnFlowDataBase.presentFlow]
                        description = it[ReturnFlowDataBase.description],



                        flow = flowRepository.getFlowByUUID(it[ReturnFlowDataBase.flowUUID]),
                        situation = situationCalledRepository.getSituationCalledByUUID(it[CalledDataBase.situationUUID]),
                        branch = it[ReturnFlowDataBase.branch],


                        createdAt = it[ReturnFlowDataBase.createdAt],
                        modifiedAt = it[ReturnFlowDataBase.modifiedAt],
                )
                listReturnFlow.add(returnFlow)
            }
        }
        return listReturnFlow.toList()
    }

    override fun updateCalledSituation(calledUUID: UUID, situationUUID: UUID): Boolean? {
        return transaction {
            transaction {
            }
            false
        }
    }

    override fun getCalledByUUID(calledUUID: UUID): Called? {
        var called: Called? = null

        transaction {
            addLogger(StdOutSqlLogger)
            CalledDataBase.select(CalledDataBase.uuid eq calledUUID).map {
                called = Called(
                        uuid = it[CalledDataBase.uuid],
                        identifier = it[CalledDataBase.identifier],
                        userName = it[CalledDataBase.userName],
                        flow = flowRepository.getFlowByUUID(it[CalledDataBase.flowUUID]),
                        situation = situationCalledRepository.getSituationCalledByUUID(it[CalledDataBase.situationUUID]),
                        branch = it[CalledDataBase.branch],
                        telephone = it[CalledDataBase.telephone],
                        createdAt = it[CalledDataBase.createdAt],
                        modifiedAt = it[CalledDataBase.modifiedAt],
                )
            }
        }

        return called
    }


    override fun getCalledByIdentifier(identifier: Long): Called? {
        var called: Called? = null

        transaction {
            addLogger(StdOutSqlLogger)
            CalledDataBase.select(CalledDataBase.identifier eq identifier).map {
                called = Called(
                        uuid = it[CalledDataBase.uuid],
                        identifier = it[CalledDataBase.identifier],
                        userName = it[CalledDataBase.userName],
                        flow = flowRepository.getFlowByUUID(it[CalledDataBase.flowUUID]),
                        situation = situationCalledRepository.getSituationCalledByUUID(it[CalledDataBase.situationUUID]),
                        branch = it[CalledDataBase.branch],
                        descriptionCalled = descriptionCalledRepository.getDescriptionCalledByDCalledUUID(it[CalledDataBase.uuid])!!,
                        telephone = it[CalledDataBase.telephone],
                        createdAt = it[CalledDataBase.createdAt],
                        modifiedAt = it[CalledDataBase.modifiedAt],
                )
            }
        }

        return called
    }
}


private fun ResultRow.toCalled(
        flowRepository: FlowRepository,
        situationCalledRepository: SituationCalledRepository,
): Called {
    return Called(
            uuid = this[CalledDataBase.uuid],
            userName = this[CalledDataBase.userName],
            flow = flowRepository.getFlowByUUID(this[CalledDataBase.flowUUID]),
            situation = situationCalledRepository.getSituationCalledByUUID(this[CalledDataBase.situationUUID]),
            branch = this[CalledDataBase.branch],
            telephone = this[CalledDataBase.telephone],
            createdAt = this[CalledDataBase.createdAt],
            modifiedAt = this[CalledDataBase.modifiedAt],
    )
}