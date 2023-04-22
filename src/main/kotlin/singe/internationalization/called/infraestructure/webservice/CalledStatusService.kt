package singe.internationalization.called.infraestructure.webservice

import singe.internationalization.called.domain.entities.CalledStatus

interface CalledStatusService {
    fun getCalledStatus(): List<CalledStatus>?

}
