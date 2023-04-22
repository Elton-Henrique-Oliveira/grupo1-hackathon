package singe.internationalization.called.domain.usecases.response

import singe.internationalization.called.domain.entities.DescriptionCalled
import singe.internationalization.called.domain.exceptions.DescriptionCalledException

data class DescriptionCalledResponse(
    var descriptionCalled: DescriptionCalled? = null,
    var error: DescriptionCalledException? = null
)
