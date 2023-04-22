package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.ReturnedCalled
import singe.internationalization.called.domain.repository.ReturnedCalledRepository
import singe.internationalization.called.infraestructure.repository.database.ReturnedCalledDataBase
import java.time.LocalDateTime
import java.util.*

@Repository
class ReturnedCalledRepositoryImplementation : ReturnedCalledRepository {

    override fun createReturnedCalled(returnedCalled: ReturnedCalled): ReturnedCalled? {
        val idGeneratedVendor = UUID.randomUUID()

        returnedCalled.uuid = idGeneratedVendor

        val returned: ReturnedCalled = transaction {
            addLogger(StdOutSqlLogger)
            ReturnedCalledDataBase.insert {
                it[uuid] = returnedCalled.uuid!!
                it[returnedText] = returnedCalled.returnedText!!
                it[descriptionCalledUUID] = returnedCalled.descriptionCalledUUID!!
            }.resultedValues

            returnedCalled
        }

        return returned
    }

    override fun updateReturnedCalled(returnedCalled: ReturnedCalled) : ReturnedCalled?{
        return transaction {

            addLogger(StdOutSqlLogger)

            returnedCalled.modifiedAt = LocalDateTime.now()

            val returned: ReturnedCalled = transaction {
                ReturnedCalledDataBase.update({
                    ReturnedCalledDataBase.uuid eq returnedCalled.uuid!!
                }) {
                    ReturnedCalledDataBase.insert {
                        it[returnedText] = returnedCalled.returnedText!!
                        it[descriptionCalledUUID] = returnedCalled.descriptionCalledUUID!!
                        it[modifiedAt] = returnedCalled.modifiedAt!!
                    }.resultedValues
                }
                returnedCalled
            }
            returned
        }
    }

    override fun getReturnedCalled(): List<ReturnedCalled>? {

        val listaReturnedCalled: MutableList<ReturnedCalled> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            ReturnedCalledDataBase.selectAll().map {
                val returnedCalled = ReturnedCalled(
                    uuid = it[ReturnedCalledDataBase.uuid],
                    descriptionCalledUUID = it[ReturnedCalledDataBase.descriptionCalledUUID],
                    returnedText = it[ReturnedCalledDataBase.returnedText],
                    createdAt = it[ReturnedCalledDataBase.createdAt],
                    modifiedAt = it[ReturnedCalledDataBase.modifiedAt],
                )
                listaReturnedCalled.add(returnedCalled)
            }
        }
        return listaReturnedCalled.toList()
    }

    override fun getReturnedCalledByDescriptionCalledUUID(descriptionCalledUUID: UUID): List<ReturnedCalled>? {

        val listaReturnedCalled: MutableList<ReturnedCalled> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            ReturnedCalledDataBase.select(
                ReturnedCalledDataBase.descriptionCalledUUID eq descriptionCalledUUID
            ).map {
                val returnedCalled = ReturnedCalled(
                    uuid = it[ReturnedCalledDataBase.uuid],
                    descriptionCalledUUID = it[ReturnedCalledDataBase.descriptionCalledUUID],
                    returnedText = it[ReturnedCalledDataBase.returnedText],
                    createdAt = it[ReturnedCalledDataBase.createdAt],
                    modifiedAt = it[ReturnedCalledDataBase.modifiedAt],
                )
                listaReturnedCalled.add(returnedCalled)
            }
        }
        return listaReturnedCalled.toList()
    }

    override fun getReturnedCalledByUUID(uuid: UUID): List<ReturnedCalled>? {

        val listaReturnedCalled: MutableList<ReturnedCalled> = mutableListOf()

        transaction {
            addLogger(StdOutSqlLogger)
            ReturnedCalledDataBase.select(
                ReturnedCalledDataBase.uuid eq uuid
            ).map {
                val returnedCalled = ReturnedCalled(
                    uuid = it[ReturnedCalledDataBase.uuid],
                    descriptionCalledUUID = it[ReturnedCalledDataBase.descriptionCalledUUID],
                    returnedText = it[ReturnedCalledDataBase.returnedText],
                    createdAt = it[ReturnedCalledDataBase.createdAt],
                    modifiedAt = it[ReturnedCalledDataBase.modifiedAt],
                )
                listaReturnedCalled.add(returnedCalled)
            }
        }
        return listaReturnedCalled.toList()
    }
}