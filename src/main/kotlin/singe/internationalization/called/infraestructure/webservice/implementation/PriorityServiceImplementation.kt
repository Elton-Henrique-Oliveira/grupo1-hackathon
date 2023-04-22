package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import singe.internationalization.called.domain.entities.Priority
import singe.internationalization.called.domain.usecases.PriorityUseCase
import singe.internationalization.called.infraestructure.webservice.PriorityService

@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.76:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)

@RequestMapping("/priority")
class PriorityServiceImplementation(
    var useCase: PriorityUseCase,
) : PriorityService {

    @GetMapping("")
    override fun getPriority(): List<Priority>? {
        return useCase.getPriority()
    }

}