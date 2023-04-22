package singe.internationalization.called.infraestructure.repository.database

import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import com.fasterxml.jackson.annotation.JsonFormat
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import java.time.LocalDateTime
import java.util.*

object ReturnFlowDataBase : Table("ReturnFlow") {
    val uuid = uuid("uuid").uniqueIndex()
    val userName = varchar("name", 200)
    val calledUUID = reference("called_uuid", CalledDataBase.uuid)

    val description = varchar("description", 200,)
    val presentFlowUUID = reference("presentFlow_uuid", CalledDataBase.uuid)
    val previousFlowUUID = reference("previousFlow_uuid", CalledDataBase.uuid)

    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    init {
        PrimaryKey(uuid)
    }
}

fun Query.withReturnFlowFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            "uuid" -> Op.build { ReturnFlowDataBase.uuid eq Utils.uuidOrEmpty(it.value) }
            "userName" -> Op.build { ReturnFlowDataBase.userName.lowerCase() like "%" + it.value.lowercase() + "%" }
            "description" -> Op.build { ReturnFlowDataBase.description.lowerCase() like "%" + it.value.lowercase() + "%" }
            "calledUUID" -> Op.build { ReturnFlowDataBase.calledUUID eq Utils.uuidOrEmpty(it.value) }
            "presentFlowUUID" -> Op.build { ReturnFlowDataBase.presentFlowUUID eq Utils.uuidOrEmpty(it.value) }
            "previousFlowUUID" -> Op.build { ReturnFlowDataBase.previousFlowUUID eq Utils.uuidOrEmpty(it.value) }
            "modifiedAtDate" -> Op.build {
                (ReturnFlowDataBase.modifiedAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }
            "createdAtDate" -> Op.build {
                (ReturnFlowDataBase.createdAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }

            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}
