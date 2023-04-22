package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.Priority
import java.util.*

interface PriorityRepository {

    fun getPriority(): List<Priority>

    fun getPriorityByUUID(priorityUUID: UUID): Priority?
}