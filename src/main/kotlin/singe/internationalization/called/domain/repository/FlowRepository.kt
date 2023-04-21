package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.Flow
import java.util.UUID

interface FlowRepository {
    fun getFlow(): List<Flow>

    fun getFlowByUUID(flowUUID: UUID): Flow?
}