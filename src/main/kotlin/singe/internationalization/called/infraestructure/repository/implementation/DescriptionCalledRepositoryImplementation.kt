package singe.internationalization.called.infraestructure.repository.implementation

import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import org.jetbrains.exposed.sql.update
import org.springframework.stereotype.Repository
import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.repository.DescriptionCalledRepository
import singe.internationalization.called.infraestructure.repository.database.DescriptionCalledDataBase
import singe.internationalization.called.infraestructure.repository.database.DescriptionCalledDataBase.uuid
import java.time.LocalDateTime
import java.util.*

@Repository
class DescriptionCalledRepositoryImplementation : DescriptionCalledRepository {

    override fun createDescriptionCalled(descriptionCalled: DescriptionCalled): DescriptionCalled {

        val idGeneratedVendor = UUID.randomUUID()

        descriptionCalled.uuid = idGeneratedVendor

        return transaction {
            val returned: DescriptionCalled = transaction {

                DescriptionCalledDataBase.insert {
                    it[uuid] = descriptionCalled.uuid!!
                    it[calledUUID] = descriptionCalled.calledUUID!!
                    it[title] = descriptionCalled.title!!
                    it[priority] = descriptionCalled.priority!!
                    it[typeSystemUUID] = descriptionCalled.typeSystemUUID!!
                    it[situation] = descriptionCalled.situation!!
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
                    it[priority] = descriptionCalled.priority!!
                    it[typeSystemUUID] = descriptionCalled.typeSystemUUID!!
                    it[situation] = descriptionCalled.situation!!
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
                    priority = it[DescriptionCalledDataBase.priority],
                    situation = it[DescriptionCalledDataBase.situation],
                    typeSystemUUID = it[DescriptionCalledDataBase.typeSystemUUID],
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
                    priority = it[DescriptionCalledDataBase.priority],
                    situation = it[DescriptionCalledDataBase.situation],
                    typeSystemUUID = it[DescriptionCalledDataBase.typeSystemUUID],
                )
            }
        }
        return descriptionCalled
    }

    override fun getDescriptionCalledByDCalledUUID(calledUUID: UUID): List<DescriptionCalled>? {
        val listDescriptionCalled: MutableList<DescriptionCalled> = mutableListOf()

        transaction {
            DescriptionCalledDataBase.select(DescriptionCalledDataBase.calledUUID eq calledUUID).map {
                val descriptionCalled = DescriptionCalled(
                    uuid = it[uuid],
                    calledUUID = it[DescriptionCalledDataBase.calledUUID],
                    title = it[DescriptionCalledDataBase.title],
                    priority = it[DescriptionCalledDataBase.priority],
                    situation = it[DescriptionCalledDataBase.situation],
                    typeSystemUUID = it[DescriptionCalledDataBase.typeSystemUUID],
                )
                listDescriptionCalled.add(descriptionCalled)
            }
        }
        return listDescriptionCalled.toList()
    }
}