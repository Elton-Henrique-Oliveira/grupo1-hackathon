package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.FlowType
import java.util.UUID

interface FlowTypeRepository {
    fun getFlowType(): List<FlowType>

    fun getFlowTypeByFlowUUID(flowUUID: UUID): List<FlowType>

    fun getFlowTypeByUUID(uuid: UUID): FlowType?
}