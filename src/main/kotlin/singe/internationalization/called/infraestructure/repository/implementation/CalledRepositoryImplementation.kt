package singe.internationalization.called.infraestructure.repository.implementation

import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.entities.SituationCalled
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.domain.repository.DescriptionCalledRepository
import singe.internationalization.called.domain.repository.FlowRepository
import singe.internationalization.called.domain.repository.SituationCalledRepository
import singe.internationalization.called.domain.usecases.response.CalledListResponse
import singe.internationalization.called.infraestructure.repository.database.*
import java.time.LocalDateTime
import java.util.*

@Repository
class CalledRepositoryImplementation(
    var descriptionCalledRepository: DescriptionCalledRepository,
    var flowRepository: FlowRepository,
    var situationCalledRepository: SituationCalledRepository,
) : CalledRepository {

    override fun createCalled(called: Called): Called {

        val idGeneratedVendor = UUID.randomUUID()

        called.uuid = idGeneratedVendor

        println()
        val returned: Called = transaction {
            addLogger(StdOutSqlLogger)

            CalledDataBase.insert {
                it[uuid] = called.uuid!!
                it[userName] = called.userName!!
                it[flowUUID] = called.flow!!.uuid!!
                it[situationUUID] = called.situation!!.uuid!!
                it[branch] = called.branch!!
                it[telephone] = called.telephone!!
            }.resultedValues
            called
        }
        println(returned)
        return returned
    }

    override fun updateCalled(called: Called): Called {
        return transaction {

            addLogger(StdOutSqlLogger)

            called.modifiedAt = LocalDateTime.now()

            val returned: Called = transaction {
                CalledDataBase.update({
                    CalledDataBase.uuid eq called.uuid!!
                }) {
                    it[uuid] = called.uuid!!
                    it[userName] = called.userName!!
                    it[flowUUID] = called.flow!!.uuid!!
                    it[situationUUID] = called.situation!!.uuid!!
                    it[branch] = called.branch!!
                    it[telephone] = called.telephone!!
                    it[modifiedAt] = called.modifiedAt!!
                }
                called
            }
            returned
        }
    }

    override fun getCalled(): List<Called> {

        val listCalled: MutableList<Called> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            CalledDataBase.selectAll().map {
                val called = Called(
                    uuid = it[CalledDataBase.uuid],
                    identifier = it[CalledDataBase.identifier],
                    userName = it[CalledDataBase.userName],
                    flow = flowRepository.getFlowByUUID(it[CalledDataBase.flowUUID]),
                    situation = situationCalledRepository.getSituationCalledByUUID(it[CalledDataBase.situationUUID]),
                    descriptionCalled = descriptionCalledRepository.getDescriptionCalledByCalledUUID(it[CalledDataBase.uuid]),
                    branch = it[CalledDataBase.branch],
                    telephone = it[CalledDataBase.telephone],
                    createdAt = it[CalledDataBase.createdAt],
                    modifiedAt = it[CalledDataBase.modifiedAt],
                )
                listCalled.add(called)
            }
        }
        return listCalled.toList()
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
                    descriptionCalled = descriptionCalledRepository.getDescriptionCalledByCalledUUID(it[CalledDataBase.uuid]),
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
                    descriptionCalled = descriptionCalledRepository.getDescriptionCalledByCalledUUID(it[CalledDataBase.uuid]),
                    telephone = it[CalledDataBase.telephone],
                    createdAt = it[CalledDataBase.createdAt],
                    modifiedAt = it[CalledDataBase.modifiedAt],
                )
            }
        }

        return called
    }

    override fun listAllCalled(
        page: Int,
        size: Int,
        orderBy: String,
        sortBy: String,
        filters: List<BasicFilter>?,
    ): List<CalledListResponse> {
        return transaction {
            addLogger(StdOutSqlLogger)
            val actorsQuery = CalledDataBase
                .selectAll()
                .limit(size, offset = (((page - 1) * size).toLong()))
                .orderBy(
                    when (sortBy) {
                        "desc" -> when (orderBy) {
                            "identifier" -> CalledDataBase.identifier to SortOrder.DESC
                            else -> CalledDataBase.identifier to SortOrder.DESC
                        }
                        "asc" -> when (orderBy) {
                            "identifier" -> CalledDataBase.identifier to SortOrder.ASC
                            else -> CalledDataBase.identifier to SortOrder.ASC
                        }
                        else -> error("VALUE NOT FOUND")
                    }
                )
            actorsQuery.withCalledFilters(filters).withDistinct(true).map {
                CalledListResponse(
                    calledUUID = it[CalledDataBase.uuid],
                    identifier =  it[CalledDataBase.identifier],
                    userName = it[CalledDataBase.userName],
                    flow = flowRepository.getFlowByUUID(it[CalledDataBase.flowUUID]),
                    situation = situationCalledRepository.getSituationCalledByUUID(it[CalledDataBase.situationUUID]),
                    branch = it[CalledDataBase.branch],
                    telephone = it[CalledDataBase.telephone],
                    descriptionCalled = descriptionCalledRepository.getDescriptionCalledByCalledUUID(it[CalledDataBase.uuid]),
                    modifiedAt = it[CalledDataBase.modifiedAt],
                    createdAt = it[CalledDataBase.createdAt],
                )
            }
        }
    }

    override fun getCountAllCalled(filters: List<BasicFilter>?): Int {
        return transaction {
            CalledDataBase.selectAll().withCalledFilters(filters).count().toInt()
        }
    }
}


private fun ResultRow.toCalled(
    flowRepository: FlowRepository,
    situationCalledRepository: SituationCalledRepository,
    descriptionCalledRepository: DescriptionCalledRepository
): Called {
    return Called(
        uuid = this[CalledDataBase.uuid],
        userName = this[CalledDataBase.userName],
        flow = flowRepository.getFlowByUUID(this[CalledDataBase.flowUUID]),
        situation = situationCalledRepository.getSituationCalledByUUID(this[CalledDataBase.situationUUID]),
        descriptionCalled = descriptionCalledRepository.getDescriptionCalledByCalledUUID(this[CalledDataBase.uuid]),
        branch = this[CalledDataBase.branch],
        telephone = this[CalledDataBase.telephone],
        createdAt = this[CalledDataBase.createdAt],
        modifiedAt = this[CalledDataBase.modifiedAt],
    )
}