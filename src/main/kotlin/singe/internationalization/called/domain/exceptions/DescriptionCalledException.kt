package singe.internationalization.called.domain.exceptions

import br.com.lince.singe.core.shared.error.GenericError


val DESCRIPTION_CALLED_CREATE_ERROR =
    DescriptionCalledException("DESCRIPTION CALLED CREATE ERROR", "An error has ocurred on create description called")

val UNREGISTERED_CALLED =
    DescriptionCalledException("UNREGISTERED CALLED", "an error because the called was not informed")

val CALLED_NOT_INFORMED =
    DescriptionCalledException("CALLED NOT INFORMED", "an error because the called was not informed")

val DESCRIPTION_CALLED_DOES_NOT_EXIST =
    DescriptionCalledException("DESCRIPTION CALLED DOES NOT EXIST","an error occurred because the description called does not exist in the system")

val RETURNED_DESCRIPTION_CALLED_CREATE_ERROR =
    DescriptionCalledException("RETURNED DESCRIPTION CALLED CREATE ERROR", "An error has ocurred on create returned description called")

val RETURNED_DESCRIPTION_CALLED_DOES_NOT_EXIST =
    DescriptionCalledException("RETURNED DESCRIPTION CALLED DOES NOT EXIST","an error occurred because the returned description called does not exist in the system")


class DescriptionCalledException(
    code: String,
    description: String
) : GenericError("called-module", code, description)