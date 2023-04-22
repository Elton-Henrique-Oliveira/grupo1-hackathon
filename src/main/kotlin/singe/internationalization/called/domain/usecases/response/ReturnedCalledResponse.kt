package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.ReturnedCalled
import singe.internationalization.called.domain.exceptions.DescriptionCalledException

data class ReturnedCalledResponse(
    var returnedCalled: ReturnedCalled? = null,
    var error: DescriptionCalledException? = null
)
