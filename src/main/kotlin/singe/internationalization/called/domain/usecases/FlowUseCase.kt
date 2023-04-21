package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.Flow

interface FlowUseCase {
    fun getFlow(): List<Flow>?
}
