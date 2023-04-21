package singe.internationalization.called.infraestructure.webservice


import singe.internationalization.called.domain.entities.Flow

interface FlowService {
    fun getFlow(): List<Flow>?
}
