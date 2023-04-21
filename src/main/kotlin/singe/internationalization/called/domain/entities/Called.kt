package singe.internationalization.called.domain.entities

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime
import java.util.*

class Called(
    var uuid: UUID?,
    var identifier: Long? = null,
    var userName: String? = null,
    var flow: Flow? = null,
    var situation: Int? = null,
    var branch: Long? = null,
    var telephone: Long? = null,
    var descriptionCalled: List<DescriptionCalled>? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var modifiedAt: LocalDateTime? = null,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    var createdAt: LocalDateTime? = null,
)
