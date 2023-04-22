package singe.internationalization.called.domain.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*
class ReturnFlow {
    var uuid: UUID?,
    var userName: String? = null,
    var calledUUID: UUID? = null,
    var description: String? = null,
    var presentFlowUUID: UUID? = null,
    var previousFlowUUID: UUID? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var modifiedAt: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,
}