package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.FlowType

interface FlowTypeUseCase {
    fun getFlowType(): List<FlowType>?
}