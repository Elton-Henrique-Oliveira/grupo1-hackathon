package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.Called
import singe.internationalization.called.domain.exceptions.CalledException

data class CalledResponse(
    var called: Called? = null,
    var error: CalledException? = null
)
