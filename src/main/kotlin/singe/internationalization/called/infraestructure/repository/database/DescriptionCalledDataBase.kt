package singe.internationalization.called.infraestructure.repository.database

import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime


object DescriptionCalledDataBase : Table("description_called") {
    val uuid = uuid("uuid").uniqueIndex()
    val calledUUID = reference("called_uuid", CalledDataBase.uuid)
    val title = varchar("title", 250)
    val description = text("description")
    val priorityUUID = reference("priority_uuid", PriorityDataBase.uuid)
    val flowTypeUUID = reference("flow_type_uuid", FlowTypeDataBase.uuid)
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)

    init {
        PrimaryKey(uuid)
    }
}
fun Query.withDescriptionCalledFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            "uuid" -> Op.build { DescriptionCalledDataBase.uuid eq Utils.uuidOrEmpty(it.value) }
            "calledUUID" -> Op.build { DescriptionCalledDataBase.calledUUID eq Utils.uuidOrEmpty(it.value) }
            "title" -> Op.build { DescriptionCalledDataBase.title.lowerCase() like "%" + it.value.lowercase() + "%" }
            "priority" -> Op.build { DescriptionCalledDataBase.priority.lowerCase() like "%" + it.value.lowercase() + "%" }
            "typeSystemUUID" -> Op.build { DescriptionCalledDataBase.typeSystemUUID eq Utils.uuidOrEmpty(it.value) }
            "situation" -> Op.build { DescriptionCalledDataBase.situation eq it.value.toInt()}
            "modifiedAtDate" -> Op.build {
                (DescriptionCalledDataBase.modifiedAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }
            "createdAtDate" -> Op.build {
                (DescriptionCalledDataBase.createdAt lessEq Utils.dateConverterMax(
                        it.value
                ))
            }

            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}