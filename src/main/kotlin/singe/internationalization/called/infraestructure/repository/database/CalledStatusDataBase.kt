package singe.internationalization.called.infraestructure.repository.database

import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime


object CalledStatusDataBase : Table("flow_type") {
    val uuid = uuid("uuid")
    val label = varchar("label", 60)
    val statusCode = integer("status_code").default(0)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
        uniqueIndex(uuid)
    }
}

fun Query.withCalledStatusFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            "uuid" -> Op.build { CalledStatusDataBase.uuid eq Utils.uuidOrEmpty(it.value) }
            "label" -> Op.build { CalledStatusDataBase.label.lowerCase() like "%" + it.value.lowercase() + "%" }
            "statusCode" -> Op.build { CalledStatusDataBase.statusCode eq it.value.toInt()}
            "modifiedAtDate" -> Op.build {
                (CalledStatusDataBase.modifiedAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }
            "createdAtDate" -> Op.build {
                (CalledStatusDataBase.createdAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }

            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}