package singe.internationalization.called.infraestructure.webservice

import singe.internationalization.called.domain.entities.FlowType

interface FlowTypeService {
    fun getFlowType(): List<FlowType>?
}
