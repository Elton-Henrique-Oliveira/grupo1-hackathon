package singe.internationalization.called.domain.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class DescriptionCalled(
    var uuid: UUID?,
    var calledUUID: UUID? = null,
    var title: String? = null,
    var priority: String? = null,
    var type: UUID? = null,
    var situation: Int? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var modifiedAt: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,
)
