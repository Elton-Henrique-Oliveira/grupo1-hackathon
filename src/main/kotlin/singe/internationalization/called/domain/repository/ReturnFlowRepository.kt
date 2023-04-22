package singe.internationalization.called.domain.repository

import singe.internationalization.called.domain.entities.ReturnFlow

interface ReturnFlowRepository {
    fun createReturnFlow(returnFlow: ReturnFlow): ReturnFlow

    fun updateReturnFlow(returnFlow: ReturnFlow): ReturnFlow

    fun getReturnFlow(): List<ReturnFlow>

}