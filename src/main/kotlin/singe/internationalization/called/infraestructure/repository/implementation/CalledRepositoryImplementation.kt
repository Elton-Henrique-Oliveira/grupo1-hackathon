package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.repository.CalledRepository
import singe.internationalization.called.infraestructure.repository.database.CalledDataBase
import java.time.LocalDateTime
import java.util.*

@Repository
class CalledRepositoryImplementation : CalledRepository {

    override fun createCalled(called: Called): Called {
        val idGeneratedVendor = UUID.randomUUID()

        called.uuid = idGeneratedVendor

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
}