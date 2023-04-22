package singe.internationalization.called.infraestructure.repository.database

import br.com.lince.singe.core.shared.utils.Utils
import br.com.lince.singe.core.shared.webservice.BasicFilter
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.lessEq
import org.jetbrains.exposed.sql.SqlExpressionBuilder.like
import org.jetbrains.exposed.sql.javatime.CurrentDateTime
import org.jetbrains.exposed.sql.javatime.datetime
import singe.internationalization.called.domain.entities.DescriptionCalled

object CalledDataBase : Table("called") {
    val uuid = uuid("uuid").uniqueIndex()
    val identifier = long("identifier").autoIncrement()
    val userName = varchar("name", 200)
    val flowUUID = reference("flow_uuid", FlowDataBase.uuid)
    val situationUUID = reference("situation_uuid", SituationCalledDataBase.uuid)
    val branch = long("branch").nullable()
    val telephone = long("telephone").nullable()
    val modifiedAt = datetime("modified_at").defaultExpression(CurrentDateTime)
    val createdAt = datetime("created_at").defaultExpression(CurrentDateTime)
    init {
        PrimaryKey(uuid)
    }
}

fun Query.withCalledFilters(filter: List<BasicFilter>?): Query {
    if (filter == null) {
        return this
    }
    val filters = filter.filter { it.name.isNotBlank() }.map {
        when (it.name) {
            "uuid" -> Op.build { CalledDataBase.uuid eq Utils.uuidOrEmpty(it.value) }
            "identifier" -> Op.build { CalledDataBase.identifier eq it.value.toLong()}
            "userName" -> Op.build { CalledDataBase.userName.lowerCase() like "%" + it.value.lowercase() + "%" }
            "modifiedAtDate" -> Op.build {
                 (CalledDataBase.modifiedAt lessEq Utils.dateConverterMax(
                    it.value
                ))
            }

            else -> throw Exception("invalid column name: ${it.name}")
        }
    }

    filters.forEach { this.andWhere { it } }

    return this
}