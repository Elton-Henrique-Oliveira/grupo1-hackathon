package singe.internationalization.called.domain.repository


import singe.internationalization.called.domain.entities.CalledStatus

interface CalledStatusRepository {
    fun getCalledStatus(): List<CalledStatus>

}
