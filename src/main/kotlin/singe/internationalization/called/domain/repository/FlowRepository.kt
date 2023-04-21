package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.Flow

interface FlowRepository {
    fun getFlow(): List<Flow>

}