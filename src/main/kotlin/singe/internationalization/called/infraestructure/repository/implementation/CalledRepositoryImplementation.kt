package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.domain.repository.DescriptionCalledRepository
import singe.internationalization.called.infraestructure.repository.database.CalledDataBase
import java.time.LocalDateTime
import java.util.*

@Repository
class CalledRepositoryImplementation(
    //var descriptionCalledRepository : DescriptionCalledRepository,
) : CalledRepository {

    override fun createCalled(called: Called): Called {

        val idGeneratedVendor = UUID.randomUUID()

        called.uuid = idGeneratedVendor

        println(called)
        return transaction {
            val returned: Called = transaction {

                CalledDataBase.insert {
                    it[uuid] = called.uuid!!
                    it[identifier] = called.identifier!!
                    it[userName] = called.userName!!
                    it[type] = called.type!!
                    it[situation] = called.situation!!
                    it[branch] = called.branch!!
                    it[telephone] = called.telephone!!
                }.resultedValues!!
                called
            }

            println(returned)
            returned
        }

    }

    override fun updateCalled(called: Called): Called {
        return transaction {

            called.modifiedAt = LocalDateTime.now()

            val returned: Called = transaction {
                CalledDataBase.update({
                    CalledDataBase.uuid eq called.uuid!!
                }) {
                    it[uuid] = called.uuid!!
                    it[identifier] = called.identifier!!
                    it[userName] = called.userName!!
                    it[type] = called.type!!
                    it[situation] = called.situation!!
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
            CalledDataBase.selectAll().map {
                val called = Called(
                    uuid = it[CalledDataBase.uuid],
                    identifier = it[CalledDataBase.identifier],
                    userName = it[CalledDataBase.userName],
                    type = it[CalledDataBase.type],
                    situation = it[CalledDataBase.situation],
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

    override fun updateCalledSituation(calledUUID: UUID, situationUUID: Int): Boolean? {
        return transaction {
            transaction {
                CalledDataBase.update({
                    CalledDataBase.uuid eq calledUUID
                }) {
                    it[situation] = situationUUID
                }
            }
            true
        }
    }

    override fun getCalledByUUID(calledUUID: UUID): Called? {
        var called: Called? = null

        transaction {
            CalledDataBase.select(CalledDataBase.uuid eq calledUUID).map {
                called = Called(
                    uuid = it[CalledDataBase.uuid],
                    identifier = it[CalledDataBase.identifier],
                    userName = it[CalledDataBase.userName],
                    type = it[CalledDataBase.type],
                    situation = it[CalledDataBase.situation],
                    branch = it[CalledDataBase.branch],
                    telephone = it[CalledDataBase.telephone],
                    createdAt = it[CalledDataBase.createdAt],
                    modifiedAt = it[CalledDataBase.modifiedAt],
                )
            }
        }

        println(called)

        return called
    }


    override fun getCalledByIdentifier(identifier: String): Called? {

        println(identifier + "aqui porra")

        var called: Called? = null

        transaction {
            CalledDataBase.select(CalledDataBase.identifier eq identifier).map {
                called = Called(
                    uuid = it[CalledDataBase.uuid],
                    identifier = it[CalledDataBase.identifier],
                    userName = it[CalledDataBase.userName],
                    type = it[CalledDataBase.type],
                    situation = it[CalledDataBase.situation],
                    branch = it[CalledDataBase.branch],
                    //descriptionCalled = descriptionCalledRepository.getDescriptionCalledByDCalledUUID(it[CalledDataBase.uuid])!!,
                    telephone = it[CalledDataBase.telephone],
                    createdAt = it[CalledDataBase.createdAt],
                    modifiedAt = it[CalledDataBase.modifiedAt],
                )
            }
        }

        println(called)

        return called
    }
}


private fun ResultRow.toCalled(): Called {
    return Called(
        uuid = this[CalledDataBase.uuid],
        identifier = this[CalledDataBase.identifier],
        userName = this[CalledDataBase.userName],
        type = this[CalledDataBase.type],
        situation = this[CalledDataBase.situation],
        branch = this[CalledDataBase.branch],
        telephone = this[CalledDataBase.telephone],
        createdAt = this[CalledDataBase.createdAt],
        modifiedAt = this[CalledDataBase.modifiedAt],
    )
}