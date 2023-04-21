package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.exceptions.CalledTypeException

data class FlowResponse(
        var called: Flow? = null,
        var error: CalledTypeException? = null
)
