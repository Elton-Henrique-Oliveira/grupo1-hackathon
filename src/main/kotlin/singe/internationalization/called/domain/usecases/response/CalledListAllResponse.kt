package singe.internationalization.called.domain.usecases.response

import br.com.lince.singe.core.shared.error.GenericError

class CalledListAllResponse(
    val called: List<CalledListResponse>? = null,
    val page: Int? = null,
    val size: Int? = null,
    val numberPages: Int? = null,
    var error: GenericError? = null
) {
    override fun toString(): String {
        return "CalledListAllResponse=($called, $page, $size , $numberPages, $error)"
    }
}