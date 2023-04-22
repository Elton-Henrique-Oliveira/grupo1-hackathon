package singe.internationalization.called.domain.entities


import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class Flow (
    var uuid: UUID?,
    var label: String? = null,
    var statusCode: Int? = null,
    var flowType: List<FlowType>? = null,
    var isInfrastructure: Boolean? = null,
    var needValidating: Boolean? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var modifiedAt: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,
)






