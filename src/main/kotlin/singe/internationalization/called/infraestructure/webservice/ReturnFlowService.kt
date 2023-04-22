package singe.internationalization.called.infraestructure.webservice

import singe.internationalization.called.domain.entities.ReturnFlow

interface ReturnFlowService {
    fun getReturnFlow(): List<ReturnFlow>?

}