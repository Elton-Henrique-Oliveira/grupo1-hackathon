package singe.internationalization.called.domain.usecases

import singe.internationalization.called.domain.entities.ReturnFlow
interface ReturnFlowUseCaseUseCase {
    fun createAndUpdate(returnFlow: ReturnFlow)

    fun getReturnFlow(): List<ReturnFlow>?

}
