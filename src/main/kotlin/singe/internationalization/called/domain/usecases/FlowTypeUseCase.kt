package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.FlowType
import java.util.*

interface FlowTypeUseCase {
    fun getFlowType(): List<FlowType>?

    fun getFlowTypeByFlowUUID(flowUUID: UUID): List<FlowType>?
}