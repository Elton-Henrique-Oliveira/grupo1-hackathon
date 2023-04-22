package singe.internationalization.called.infraestructure.webservice.implementation

import org.springframework.web.bind.annotation.*
import singe.internationalization.called.domain.entities.Flow
import singe.internationalization.called.domain.usecases.FlowUseCase
import singe.internationalization.called.infraestructure.webservice.FlowService


@RestController
@CrossOrigin(
    origins = ["http://10.0.11.143:3000", "http://10.0.11.76:3000", "http://10.0.11.139:3000"],
    allowCredentials = "true"
)
@RequestMapping("/flow")
class FlowServiceImplementation(
        val flowUserCase: FlowUseCase,
) : FlowService {
    @GetMapping("")
    override fun getFlow(): List<Flow>? {
        return flowUserCase.getFlow();
    }
}
