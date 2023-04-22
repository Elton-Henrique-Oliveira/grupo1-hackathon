package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.Priority

interface PriorityUseCase {
    fun getPriority(): List<Priority>?

}