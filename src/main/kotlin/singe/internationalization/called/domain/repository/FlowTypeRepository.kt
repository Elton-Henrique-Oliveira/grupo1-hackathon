package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.FlowType

interface FlowTypeRepository {
    fun getFlowType(): List<FlowType>
}