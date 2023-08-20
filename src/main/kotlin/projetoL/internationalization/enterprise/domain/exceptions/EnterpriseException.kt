package projetoL.internationalization.enterprise.domain.exceptions

import projetoL.core.shared.error.GenericError

val ENTERPRISE_DOES_NOT_EXIST =
    EnterpriseException(
        "ENTERPRISE_DOES_NOT_EXIST",
        "an error occurred because the enterprise does not exist in the system"
    )

class EnterpriseException(
    code: String,
    description: String
) : GenericError("enterprise-module", code, description)