package singe.internationalization.called.domain.exceptions

import br.com.lince.singe.core.shared.error.GenericError

val FLOW_CREATE_ERROR =
        FlowException("FLOW_CREATE_ERROR", "An error has ocurred on create flow")


class FlowException(
        code: String,
        description: String
) : GenericError("flow-module", code, description)