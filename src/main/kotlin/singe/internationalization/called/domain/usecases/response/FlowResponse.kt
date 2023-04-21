package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.exceptions.FlowException

data class FlowResponse(
        var called: Flow? = null,
        var error: FlowException? = null
)
