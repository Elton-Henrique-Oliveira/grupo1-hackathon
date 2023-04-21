package singe.internationalization.called.infraestructure.webservice

import singe.internationalization.called.domain.entities.FlowType
import java.util.UUID

interface FlowTypeService {
    fun getFlowType(): List<FlowType>?

    fun getFlowTypeByFlowUUID(flowUUID: UUID): List<FlowType>?
}
