package singe.internationalization.called.domain.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class ReturnedCalled (
    var uuid: UUID?,
    var returnedText: String? = null,
    var descriptionCalledUUID: UUID? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var modifiedAt: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,
)