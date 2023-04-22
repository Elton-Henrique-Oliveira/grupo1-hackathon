package singe.internationalization.called.infraestructure.repository.database
import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime


object SituationCalledDataBase : Table("called_situation") {
    val uuid = uuid("uuid").uniqueIndex()
    val label = varchar("label", 100)
    val statusCode = integer("status_code")
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}

fun Query.withSituationCalledFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            "uuid" -> Op.build { SituationCalledDataBase.uuid eq Utils.uuidOrEmpty(it.value) }
            "label" -> Op.build { SituationCalledDataBase.label.lowerCase() like "%" + it.value.lowercase() + "%" }
            "statusCode" -> Op.build { SituationCalledDataBase.statusCode eq it.value.toInt()}
            "modifiedAtDate" -> Op.build {
                (SituationCalledDataBase.modifiedAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }
            "createdAtDate" -> Op.build {
                (SituationCalledDataBase.createdAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }

            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}