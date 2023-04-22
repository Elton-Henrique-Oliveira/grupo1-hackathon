package singe.internationalization.called.infraestructure.webservice

import singe.internationalization.called.domain.entities.Priority

interface PriorityService {
    fun getPriority(): List<Priority>?
}